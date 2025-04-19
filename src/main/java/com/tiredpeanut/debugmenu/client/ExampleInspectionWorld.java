package com.tiredpeanut.debugmenu.client;

import com.tiredpeanut.debugmenu.DebugMenuMod;
import com.tiredpeanut.debugmenu.annotations.DebugMenuClass;
import com.tiredpeanut.debugmenu.annotations.DebugMenuField;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;

@Mod.EventBusSubscriber(modid = DebugMenuMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
@DebugMenuClass(title = "World Events", description = "Example inspection World")
public class ExampleInspectionWorld {

    @DebugMenuField(descriptionToolTip = "Nutz")
    private static boolean freezeTime = false;
    private static Long storedTime = null;

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END && freezeTime == false) return;

        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) return;

        for (ServerLevel level : server.getAllLevels()) {
            if (freezeTime) {
                // Capture current time once when freeze starts
                if (storedTime == null) {
                    storedTime = level.getDayTime();
                }
                // Continuously set world time to freeze
                level.setDayTime(storedTime);
            } else {
                // Unfreeze - allow time to progress normally
                storedTime = null;
            }
        }
    }
}
