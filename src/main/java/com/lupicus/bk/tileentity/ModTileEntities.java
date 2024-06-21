package com.lupicus.bk.tileentity;

import com.lupicus.bk.Main;
import com.lupicus.bk.block.ModBlocks;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModTileEntities
{
	public static final BlockEntityType<HoneyExtractorTileEntity> HONEY_EXTRACTOR = BlockEntityType.Builder.of(HoneyExtractorTileEntity::new, ModBlocks.HONEY_EXTRACTOR).build(null);

	public static void register()
	{
		register("honey_extractor", HONEY_EXTRACTOR);
	}

	private static void register(String key, BlockEntityType<?> type)
	{
		Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Main.MODID, key), type);
	}
}
