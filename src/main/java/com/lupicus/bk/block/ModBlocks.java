package com.lupicus.bk.block;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks
{
	public static final Block HONEY_EXTRACTOR = new HoneyExtractor(Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD)).setRegistryName("honey_extractor");

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
