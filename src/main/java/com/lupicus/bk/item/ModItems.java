package com.lupicus.bk.item;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.lupicus.bk.Main;
import com.lupicus.bk.block.ModBlocks;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.block.Block;

public class ModItems
{
	public static final Item BEE_POLLEN = register("bee_pollen", Item::new, new Properties().food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.6F).build(),
			Consumables.defaultFood().consumeSeconds(0.8F).onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.LEVITATION, 40, 6)), 1.0F)).build()).stacksTo(16));
	public static final Item ROYAL_JELLY = register("royal_jelly", Item::new, new Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1.0F).alwaysEdible().build(),
			Consumables.defaultFood().consumeSeconds(0.8F).onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.ABSORPTION, 9600, 0)), 1.0F)).build()).stacksTo(16));
	public static final Item HONEY_EXTRACTOR = register(ModBlocks.HONEY_EXTRACTOR, BlockItem::new, new Properties());

	public static void register()
	{
	}

	private static Item register(String name, Function<Properties, Item> func, Properties prop)
	{
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Main.MODID, name));
		return Items.registerItem(key, func, prop);
	}

	private static Item register(Block block, BiFunction<Block, Properties, Item> func, Properties prop)
	{
		return Items.registerBlock(block, func, prop);
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
