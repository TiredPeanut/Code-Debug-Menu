package com.tiredpeanut.debugmenu.screen;

import com.tiredpeanut.debugmenu.DebugMenuMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class DebugMenuScreen extends Screen {
    private static final String SCREENID = "debugMenu";
    private static final Component TITLE = Component.translatable("component." + DebugMenuMod.MODID + SCREENID + ".title");


    private final int imageWidth;
    private final int imageHeight;
    private int leftPos;
    private int rightPos;

    //Widgets
    private Button cancelBtn;
    private static final Component CANCELBTN = Component.translatable("component." + DebugMenuMod.MODID + SCREENID + ".cancelBtn");
    //What happens when you click the cancel btn
    private void HandleCancelBtn(Button btn) {

    }

    private Button saveBtn;
    private static final Component SAVEBTN = Component.translatable("component." + DebugMenuMod.MODID + SCREENID + ".saveBtn");
    //What happens when you click the save btn
    private void HandleSaveBtn(Button btn) {

    }

    public DebugMenuScreen() {
        super(TITLE);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    //Pauses the game
    @Override
    public boolean isPauseScreen() {
        return true;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        //Adds the dark transparent bg
        //Think of it as the background when a bootstrap modal is opened -- showing my age with this one
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);

        //The order of what is rendered matters
        //Think of it as a z-Index in css for the most part
        //First rendered item is at the lowest index or behind other widgets


    }

    //Think of this as it if it is called in the constructor
    @Override
    protected void init() {
        super.init();

        //Center Horizontal
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.rightPos = (this.height - this.imageHeight) / 2;

        //Build the cancel btn
        this.cancelBtn = addRenderableWidget(
            Button.builder(
                CANCELBTN,
                this::HandleCancelBtn
            )
            .bounds(this.leftPos + 8, this.rightPos + 8, 20, 20)
            .build()
        );

        //Build the save btn
        this.saveBtn = addRenderableWidget(
            Button.builder(
                SAVEBTN,
                this::HandleSaveBtn
            )
            .bounds(cancelBtn.getX() + 8, cancelBtn.getY() + 8, 20, 20)
            .build()
        );
    }
}
