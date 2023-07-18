package com.lupicus.bk.tileentity;

import com.lupicus.bk.Main;
import com.lupicus.bk.block.ModBlocks;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModTileEntities
{
	public static final BlockEntityType<HoneyExtractorTileEntity> HONEY_EXTRACTOR = BlockEntityType.Builder.create(HoneyExtractorTileEntity::new, ModBlocks.HONEY_EXTRACTOR).build(null);

	public static void register()
	{
		register("honey_extractor", HONEY_EXTRACTOR);
	}

	private static void register(String key, BlockEntityType<?> type)
	{
		Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Main.MODID, key), type);
	}
}
