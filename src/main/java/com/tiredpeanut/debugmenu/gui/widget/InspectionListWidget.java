package com.tiredpeanut.debugmenu.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ObjectSelectionList;
import org.jetbrains.annotations.NotNull;

public class InspectionListWidget extends ObjectSelectionList<InspectionListItem> {

    public InspectionListWidget(Minecraft mc, int width, int height, int top, int bottom, int itemHeight) {
        super(mc, width, height, top, bottom, itemHeight);
    }

    @Override
    public int addEntry(@NotNull InspectionListItem entry) {
        return super.addEntry(entry);
    }

    public Minecraft getMinecraftInstance() {
        return super.minecraft;
    }
}
