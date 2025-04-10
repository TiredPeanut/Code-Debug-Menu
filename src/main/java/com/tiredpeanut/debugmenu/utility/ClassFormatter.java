package com.tiredpeanut.debugmenu.utility;

import com.tiredpeanut.debugmenu.annotations.DebugMenuClass;
import com.tiredpeanut.debugmenu.annotations.DebugMenuMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

public class ClassFormatter {

    public static String formatClass(Class<?> targetClass) {
        DebugMenuClass debugMenuClassAno = targetClass.getAnnotation(DebugMenuClass.class);
        if(debugMenuClassAno == null) return null;

        StringBuilder sb = new StringBuilder();

        // Annotations
        for (Annotation annotation : targetClass.getAnnotations()) {
            sb.append("@").append(annotation.annotationType().getSimpleName()).append("\n");
        }

        // Class declaration
        int modifiers = targetClass.getModifiers();
        sb.append(Modifier.toString(modifiers)).append(" class ").append(targetClass.getSimpleName()).append(" {\n\n");

        // Do we want all fields or only ones annotated with DebugMenuMethod
        List<Field> fields = debugMenuClassAno.displayOnlyAnnotated()
                ? Arrays.stream(targetClass.getDeclaredFields()).filter(method -> method.isAnnotationPresent(DebugMenuMethod.class)).toList()
                : Arrays.stream(targetClass.getDeclaredFields()).toList();

        for (Field field : fields) {
            sb.append("    ")
                    .append(Modifier.toString(field.getModifiers())).append(" ")
                    .append(field.getType().getSimpleName()).append(" ")
                    .append(field.getName()).append(";\n");
        }

        sb.append("\n");

        // Methods
        //Am I missing something or does this syntax just suck
        //Oh I see extension methods are not a thing
        //But default interface methods are kinda the same...
        //Thats what the toList is doing -- kinda gross
        //Static classes are not a thing
        //Wait but nested static classes are a thing
        //Thats enough of that rabbit hole
        List<Method> methods = debugMenuClassAno.displayOnlyAnnotated()
                ? Arrays.stream(targetClass.getDeclaredMethods()).filter(method -> method.isAnnotationPresent(DebugMenuMethod.class)).toList()
                : Arrays.stream(targetClass.getDeclaredMethods()).toList();

        for (Method method : methods) {
            sb.append("    ")
                    .append(Modifier.toString(method.getModifiers())).append(" ")
                    .append(method.getReturnType().getSimpleName()).append(" ")
                    .append(method.getName())
                    .append("(");

            // Parameters
            Parameter[] params = method.getParameters();
            for (int i = 0; i < params.length; i++) {
                sb.append(params[i].getType().getSimpleName())
                        .append(" ").append(params[i].getName());
                if (i < params.length - 1) {
                    sb.append(", ");
                }
            }

            sb.append(") {}\n");
        }

        sb.append("}\n");
        return sb.toString();
    }
}