package com.tiredpeanut.debugmenu.gui.screen;

import com.tiredpeanut.debugmenu.DebugMenuMod;
import com.tiredpeanut.debugmenu.gui.widget.InspectionListItem;
import com.tiredpeanut.debugmenu.gui.widget.InspectionListItemModel;
import com.tiredpeanut.debugmenu.gui.widget.InspectionListWidget;
import com.tiredpeanut.debugmenu.utility.DebugMenuAnnotationUtility;
import com.tiredpeanut.debugmenu.utility.UIUtility;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DebugMenuScreen extends Screen {

    public DebugMenuScreen() {
        super(TITLE);
    }

    private static final String SCREENID = ".debugmenu";
    private static final Component TITLE = Component.translatable("component." + DebugMenuMod.MODID + SCREENID + ".title");
    private static final ResourceLocation BACKGROUND = new ResourceLocation(DebugMenuMod.MODID, "textures/gui/somebackground.png");

    private InspectionListWidget inspectionList;
    private static InspectionListItemModel currentSelectedItemModel;
    private static Component currentSelectedItemDescription;
    private void onInspectionListItemClick(InspectionListItemModel model) {
        int blackBoxLeft = this.inspectionList.getRight() + 10;
        String descriptionClippedText = UIUtility.truncateTextToFit(this.minecraft.font, model.description(), this.width - blackBoxLeft - 10);
        currentSelectedItemDescription = Component.literal(descriptionClippedText);
        currentSelectedItemModel = model;

        int y = 25; // start drawing at Y=25
        int lineSpacing = this.minecraft.font.lineHeight + 4; // adjust for font size + spacing
        int blackPaddingLeft = blackBoxLeft + 5;
        int maxTextWidth = this.width - blackPaddingLeft - 10;

        for (DebugMenuAnnotationUtility.DebugMenuFieldModel fieldModel : DebugMenuAnnotationUtility.getDebugMenuFieldModels(currentSelectedItemModel.referenceClass())) {
            String fieldType = fieldModel.field().getType().getTypeName();
            String fieldName = fieldModel.field().getName();
            String fullText = fieldType + " " + fieldName;

            for (FormattedCharSequence line : this.minecraft.font.split(Component.literal(fullText), maxTextWidth)) {
                graphics.drawString(this.minecraft.font, line, blackPaddingLeft, y, 65535);
                y += lineSpacing;
            }

            EditBox valueEditBox = new EditBox(this.minecraft.font, blackPaddingLeft, y, 20, 16, Component.literal("test"));
            this.addRenderableWidget(valueEditBox);
        }
    }

    private EditBox searchBox;
    private static final Component searchBoxPlaceholder = Component.translatable("component." + DebugMenuMod.MODID + SCREENID + ".searchBoxPlaceholder");
    //What happens when you search in the searchBox
    private void onSearchBoxChange(String query) {
        String lowerSearch = query.toLowerCase();

        List<InspectionListItemModel> filtered = this.inspectionList.getAllEntries().stream()
                .filter(model -> model._model.title().toLowerCase().contains(lowerSearch))
                .map(s -> s._model)
                .toList();

        this.updateInspectionListItems(filtered);
    }

    private Button cancelBtn;
    private static final Component CANCELBTN = Component.translatable("component." + DebugMenuMod.MODID + SCREENID + ".cancelBtn");
    //What happens when you click the cancel btn
    private void onCancelBtnClick(Button btn) {
        this.minecraft.setScreen(null);
    }

    private Button saveBtn;
    private static final Component SAVEBTN = Component.translatable("component." + DebugMenuMod.MODID + SCREENID + ".saveBtn");
    //What happens when you click the save btn
    private void onSaveBtnClick(Button btn) {

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

        int inspectionListPaddingLeft = 5;

        //Idk wtf I'm doing -- sending it at this point with the UI
        int inspectionListPaddingY = 25;
        this.inspectionList = new InspectionListWidget(this.minecraft, inspectionListPaddingLeft, 150, this.height, inspectionListPaddingY, this.height - 20, 30);

        List<InspectionListItemModel> inspectionListItemModels =  DebugMenuAnnotationUtility.getInspectionListItemModels();
        List<InspectionListItem> items = this.updateInspectionListItems(inspectionListItemModels);
        this.inspectionList.setAllEntries(items);
        this.addRenderableWidget(inspectionList);

        // Search box
        this.searchBox = new EditBox(
            this.font,
            inspectionListPaddingLeft,
            5,
            this.inspectionList.getWidth(),
            16,
            searchBoxPlaceholder
        );
        this.searchBox.setResponder(this::onSearchBoxChange);
        this.addRenderableWidget(this.searchBox);

        initButtons();
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {

        //Adds the dark transparent bg
        //Think of it as the background when a bootstrap modal is opened -- showing my age with this one
        renderDirtBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTicks);

        // Draw placeholder text when search box is empty and not focused
        if (this.searchBox.getValue().isEmpty() && !this.searchBox.isFocused()) {
            graphics.drawString(
                this.font,
                searchBoxPlaceholder,
                this.searchBox.getX() + 4,
                this.searchBox.getY() + 4,
                0x888888, // light gray placeholder text
                false
            );
        }

        //Create a black rectangle
        int blackBoxLeft = this.inspectionList.getRight() + 10;
        int blackBoxRight = this.width - 5;
        int blackBoxWidth = blackBoxRight - blackBoxLeft;
        graphics.fill(blackBoxLeft, 20, this.width - 5, this.height -20, 0xFF000000);


        int y = 25; // start drawing at Y=25
        int lineSpacing = this.minecraft.font.lineHeight + 4; // adjust for font size + spacing
        int blackPaddingLeft = blackBoxLeft + 5;
        int maxTextWidth = this.width - blackPaddingLeft - 10;
        //An item is selected what should we render?
        if(currentSelectedItemModel != null) {

            graphics.drawString(this.minecraft.font, currentSelectedItemDescription, blackBoxLeft, 8, 65535);

        }


    }

    private void initButtons() {
        int totalWidth = (this.width - 5) - (this.inspectionList.getRight() + 10);
        int totalPadding = 5 * 3; // left + middle + right
        int buttonWidth = (totalWidth - totalPadding) / 2;

        int cancelBtnX = this.inspectionList.getRight() + 15;
        int saveBtnX = cancelBtnX + buttonWidth + 5;

        this.cancelBtn = addRenderableWidget(
                Button.builder(CANCELBTN, this::onCancelBtnClick)
                        .bounds(cancelBtnX, this.height - 17, buttonWidth, 15)
                        .build()
        );

        this.saveBtn = addRenderableWidget(
                Button.builder(SAVEBTN, this::onSaveBtnClick)
                        .bounds(saveBtnX, this.height - 17, buttonWidth, 15)
                        .build()
        );
    }

    private List<InspectionListItem> updateInspectionListItems(List<InspectionListItemModel> models) {
        this.inspectionList.clearEntries();

        List<InspectionListItem> items = new ArrayList<>();
        for (InspectionListItemModel model : models) {
            InspectionListItem item = new InspectionListItem(this.inspectionList, this::onInspectionListItemClick, model);
            this.inspectionList.addEntry(item);
            items.add(item);
        }

        return items;
    }

}
