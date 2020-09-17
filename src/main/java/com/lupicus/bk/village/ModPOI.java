package com.lupicus.bk.village;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import com.lupicus.bk.Main;
import com.lupicus.bk.block.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.coremod.api.ASMAPI;
import net.minecraftforge.registries.IForgeRegistry;

public class ModPOI
{
	public static final PointOfInterestType BEEKEEPER = create("beekeeper", ModBlocks.HONEY_EXTRACTOR, 1, 1);

	private static PointOfInterestType create(String key, Block block, int maxFree, int proximity)
	{
		PointOfInterestType ret = null;
		try {
			Constructor<PointOfInterestType> ctr = PointOfInterestType.class.getDeclaredConstructor(String.class, Set.class, int.class, int.class);
			ctr.setAccessible(true);
			ret = ctr.newInstance(key, ImmutableSet.copyOf(block.getStateContainer().getValidStates()), maxFree, proximity);
			ret.setRegistryName(Main.MODID, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void register(IForgeRegistry<PointOfInterestType> registry)
	{
		registry.register(BEEKEEPER);
		addStates(BEEKEEPER);
	}

	static void addStates(PointOfInterestType type)
	{
		try {
			String name = ASMAPI.mapMethod("func_221052_a"); // registerBlockStates
			Method method = PointOfInterestType.class.getDeclaredMethod(name, PointOfInterestType.class);
			method.setAccessible(true);
			method.invoke(null, type);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
