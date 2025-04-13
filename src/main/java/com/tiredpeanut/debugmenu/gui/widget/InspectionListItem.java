package com.tiredpeanut.debugmenu.gui.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;
import java.util.function.Consumer;

public class InspectionListItem extends ObjectSelectionList.Entry<InspectionListItem> {

    private final InspectionListWidget _parent;
    private final Consumer<InspectionListItemModel> _onClickDelegate;
    private final InspectionListItemModel _model;
    private Component _title;
    //private Component _description;

    public InspectionListItem(InspectionListWidget parent, Consumer<InspectionListItemModel> onClickDelegate, InspectionListItemModel model) {
        _parent = parent;
        _onClickDelegate = onClickDelegate;
        _model  = model;
    }

    private void init() {
        _title = Component.literal(_model.title());
        //_description = Component.literal(_model.description());
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pIndex, int pTop, int pLeft, int pWidth, int pHeight, int pMouseX, int pMouseY, boolean pHovering, float pPartialTick) {
        init();

        Font font = _parent.getMinecraftInstance().font;
        pGuiGraphics.drawString(font, _title, pLeft + 38, pTop + 5, 65535);

        /* PoseStack poseStack = pGuiGraphics.pose(); // This controls transformations
        poseStack.pushPose();
        poseStack.scale(0.75f, 0.75f, 1.0f); // Scale down font size to 75%
        // Since we're scaling down, we need to adjust x/y so text still appears in the right place
        float scale = 0.75f;
        float scaledX = pTop / scale;
        float scaledY = pLeft / scale;

        pGuiGraphics.drawString(font, _description, (int) scaledY + 55, (int)scaledX + 21, 65535);
        poseStack.popPose();
        */
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
