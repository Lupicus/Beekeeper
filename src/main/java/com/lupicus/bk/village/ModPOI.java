package com.lupicus.bk.village;

import com.lupicus.bk.Main;
import com.lupicus.bk.block.ModBlocks;

import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

public class ModPOI
{
	public static final RegistryKey<PointOfInterestType> BEEKEEPER_KEY = RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, new Identifier(Main.MODID, "beekeeper"));
	public static final PointOfInterestType BEEKEEPER = create(BEEKEEPER_KEY, ModBlocks.HONEY_EXTRACTOR, 1, 1);

	private static PointOfInterestType create(RegistryKey<PointOfInterestType> key, Block block, int maxFree, int proximity)
	{
		return PointOfInterestHelper.register(key.getValue(), 1, 1, block);
	}
}
