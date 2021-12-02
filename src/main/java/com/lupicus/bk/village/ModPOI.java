package com.lupicus.bk.village;

import com.google.common.collect.ImmutableSet;

import com.lupicus.bk.Main;
import com.lupicus.bk.block.ModBlocks;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistry;

public class ModPOI
{
	public static final PoiType BEEKEEPER = create("beekeeper", ModBlocks.HONEY_EXTRACTOR, 1, 1);

	private static PoiType create(String key, Block block, int maxFree, int proximity)
	{
		PoiType ret = new PoiType(key, ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates()), maxFree, proximity);
		ret.setRegistryName(Main.MODID, key);
		return ret;
	}

	public static void register(IForgeRegistry<PoiType> registry)
	{
		registry.register(BEEKEEPER);
	}
}
