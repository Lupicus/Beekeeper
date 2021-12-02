package com.lupicus.bk.item;

import com.lupicus.bk.block.ModBlocks;

import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems
{
	public static final Item BEE_POLLEN = new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.6F).fast().effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 40, 6), 1.0F).build()).tab(CreativeModeTab.TAB_FOOD).stacksTo(16)).setRegistryName("bee_pollen");
	public static final Item ROYAL_JELLY = new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(1.0F).fast().effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 9600, 0), 1.0F).alwaysEat().build()).tab(CreativeModeTab.TAB_FOOD).stacksTo(16)).setRegistryName("royal_jelly");
	public static final Item HONEY_EXTRACTOR = new BlockItem(ModBlocks.HONEY_EXTRACTOR, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)).setRegistryName("honey_extractor");

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
