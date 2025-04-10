package com.tiredpeanut.debugmenu.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DebugMenuMethod {
    String methodDescription();
}
