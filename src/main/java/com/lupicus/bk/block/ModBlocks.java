package com.lupicus.bk.block;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks
{
	public static final Block HONEY_EXTRACTOR = new HoneyExtractor(Properties.create(Material.ROCK).hardnessAndResistance(3.5F)).setRegistryName("honey_extractor");

	public static void register(IForgeRegistry<Block> forgeRegistry)
	{
		forgeRegistry.register(HONEY_EXTRACTOR);
	}

	@OnlyIn(Dist.CLIENT)
	public static void setRenderLayer()
	{
	}

	@OnlyIn(Dist.CLIENT)
	public static void register(BlockColors blockColors)
	{
	}
}
