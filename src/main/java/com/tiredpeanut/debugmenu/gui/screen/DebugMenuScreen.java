package com.tiredpeanut.debugmenu.gui.screen;

import com.tiredpeanut.debugmenu.DebugMenuMod;
import com.tiredpeanut.debugmenu.client.ClientForgeHandler;
import com.tiredpeanut.debugmenu.gui.widget.InspectionListItem;
import com.tiredpeanut.debugmenu.gui.widget.InspectionListItemModel;
import com.tiredpeanut.debugmenu.gui.widget.InspectionListWidget;
import com.tiredpeanut.debugmenu.utility.ClassFormatter;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;

public class DebugMenuScreen extends Screen {
    private static final String SCREENID = ".debugmenu";
    private static final Component TITLE = Component.translatable("component." + DebugMenuMod.MODID + SCREENID + ".title");
    private static final ResourceLocation BACKGROUND = new ResourceLocation(DebugMenuMod.MODID, "textures/gui/somebackground.png");

    private int leftPos;
    private int topPos;

    //Widgets
    private InspectionListWidget inspectionList;

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
    }

    //Pauses the game
    @Override
    public boolean isPauseScreen() {
        return true;
    }

    //Think of this as it if it is called in the constructor
    @Override
    protected void init() {
        super.init();

        //Build the cancel btn
        /*
        this.cancelBtn = addRenderableWidget(
            Button.builder(
                CANCELBTN,
                this::HandleCancelBtn
            )
            .bounds(this.leftPos + 8, this.topPos + 8, 30, 20)
            .build()
        );

        //Build the save btn
        this.saveBtn = addRenderableWidget(
            Button.builder(
                SAVEBTN,
                this::HandleSaveBtn
            )
            .bounds(cancelBtn.getX() + 40, cancelBtn.getY(), 30, 20)
            .build()
        );
        */
        //Idk wtf I'm doing -- sending it at this point with the UI
        int inspectionListPaddingY = 20;
        int inspectionListPaddingLeft = 5;
        this.inspectionList = new InspectionListWidget(this.minecraft, inspectionListPaddingLeft, 150, this.height, inspectionListPaddingY, this.height - inspectionListPaddingY, 30);

        inspectionList.addEntry(new InspectionListItem(inspectionList, this::onItemClick, new InspectionListItemModel("A Title", "A description")));
        inspectionList.addEntry(new InspectionListItem(inspectionList, this::onItemClick, new InspectionListItemModel("A new Title", "Newest Des")));
        inspectionList.addEntry(new InspectionListItem(inspectionList, this::onItemClick, new InspectionListItemModel("A new Title", "Newest Des")));
        inspectionList.addEntry(new InspectionListItem(inspectionList, this::onItemClick, new InspectionListItemModel("A new Title", "Newest Des")));
        inspectionList.addEntry(new InspectionListItem(inspectionList, this::onItemClick, new InspectionListItemModel("A new Title", "Newest Des")));
        inspectionList.addEntry(new InspectionListItem(inspectionList, this::onItemClick, new InspectionListItemModel("A new Title", "Newest Des")));
        inspectionList.addEntry(new InspectionListItem(inspectionList, this::onItemClick, new InspectionListItemModel("A new Title", "Newest Des")));
        inspectionList.addEntry(new InspectionListItem(inspectionList, this::onItemClick, new InspectionListItemModel("A new Title", "Newest Des")));
        inspectionList.addEntry(new InspectionListItem(inspectionList, this::onItemClick, new InspectionListItemModel("A new Title", "Newest Des")));
        inspectionList.addEntry(new InspectionListItem(inspectionList, this::onItemClick, new InspectionListItemModel("A new Title", "Newest Des")));


        this.addRenderableWidget(inspectionList);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {

        //Adds the dark transparent bg
        //Think of it as the background when a bootstrap modal is opened -- showing my age with this one
        renderDirtBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);

        //The order of what is rendered matters
        //Think of it as a z-Index in css for the most part
        //First rendered item is at the lowest index or behind other widgets
    }

    private void onItemClick(InspectionListItemModel model) {

    }
}
