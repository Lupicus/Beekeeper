package com.lupicus.bk.village;

import com.google.common.collect.ImmutableSet;
import com.lupicus.bk.Main;
import com.lupicus.bk.block.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistry;

public class ModPOI
{
	public static final ResourceKey<PoiType> BEEKEEPER_KEY = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, new ResourceLocation(Main.MODID, "beekeeper"));
	public static final PoiType BEEKEEPER = create(ModBlocks.HONEY_EXTRACTOR, 1, 1);

	private static PoiType create(Block block, int maxFree, int proximity)
	{
		return new PoiType(ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates()), maxFree, proximity);
	}

	public static void register(IForgeRegistry<PoiType> registry)
	{
		registry.register(BEEKEEPER_KEY.location(), BEEKEEPER);
	}
}
