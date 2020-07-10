package com.lupicus.bk.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;

import com.google.common.collect.ImmutableSet;

import com.lupicus.bk.Main;
import com.lupicus.bk.item.ModItems;
import com.lupicus.bk.sound.ModSounds;
import com.lupicus.bk.village.ModPOI;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.brain.task.GiveHeroGiftsTask;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.coremod.api.ASMAPI;
import net.minecraftforge.registries.IForgeRegistry;

public class ModProfessions
{
	public static final VillagerProfession BEEKEEPER = create("beekeeper", ModPOI.BEEKEEPER, ImmutableSet.of(), ImmutableSet.of(Blocks.BEEHIVE), ModSounds.ENTITY_VILLAGER_WORK_BEEKEEPER);
	private static Constructor<?> ctr1 = null;
	private static Constructor<?> ctr2 = null;

	@SuppressWarnings("unused")
	private static VillagerProfession create(String key, PointOfInterestType type, SoundEvent event)
	{
		return create(key, type, ImmutableSet.of(), ImmutableSet.of(), event);
	}

	private static VillagerProfession create(String key, PointOfInterestType type, ImmutableSet<Item> items, ImmutableSet<Block> blocks, SoundEvent event)
	{
		VillagerProfession ret = null;
		try {
			Constructor<VillagerProfession> ctr = VillagerProfession.class.getDeclaredConstructor(String.class,
					PointOfInterestType.class, ImmutableSet.class, ImmutableSet.class, SoundEvent.class);
			ctr.setAccessible(true);
			ret = ctr.newInstance(key, type, items, blocks, event);
			ret.setRegistryName(Main.MODID, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void register(IForgeRegistry<VillagerProfession> registry)
	{
		registry.register(BEEKEEPER);
		setupTrades();
		setupLoot();
	}

	static void setupTrades()
	{
		findConstructors();
		ITrade[] value;
		Int2ObjectMap<ITrade[]> bk = new Int2ObjectArrayMap<>();
		value = new ITrade[] {EmeraldForItemsTrade(Items.HONEY_BOTTLE, 4, 8, 2), ItemsForEmeraldsTrade(Items.BEEHIVE, 1, 1, 2)};
		bk.put(1, value);
		value = new ITrade[] {EmeraldForItemsTrade(Items.SUNFLOWER, 18, 8, 4), ItemsForEmeraldsTrade(Items.SHEARS, 3, 1, 4), ItemsForEmeraldsTrade(Items.RED_TULIP, 1, 10, 6), ItemsForEmeraldsTrade(Items.ORANGE_TULIP, 1, 10, 6)};
		bk.put(2, value);
		value = new ITrade[] {EmeraldForItemsTrade(Items.GLASS_BOTTLE, 9, 8, 9), ItemsForEmeraldsTrade(ModItems.BEE_POLLEN, 1, 1, 9), ItemsForEmeraldsTrade(Items.HONEYCOMB, 1, 2, 8)};
		bk.put(3, value);
		value = new ITrade[] {EmeraldForItemsTrade(Items.BLUE_ORCHID, 9, 8, 12), ItemsForEmeraldsTrade(Items.BEE_SPAWN_EGG, 10, 1, 13)};
		bk.put(4, value);
		value = new ITrade[] {EmeraldForItemsTrade(Items.ALLIUM, 9, 8, 4), ItemsForEmeraldsTrade(ModItems.ROYAL_JELLY, 5, 1, 5)};
		bk.put(5, value);
		VillagerTrades.VILLAGER_DEFAULT_TRADES.put(BEEKEEPER, bk);
	}

	private static void findConstructors()
	{
		for (Class<?> c : VillagerTrades.class.getDeclaredClasses())
		{
			if (c.getName().endsWith("$EmeraldForItemsTrade"))
			{
				try {
					ctr1 = c.getDeclaredConstructor(IItemProvider.class, int.class, int.class, int.class);
					ctr1.setAccessible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (c.getName().endsWith("$ItemsForEmeraldsTrade"))
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
	private static ITrade EmeraldForItemsTrade(Item item, int count, int maxUses, int xpValue)
	{
		ITrade ret = null;
		try {
			ret = (ITrade) ctr1.newInstance(item, count, maxUses, xpValue);
		}
		catch (Exception e) {
		}
		return ret;
	}

	/**
	 * Selling Items
	 */
	private static ITrade ItemsForEmeraldsTrade(Item item, int cost, int count, int xpValue)
	{
		ITrade ret = null;
		try {
			ret = (ITrade) ctr2.newInstance(item, cost, count, xpValue);
		}
		catch (Exception e) {
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	private static void setupLoot()
	{
		try {
			String name = ASMAPI.mapField("field_220403_a"); // GIFTS
			Field field = GiveHeroGiftsTask.class.getDeclaredField(name);
			field.setAccessible(true);
			Map<VillagerProfession, ResourceLocation> value = (Map<VillagerProfession, ResourceLocation>) field.get(null);
			value.put(BEEKEEPER, new ResourceLocation(Main.MODID, "gameplay/hero_of_the_village/beekeeper_gift"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
