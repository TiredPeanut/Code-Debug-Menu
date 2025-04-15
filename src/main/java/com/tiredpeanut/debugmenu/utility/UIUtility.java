package com.tiredpeanut.debugmenu.utility;

import net.minecraft.client.gui.Font;

public class UIUtility {

    public static String truncateTextToFit(Font font, String text, int maxWidth) {
        if (font.width(text) <= maxWidth) {
            return text; // No need to truncate
        }

        String ellipsis = "...";
        int ellipsisWidth = font.width(ellipsis);

        // Start trimming characters until it fits
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            builder.append(text.charAt(i));
            if (font.width(builder.toString()) + ellipsisWidth >= maxWidth) {
                break;
            }
        }

        return builder.toString().trim() + ellipsis;
    }
}
