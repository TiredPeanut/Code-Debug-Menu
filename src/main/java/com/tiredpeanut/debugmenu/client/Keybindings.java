package com.tiredpeanut.debugmenu.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.tiredpeanut.debugmenu.DebugMenuMod;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

//Create Key Mappings that are then registered in the ClientHandler
public final class Keybindings {
    public static final Keybindings INSTANCE = new Keybindings();

    private  Keybindings() {

    }

    public static final String openDebugMenuKeyMappingKey = "key-" + DebugMenuMod.MODID + "-openDebugMenuKey";
    public final KeyMapping openDebugMenuKeyMapping = new KeyMapping(
            openDebugMenuKeyMappingKey,
            KeyConflictContext.IN_GAME,
            InputConstants.getKey(InputConstants.KEY_PERIOD, -1),
            KeyMapping.CATEGORY_MISC
    );
}
