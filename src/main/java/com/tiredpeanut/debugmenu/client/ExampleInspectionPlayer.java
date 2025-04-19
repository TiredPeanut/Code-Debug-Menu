package com.tiredpeanut.debugmenu.client;

import com.tiredpeanut.debugmenu.DebugMenuMod;
import com.tiredpeanut.debugmenu.annotations.DebugMenuClass;
import com.tiredpeanut.debugmenu.annotations.DebugMenuField;
import com.tiredpeanut.debugmenu.annotations.DebugMenuMethod;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DebugMenuMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
@DebugMenuClass(title = "Player Events", description = "Example inspection Player")
public class ExampleInspectionPlayer {

    @DebugMenuField(descriptionToolTip = "Your walking speed -- base player speed is 0.1f")
    public static float playerSpeed;

    @SubscribeEvent
    @DebugMenuMethod(methodDescription = "Player related data")
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;

        Player player = event.player;
        if(player.level().isClientSide() == false) {

            if(playerSpeed != 0.0f) {
                player.getAbilities().setWalkingSpeed(playerSpeed);
                player.onUpdateAbilities();
            }
        }
    }

    @DebugMenuField(descriptionToolTip = "Your jump height -- base player jump is 0.4D")
    public static double playerJumpHeight = 0.0D;

    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event) {
        if ((event.getEntity() instanceof Player) == false) return;

        Player player = (Player) event.getEntity();
        player.setDeltaMovement(player.getDeltaMovement().add(0, playerJumpHeight, 0));
    }
}
