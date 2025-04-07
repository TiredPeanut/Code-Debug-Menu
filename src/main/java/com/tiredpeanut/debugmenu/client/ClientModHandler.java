package com.tiredpeanut.debugmenu.client;

import com.tiredpeanut.debugmenu.DebugMenuMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

//Register client side events
//Specially registers the events on the mods event bus
@Mod.EventBusSubscriber(modid = DebugMenuMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModHandler {

    @SubscribeEvent
    public static void onRegisterKeys(RegisterKeyMappingsEvent event) {
        event.register(Keybindings.INSTANCE.openDebugMenuKeyMapping);
    }
}
