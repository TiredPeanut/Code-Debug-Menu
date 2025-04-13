package com.tiredpeanut.debugmenu.gui.screen;

import com.tiredpeanut.debugmenu.DebugMenuMod;
import com.tiredpeanut.debugmenu.gui.widget.InspectionListItem;
import com.tiredpeanut.debugmenu.gui.widget.InspectionListItemModel;
import com.tiredpeanut.debugmenu.gui.widget.InspectionListWidget;
import com.tiredpeanut.debugmenu.utility.DebugMenuAnnotationUtility;
import com.tiredpeanut.debugmenu.utility.UIUtility;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class DebugMenuScreen extends Screen {

    public DebugMenuScreen() {
        super(TITLE);
    }

    private static final String SCREENID = ".debugmenu";
    private static final Component TITLE = Component.translatable("component." + DebugMenuMod.MODID + SCREENID + ".title");
    private static final ResourceLocation BACKGROUND = new ResourceLocation(DebugMenuMod.MODID, "textures/gui/somebackground.png");

    //Widgets
    private InspectionListWidget inspectionList;

    private Button cancelBtn;
    private static final Component CANCELBTN = Component.translatable("component." + DebugMenuMod.MODID + SCREENID + ".cancelBtn");
    //What happens when you click the cancel btn
    private void HandleCancelBtn(Button btn) {
        this.minecraft.setScreen(null);
    }

    private Button saveBtn;
    private static final Component SAVEBTN = Component.translatable("component." + DebugMenuMod.MODID + SCREENID + ".saveBtn");
    //What happens when you click the save btn
    private void HandleSaveBtn(Button btn) {

    }

    private static String currentItemDescriptionText;

    //Pauses the game
    @Override
    public boolean isPauseScreen() {
        return true;
    }

    //Think of this as it if it is called in the constructor
    @Override
    protected void init() {
        super.init();

        /*

        */
        //Idk wtf I'm doing -- sending it at this point with the UI
        int inspectionListPaddingY = 20;
        int inspectionListPaddingLeft = 5;
        this.inspectionList = new InspectionListWidget(this.minecraft, inspectionListPaddingLeft, 150, this.height, inspectionListPaddingY, this.height - inspectionListPaddingY, 30);

        List<InspectionListItemModel> inspectionListItemModels =  DebugMenuAnnotationUtility.getInspectionListItemModels();
        for(InspectionListItemModel inspectionListItemModel : inspectionListItemModels) {
            inspectionList.addEntry(new InspectionListItem(inspectionList, this::onItemClick, inspectionListItemModel));
        }

        this.addRenderableWidget(inspectionList);


    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {

        //Adds the dark transparent bg
        //Think of it as the background when a bootstrap modal is opened -- showing my age with this one
        renderDirtBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);

        //Create a black rectangle
        int afterInspectionListRight = this.inspectionList.getRight() + 10;
        graphics.fill(afterInspectionListRight, 20, this.width - 5, this.height -20, 0xFF000000);

        //Draw the description above the rectangle
        if(currentItemDescriptionText != null) {

            String clippedText = UIUtility.truncateTextToFit(this.minecraft.font, currentItemDescriptionText, this.width - 5);
            Component asComponent = Component.literal(clippedText);

            graphics.drawString(this.minecraft.font, asComponent, afterInspectionListRight, 8, 65535);
        }

        int blackBoxLeft = this.inspectionList.getRight() + 10;
        int blackBoxRight = this.width - 5;
        int blackBoxWidth = blackBoxRight - blackBoxLeft;

        int totalPadding = 5 * 3; // left + middle + right
        int buttonWidth = (blackBoxWidth - totalPadding) / 2;

        int cancelBtnX = blackBoxLeft + 5;
        int saveBtnX = cancelBtnX + buttonWidth + 5;

        this.cancelBtn = addRenderableWidget(
            Button.builder(CANCELBTN, this::HandleCancelBtn)
                .bounds(cancelBtnX, this.height - 17, buttonWidth, 15)
                .build()
        );

        this.saveBtn = addRenderableWidget(
            Button.builder(SAVEBTN, this::HandleSaveBtn)
                .bounds(saveBtnX, this.height - 17, buttonWidth, 15)
                .build()
        );
    }

    private void onItemClick(InspectionListItemModel model) {
        currentItemDescriptionText = model.description();
    }

}
