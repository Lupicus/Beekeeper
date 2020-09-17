package com.lupicus.bk.item;

import com.lupicus.bk.block.ModBlocks;

import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems
{
	public static final Item BEE_POLLEN = new Item(new Item.Properties().food(new Food.Builder().hunger(3).saturation(0.6F).fastToEat().effect(new EffectInstance(Effects.LEVITATION, 40, 6), 1.0F).build()).group(ItemGroup.FOOD).maxStackSize(16)).setRegistryName("bee_pollen");
	public static final Item ROYAL_JELLY = new Item(new Item.Properties().food(new Food.Builder().hunger(7).saturation(1.0F).fastToEat().effect(new EffectInstance(Effects.ABSORPTION, 9600, 0), 1.0F).setAlwaysEdible().build()).group(ItemGroup.FOOD).maxStackSize(16)).setRegistryName("royal_jelly");
	public static final Item HONEY_EXTRACTOR = new BlockItem(ModBlocks.HONEY_EXTRACTOR, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("honey_extractor");

	public static void register(IForgeRegistry<Item> forgeRegistry)
	{
		forgeRegistry.registerAll(BEE_POLLEN, ROYAL_JELLY);
		forgeRegistry.register(HONEY_EXTRACTOR);
	}

	@OnlyIn(Dist.CLIENT)
	public static void register(ItemColors itemColors)
	{
	}
}
