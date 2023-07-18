package com.lupicus.bk.entity;

import java.util.function.Predicate;

import com.google.common.collect.ImmutableSet;

import com.lupicus.bk.Main;
import com.lupicus.bk.item.ModItems;
import com.lupicus.bk.sound.ModSounds;
import com.lupicus.bk.village.ModPOI;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.registry.VillagerInteractionRegistries;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradeOffers.Factory;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModProfessions
{
	public static final VillagerProfession BEEKEEPER = create("beekeeper", ModPOI.BEEKEEPER_KEY, ImmutableSet.of(), ImmutableSet.of(Blocks.BEEHIVE), ModSounds.ENTITY_VILLAGER_WORK_BEEKEEPER);

	@SuppressWarnings("unused")
	private static VillagerProfession create(String key, RegistryKey<PointOfInterestType> type, SoundEvent event)
	{
		return create(key, type, ImmutableSet.of(), ImmutableSet.of(), event);
	}

	private static VillagerProfession create(String key, RegistryKey<PointOfInterestType> type, ImmutableSet<Item> items, ImmutableSet<Block> blocks, SoundEvent event)
	{
		Predicate<RegistryEntry<PointOfInterestType>> pred = (h) -> h.matchesKey(type);
		return new VillagerProfession(key, pred, pred, items, blocks, event);
	}

	public static void register()
	{
		Registry.register(Registries.VILLAGER_PROFESSION, makeKey(BEEKEEPER), BEEKEEPER);
		setupTrades();
		setupLoot();
	}

	private static Identifier makeKey(VillagerProfession prof)
	{
		return new Identifier(Main.MODID, prof.id());
	}

	static void setupTrades()
	{
		Factory[] value;
		Int2ObjectMap<Factory[]> map = new Int2ObjectOpenHashMap<>();
		value = new Factory[] {EmeraldForItemsTrade(Items.HONEY_BOTTLE, 4, 8, 2), EmeraldForItemsTrade(Items.OAK_LOG, 12, 8, 2), EmeraldForItemsTrade(Items.BIRCH_LOG, 12, 8, 2), ItemsForEmeraldsTrade(Items.BEEHIVE, 1, 1, 2), ItemsForEmeraldsTrade(Items.TORCH, 1, 16, 1)};
		map.put(1, value);
		value = new Factory[] {EmeraldForItemsTrade(Items.SUNFLOWER, 18, 8, 4), ItemsForEmeraldsTrade(Items.CAMPFIRE, 2, 1, 5), ItemsForEmeraldsTrade(Items.SHEARS, 3, 1, 4), ItemsForEmeraldsTrade(Items.RED_TULIP, 1, 10, 6), ItemsForEmeraldsTrade(Items.ORANGE_TULIP, 1, 10, 6)};
		map.put(2, value);
		value = new Factory[] {EmeraldForItemsTrade(Items.GLASS_BOTTLE, 9, 8, 9),  EmeraldForItemsTrade(Items.HONEYCOMB, 3, 8, 6), ItemsForEmeraldsTrade(ModItems.BEE_POLLEN, 1, 1, 9)};
		map.put(3, value);
		value = new Factory[] {EmeraldForItemsTrade(Items.BLUE_ORCHID, 9, 8, 12), ItemsForEmeraldsTrade(Items.BEE_SPAWN_EGG, 10, 1, 13)};
		map.put(4, value);
		value = new Factory[] {EmeraldForItemsTrade(Items.ALLIUM, 9, 8, 4), ItemsForEmeraldsTrade(ModItems.ROYAL_JELLY, 5, 1, 5)};
		map.put(5, value);
		TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(BEEKEEPER, map);
	}

	/**
	 * Buying Items
	 */
	private static Factory EmeraldForItemsTrade(Item item, int count, int maxUses, int xpValue)
	{
		return new TradeOffers.BuyForOneEmeraldFactory(item, count, maxUses, xpValue);
	}

	/**
	 * Selling Items
	 */
	private static Factory ItemsForEmeraldsTrade(Item item, int cost, int count, int xpValue)
	{
		return new TradeOffers.SellItemFactory(item, cost, count, xpValue);
	}

	private static void setupLoot()
	{
		VillagerInteractionRegistries.registerGiftLootTable(BEEKEEPER, new Identifier(Main.MODID, "gameplay/hero_of_the_village/beekeeper_gift"));
	}
}
