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
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ModProfessions
{
	public static final VillagerProfession BEEKEEPER = create("beekeeper", ModPOI.BEEKEEPER_KEY, ImmutableSet.of(), ImmutableSet.of(Blocks.BEEHIVE), ModSounds.ENTITY_VILLAGER_WORK_BEEKEEPER);

	@SuppressWarnings("unused")
	private static VillagerProfession create(String key, ResourceKey<PoiType> type, SoundEvent event)
	{
		return create(key, type, ImmutableSet.of(), ImmutableSet.of(), event);
	}

	private static VillagerProfession create(String key, ResourceKey<PoiType> type, ImmutableSet<Item> items, ImmutableSet<Block> blocks, SoundEvent event)
	{
		Predicate<Holder<PoiType>> pred = (h) -> h.is(type);
		return new VillagerProfession(key, pred, pred, items, blocks, event);
	}

	public static void register()
	{
		Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, makeKey(BEEKEEPER), BEEKEEPER);
		setupTrades();
		setupLoot();
	}

	private static ResourceLocation makeKey(VillagerProfession prof)
	{
		return new ResourceLocation(Main.MODID, prof.name());
	}

	static void setupTrades()
	{
		ItemListing[] value;
		Int2ObjectMap<ItemListing[]> map = new Int2ObjectOpenHashMap<>();
		value = new ItemListing[] {EmeraldForItemsTrade(Items.HONEY_BOTTLE, 4, 8, 2), EmeraldForItemsTrade(Items.OAK_LOG, 12, 8, 2), EmeraldForItemsTrade(Items.BIRCH_LOG, 12, 8, 2), ItemsForEmeraldsTrade(Items.BEEHIVE, 1, 1, 1), ItemsForEmeraldsTrade(Items.TORCH, 1, 16, 1)};
		map.put(1, value);
		value = new ItemListing[] {EmeraldForItemsTrade(Items.SUNFLOWER, 18, 8, 10), ItemsForEmeraldsTrade(Items.CAMPFIRE, 2, 1, 5), ItemsForEmeraldsTrade(Items.SHEARS, 3, 1, 5), ItemsForEmeraldsTrade(Items.RED_TULIP, 1, 10, 5), ItemsForEmeraldsTrade(Items.ORANGE_TULIP, 1, 10, 5)};
		map.put(2, value);
		value = new ItemListing[] {EmeraldForItemsTrade(Items.GLASS_BOTTLE, 9, 8, 20), EmeraldForItemsTrade(Items.HONEYCOMB, 9, 8, 20), ItemsForEmeraldsTrade(ModItems.BEE_POLLEN, 1, 1, 10)};
		map.put(3, value);
		value = new ItemListing[] {EmeraldForItemsTrade(Items.BLUE_ORCHID, 9, 8, 30), ItemsForEmeraldsTrade(Items.BEE_SPAWN_EGG, 10, 1, 15)};
		map.put(4, value);
		value = new ItemListing[] {EmeraldForItemsTrade(Items.ALLIUM, 9, 8, 30), ItemsForEmeraldsTrade(ModItems.ROYAL_JELLY, 5, 1, 30)};
		map.put(5, value);
		VillagerTrades.TRADES.put(BEEKEEPER, map);
	}

	/**
	 * Buying Items
	 */
	private static ItemListing EmeraldForItemsTrade(Item item, int count, int maxUses, int xpValue)
	{
		return new VillagerTrades.EmeraldForItems(item, count, maxUses, xpValue);
	}

	/**
	 * Selling Items
	 */
	private static ItemListing ItemsForEmeraldsTrade(Item item, int cost, int count, int xpValue)
	{
		return new VillagerTrades.ItemsForEmeralds(item, cost, count, xpValue);
	}

	private static void setupLoot()
	{
		VillagerInteractionRegistries.registerGiftLootTable(BEEKEEPER, ResourceKey.create(Registries.LOOT_TABLE, new ResourceLocation(Main.MODID, "gameplay/hero_of_the_village/beekeeper_gift")));
	}
}
