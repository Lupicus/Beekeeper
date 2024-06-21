package com.lupicus.bk.item;

import com.lupicus.bk.Main;
import com.lupicus.bk.block.ModBlocks;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;

public class ModItems
{
	public static final Item BEE_POLLEN = new Item(new Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.6F).fast().effect(new MobEffectInstance(MobEffects.LEVITATION, 40, 6), 1.0F).build()).stacksTo(16));
	public static final Item ROYAL_JELLY = new Item(new Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1.0F).fast().effect(new MobEffectInstance(MobEffects.ABSORPTION, 9600, 0), 1.0F).alwaysEdible().build()).stacksTo(16));
	public static final Item HONEY_EXTRACTOR = new BlockItem(ModBlocks.HONEY_EXTRACTOR, new Properties());

	public static void register()
	{
		register("bee_pollen", BEE_POLLEN);
		register("royal_jelly", ROYAL_JELLY);
		register("honey_extractor", HONEY_EXTRACTOR);
	}

	private static void register(String key, Item item)
	{
		Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Main.MODID, key), item);
	}

	public static void setupTabs()
	{
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register((c) -> {
			c.accept(BEE_POLLEN);
			c.accept(ROYAL_JELLY);
		});
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register((c) -> {
			c.accept(HONEY_EXTRACTOR);
		});
	}
}
