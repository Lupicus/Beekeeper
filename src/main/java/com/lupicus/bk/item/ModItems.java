package com.lupicus.bk.item;

import com.lupicus.bk.block.ModBlocks;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems
{
	public static final Item BEE_POLLEN = new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.6F).fast().effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 40, 6), 1.0F).build()).tab(CreativeModeTab.TAB_FOOD).stacksTo(16));
	public static final Item ROYAL_JELLY = new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(1.0F).fast().effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 9600, 0), 1.0F).alwaysEat().build()).tab(CreativeModeTab.TAB_FOOD).stacksTo(16));
	public static final Item HONEY_EXTRACTOR = new BlockItem(ModBlocks.HONEY_EXTRACTOR, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

	public static void register(IForgeRegistry<Item> forgeRegistry)
	{
		forgeRegistry.register("bee_pollen", BEE_POLLEN);
		forgeRegistry.register("royal_jelly", ROYAL_JELLY);
		forgeRegistry.register("honey_extractor", HONEY_EXTRACTOR);
	}
}
