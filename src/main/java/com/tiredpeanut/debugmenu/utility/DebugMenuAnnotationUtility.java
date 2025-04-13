package com.tiredpeanut.debugmenu.utility;

import com.tiredpeanut.debugmenu.annotations.DebugMenuClass;
import com.tiredpeanut.debugmenu.gui.widget.InspectionListItem;
import com.tiredpeanut.debugmenu.gui.widget.InspectionListItemModel;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.ModFileScanData;
import java.util.*;

public class DebugMenuAnnotationUtility {
    private static final HashSet<String> ClassNamesToInspect = new HashSet<String>();

    static {
        String annotationName = "com.tiredpeanut.debugmenu.annotations.DebugMenuClass";
        ClassNamesToInspect.addAll(getClassNamesFromAnnotationName(annotationName));
    }

    private static List<String> getClassNamesFromAnnotationName(String annotationName) {

        List<String> classNameList = new ArrayList<>();

        ModList.get().getAllScanData().forEach(scanData -> {
            for (ModFileScanData.AnnotationData annotation : scanData.getAnnotations()) {
                if (annotation.annotationType().getClassName().equals(annotationName)) {
                    String className = annotation.clazz().getClassName();
                    classNameList.add(className);
                }
            }
        });

        return classNameList;
    }

    private static List<Class<?>> getClassesFromClassNames() {
        List<Class<?>> classList = new ArrayList<>();
        for(String className : ClassNamesToInspect) {
            try {
                classList.add(Class.forName(className));
            }
            catch (ClassNotFoundException ignored) {
            }
        }
        return classList;
    }

    private static List<DebugMenuClass> getDebugMenuClassAnnotations() {
        List<DebugMenuClass> debugMenuAttributes = new ArrayList<>();

        List<Class<?>> classes =  getClassesFromClassNames();
        for(Class<?> classToInspect : classes) {
            debugMenuAttributes.add(classToInspect.getAnnotation(DebugMenuClass.class));
        }
        return debugMenuAttributes;
    }

    public static List<InspectionListItemModel> getInspectionListItemModels() {

        List<InspectionListItemModel> inspectionListItems = new ArrayList<>();

        List<DebugMenuClass> debugMenuAttributes = getDebugMenuClassAnnotations();
        for(DebugMenuClass attribute : debugMenuAttributes) {
            InspectionListItemModel item = new InspectionListItemModel(attribute.title(), attribute.description(), attribute);
            inspectionListItems.add(item);
        }

        return inspectionListItems;
    }
}
