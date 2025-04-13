package com.tiredpeanut.debugmenu.client;

import com.tiredpeanut.debugmenu.DebugMenuMod;
import com.tiredpeanut.debugmenu.annotations.DebugMenuClass;
import com.tiredpeanut.debugmenu.annotations.DebugMenuMethod;
import com.tiredpeanut.debugmenu.gui.screen.DebugMenuScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@DebugMenuClass(title = "Forge Events", description = "Intercepted events of the forge event bus even loger text asdfasdfasdf  asdfasd fasd f asdfasdfasdfasdfasdf sdf asdf ")
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

    @SubscribeEvent
    @DebugMenuMethod(methodDescription = "Player related data")
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;

        Player player = event.player;
        if(player.level().isClientSide() == false) {

            float playerSpeed = 0.1f; // default walking speed
            player.getAbilities().setWalkingSpeed(playerSpeed);
            player.onUpdateAbilities();
        }
    }
}
