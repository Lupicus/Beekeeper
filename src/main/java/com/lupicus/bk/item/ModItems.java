package com.lupicus.bk.item;

import com.lupicus.bk.Main;
import com.lupicus.bk.block.ModBlocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems
{
	public static final Item BEE_POLLEN = new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(3).saturationModifier(0.6F).snack().statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 40, 6), 1.0F).build()).maxCount(16));
	public static final Item ROYAL_JELLY = new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(7).saturationModifier(1.0F).snack().statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 9600, 0), 1.0F).alwaysEdible().build()).maxCount(16));
	public static final Item HONEY_EXTRACTOR = new BlockItem(ModBlocks.HONEY_EXTRACTOR, new FabricItemSettings());

	public static void register()
	{
		register("bee_pollen", BEE_POLLEN);
		register("royal_jelly", ROYAL_JELLY);
		register("honey_extractor", HONEY_EXTRACTOR);
	}

	public static void setupTabs()
	{
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register((c) -> {
			c.add(BEE_POLLEN);
			c.add(ROYAL_JELLY);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register((c) -> {
			c.add(HONEY_EXTRACTOR);
		});
	}

	private static void register(String key, Item item)
	{
		Registry.register(Registries.ITEM, new Identifier(Main.MODID, key), item);
	}
}
