package com.tiredpeanut.debugmenu.client;

import com.tiredpeanut.debugmenu.DebugMenuMod;
import com.tiredpeanut.debugmenu.screen.DebugMenuScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DebugMenuMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeHandler {

    @SubscribeEvent
    public static void onClientTicket(TickEvent.ClientTickEvent event) {
        //Our key for the mod menu was pressed what do we do?
        if(Keybindings.INSTANCE.openDebugMenuKeyMapping.isDown()) {
            Keybindings.INSTANCE.openDebugMenuKeyMapping.consumeClick();

            Minecraft mc = Minecraft.getInstance();
            if(mc.screen == null) {
                mc.setScreen(new DebugMenuScreen());
            }

        }
    }
}
