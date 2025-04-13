package com.tiredpeanut.debugmenu.gui.widget;

import com.tiredpeanut.debugmenu.annotations.DebugMenuClass;

public record InspectionListItemModel(String title, String description, DebugMenuClass referenceAnnotation) {}
