package com.tiredpeanut.debugmenu.utility;

import net.minecraft.client.gui.Font;

public class UIUtility {

    public static String truncateTextToFit(Font font, String text, int maxWidth) {
        if (font.width(text) <= maxWidth) return text;

        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (font.width(sb.toString() + c + "...") > maxWidth) {
                break;
            }
            sb.append(c);
        }
        return sb.toString() + "...";
    }
}
