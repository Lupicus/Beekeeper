package com.lupicus.bk.item;

import com.lupicus.bk.block.ModBlocks;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems
{
	public static final Item BEE_POLLEN = new Item(new Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.6F).fast().effect(new MobEffectInstance(MobEffects.LEVITATION, 40, 6), 1.0F).build()).stacksTo(16));
	public static final Item ROYAL_JELLY = new Item(new Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1.0F).fast().effect(new MobEffectInstance(MobEffects.ABSORPTION, 9600, 0), 1.0F).alwaysEdible().build()).stacksTo(16));
	public static final Item HONEY_EXTRACTOR = new BlockItem(ModBlocks.HONEY_EXTRACTOR, new Properties());

	public static void register(IForgeRegistry<Item> forgeRegistry)
	{
		forgeRegistry.register("bee_pollen", BEE_POLLEN);
		forgeRegistry.register("royal_jelly", ROYAL_JELLY);
		forgeRegistry.register("honey_extractor", HONEY_EXTRACTOR);
	}

	public static void setupTabs(BuildCreativeModeTabContentsEvent event)
	{
		if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS)
		{
			event.accept(BEE_POLLEN);
			event.accept(ROYAL_JELLY);
		}
		else if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS)
		{
			event.accept(HONEY_EXTRACTOR);
		}
	}
}
