package com.lupicus.bk.block;

import com.lupicus.bk.Main;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks
{
	public static final Block HONEY_EXTRACTOR = new HoneyExtractor(Properties.of().mapColor(MapColor.WOOD).strength(2.5F).sound(SoundType.WOOD));

	public static void register()
	{
		register("honey_extractor", HONEY_EXTRACTOR);
	}

	private static void register(String key, Block block)
	{
		Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(Main.MODID, key), block);
	}
}
