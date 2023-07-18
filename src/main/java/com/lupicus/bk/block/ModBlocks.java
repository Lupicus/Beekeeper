package com.lupicus.bk.block;

import com.lupicus.bk.Main;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks
{
	public static final Block HONEY_EXTRACTOR = new HoneyExtractor(FabricBlockSettings.create().mapColor(MapColor.OAK_TAN).strength(2.5F).sounds(BlockSoundGroup.WOOD));

	public static void register()
	{
		register("honey_extractor", HONEY_EXTRACTOR);
	}

	private static void register(String key, Block block)
	{
		Registry.register(Registries.BLOCK, new Identifier(Main.MODID, key), block);
	}
}
