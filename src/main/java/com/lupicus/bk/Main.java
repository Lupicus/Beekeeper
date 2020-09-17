package com.lupicus.bk;

import com.lupicus.bk.block.ModBlocks;
import com.lupicus.bk.entity.ModProfessions;
import com.lupicus.bk.item.ModItems;
import com.lupicus.bk.sound.ModSounds;
import com.lupicus.bk.village.ModPOI;
import com.lupicus.bk.world.ModVillage;

import net.minecraft.block.Block;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MODID)
public class Main
{
    public static final String MODID = "bk";

    public Main()
    {
    	FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

	@SubscribeEvent
	public void setupCommon(final FMLCommonSetupEvent event)
	{
		ModVillage.updatePools();
	}

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents
    {
	    @SubscribeEvent
	    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event)
	    {
	        ModBlocks.register(event.getRegistry());
	    }

	    @SubscribeEvent
	    public static void onItemsRegistry(final RegistryEvent.Register<Item> event)
	    {
	        ModItems.register(event.getRegistry());
	    }

        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void onColorsRegistry(final ColorHandlerEvent.Block event)
        {
        	ModBlocks.register(event.getBlockColors());
        }

        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void onColorsRegistry(final ColorHandlerEvent.Item event)
        {
        	ModItems.register(event.getItemColors());
        }

	    @SubscribeEvent
	    public static void onProfRegistry(final RegistryEvent.Register<VillagerProfession> event)
	    {
	    	ModProfessions.register(event.getRegistry());
	    }

	    @SubscribeEvent
	    public static void onPOIRegistry(final RegistryEvent.Register<PointOfInterestType> event)
	    {
	    	ModPOI.register(event.getRegistry());
	    }

        @SubscribeEvent
        public static void onSoundRegistry(final RegistryEvent.Register<SoundEvent> event)
        {
        	ModSounds.register(event.getRegistry());
        }
    }
}
