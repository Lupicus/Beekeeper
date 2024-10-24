package com.lupicus.bk.block;

import java.util.function.Function;

import com.lupicus.bk.Main;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks
{
	public static final Block HONEY_EXTRACTOR = register("honey_extractor", HoneyExtractor::new, Properties.of().mapColor(MapColor.WOOD).strength(2.5F).sound(SoundType.WOOD));

	public static void register()
	{
	}

	private static Block register(String name, Function<Properties, Block> func, Properties prop)
	{
		ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Main.MODID, name));
		return Blocks.register(key, func, prop);
	}
}
