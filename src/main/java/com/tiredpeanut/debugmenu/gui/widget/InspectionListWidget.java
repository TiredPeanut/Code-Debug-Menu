package com.tiredpeanut.debugmenu.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ObjectSelectionList;
import org.jetbrains.annotations.NotNull;

public class InspectionListWidget extends ObjectSelectionList<InspectionListItem> {

    public InspectionListWidget(Minecraft mc, int leftPos, int width, int height, int top, int bottom, int itemHeight) {
        super(mc, width, height, top, bottom, itemHeight);
        this.setLeftPos(leftPos);
    }


    @Override
    public int addEntry(@NotNull InspectionListItem entry) {
        return super.addEntry(entry);
    }

    @Override
    public void clearEntries() {
        super.clearEntries();
    }

    @Override
    public int getScrollbarPosition() {
        return this.getLeft() + this.getWidth() - 6; // align scrollbar with list edge
    }

    @Override
    public int getLeft() {
        return super.getLeft() + 4; // optional: give some left margin inside rows
    }

    public Minecraft getMinecraftInstance() {
        return super.minecraft;
    }
}
