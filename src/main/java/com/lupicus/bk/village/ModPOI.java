package com.lupicus.bk.village;

import com.lupicus.bk.Main;
import com.lupicus.bk.block.ModBlocks;

import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;

public class ModPOI
{
	public static final ResourceKey<PoiType> BEEKEEPER_KEY = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, ResourceLocation.fromNamespaceAndPath(Main.MODID, "beekeeper"));
	public static final PoiType BEEKEEPER = register(BEEKEEPER_KEY, ModBlocks.HONEY_EXTRACTOR, 1, 1);

	private static PoiType register(ResourceKey<PoiType> key, Block block, int maxFree, int proximity)
	{
		return PointOfInterestHelper.register(key.location(), maxFree, proximity, block);
	}
}
