package com.lupicus.bk.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks
{
	public static final Block HONEY_EXTRACTOR = new HoneyExtractor(Properties.of().mapColor(MapColor.WOOD).strength(2.5F).sound(SoundType.WOOD));

	public static void register(IForgeRegistry<Block> forgeRegistry)
	{
		forgeRegistry.register("honey_extractor", HONEY_EXTRACTOR);
	}
}
