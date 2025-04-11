package com.tiredpeanut.debugmenu.gui.widget;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class InspectionListItem extends ObjectSelectionList.Entry<InspectionListItem> {

    private final InspectionListWidget _parent;
    private final Consumer<InspectionListItemModel> _onClickDelegate;
    private final InspectionListItemModel _model;
    private Component _title;
    private Component _description;

    public InspectionListItem(InspectionListWidget parent, Consumer<InspectionListItemModel> onClickDelegate, InspectionListItemModel model) {
        _parent = parent;
        _onClickDelegate = onClickDelegate;
        _model  = model;
    }

    private void init() {
        _title = Component.literal(_model.title());
        _description = Component.literal(_model.description());
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pIndex, int pTop, int pLeft, int pWidth, int pHeight, int pMouseX, int pMouseY, boolean pHovering, float pPartialTick) {
        init();
        pGuiGraphics.drawString(_parent.getMinecraftInstance().font, _title, pTop, 30, 65535);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        _onClickDelegate.accept(_model);
        return true;
    }

    @Override
    public Component getNarration() {
        return null;
    }
}
