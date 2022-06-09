package com.lupicus.bk.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.function.Predicate;

import com.google.common.collect.ImmutableSet;

import com.lupicus.bk.Main;
import com.lupicus.bk.item.ModItems;
import com.lupicus.bk.sound.ModSounds;
import com.lupicus.bk.village.ModPOI;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.behavior.GiveGiftToHero;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.coremod.api.ASMAPI;
import net.minecraftforge.registries.IForgeRegistry;

public class ModProfessions
{
	public static final VillagerProfession BEEKEEPER = create("beekeeper", ModPOI.BEEKEEPER_KEY, ImmutableSet.of(), ImmutableSet.of(Blocks.BEEHIVE), ModSounds.ENTITY_VILLAGER_WORK_BEEKEEPER);
	private static Constructor<?> ctr1 = null;
	private static Constructor<?> ctr2 = null;

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

	public static void register(IForgeRegistry<VillagerProfession> registry)
	{
		registry.register(makeKey(BEEKEEPER), BEEKEEPER);
		setupTrades();
		setupLoot();
	}

	private static ResourceLocation makeKey(VillagerProfession prof)
	{
		return new ResourceLocation(Main.MODID, prof.name());
	}

	static void setupTrades()
	{
		findConstructors();
		ItemListing[] value;
		Int2ObjectMap<ItemListing[]> bk = new Int2ObjectArrayMap<>();
		value = new ItemListing[] {EmeraldForItemsTrade(Items.HONEY_BOTTLE, 4, 8, 2), ItemsForEmeraldsTrade(Items.BEEHIVE, 1, 1, 2)};
		bk.put(1, value);
		value = new ItemListing[] {EmeraldForItemsTrade(Items.SUNFLOWER, 18, 8, 4), ItemsForEmeraldsTrade(Items.SHEARS, 3, 1, 4), ItemsForEmeraldsTrade(Items.RED_TULIP, 1, 10, 6), ItemsForEmeraldsTrade(Items.ORANGE_TULIP, 1, 10, 6)};
		bk.put(2, value);
		value = new ItemListing[] {EmeraldForItemsTrade(Items.GLASS_BOTTLE, 9, 8, 9), ItemsForEmeraldsTrade(ModItems.BEE_POLLEN, 1, 1, 9), ItemsForEmeraldsTrade(Items.HONEYCOMB, 1, 2, 8)};
		bk.put(3, value);
		value = new ItemListing[] {EmeraldForItemsTrade(Items.BLUE_ORCHID, 9, 8, 12), ItemsForEmeraldsTrade(Items.BEE_SPAWN_EGG, 10, 1, 13)};
		bk.put(4, value);
		value = new ItemListing[] {EmeraldForItemsTrade(Items.ALLIUM, 9, 8, 4), ItemsForEmeraldsTrade(ModItems.ROYAL_JELLY, 5, 1, 5)};
		bk.put(5, value);
		VillagerTrades.TRADES.put(BEEKEEPER, bk);
	}

	private static void findConstructors()
	{
		for (Class<?> c : VillagerTrades.class.getDeclaredClasses())
		{
			if (c.getName().endsWith("$EmeraldForItems"))
			{
				try {
					ctr1 = c.getDeclaredConstructor(ItemLike.class, int.class, int.class, int.class);
					ctr1.setAccessible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (c.getName().endsWith("$ItemsForEmeralds"))
			{
				try {
					ctr2 = c.getDeclaredConstructor(Item.class, int.class, int.class, int.class);
					ctr2.setAccessible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Buying Items
	 */
	private static ItemListing EmeraldForItemsTrade(Item item, int count, int maxUses, int xpValue)
	{
		ItemListing ret = null;
		try {
			ret = (ItemListing) ctr1.newInstance(item, count, maxUses, xpValue);
		}
		catch (Exception e) {
		}
		return ret;
	}

	/**
	 * Selling Items
	 */
	private static ItemListing ItemsForEmeraldsTrade(Item item, int cost, int count, int xpValue)
	{
		ItemListing ret = null;
		try {
			ret = (ItemListing) ctr2.newInstance(item, cost, count, xpValue);
		}
		catch (Exception e) {
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	private static void setupLoot()
	{
		try {
			String name = ASMAPI.mapField("f_147550_"); // GIFTS
			Field field = GiveGiftToHero.class.getDeclaredField(name);
			field.setAccessible(true);
			Map<VillagerProfession, ResourceLocation> value = (Map<VillagerProfession, ResourceLocation>) field.get(null);
			value.put(BEEKEEPER, new ResourceLocation(Main.MODID, "gameplay/hero_of_the_village/beekeeper_gift"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
