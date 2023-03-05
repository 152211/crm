package com.hworld.utils;

import java.util.HashMap;
import java.util.Map;

public class ErrorUtils {
    public static String printCallStatck() {
        Throwable ex = new Throwable();
        StackTraceElement[] stackElements = ex.getStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> errorMap = new HashMap<>();
        if (stackElements != null) {
            for (int i = 0; i < stackElements.length; i++) {
                stringBuilder.append(stackElements[i].getClassName()).append(";");
                stringBuilder.append(stackElements[i].getFileName()).append(";");
                stringBuilder.append(stackElements[i].getLineNumber()).append(";");
                stringBuilder.append(stackElements[i].getMethodName()).append(".");
            }
        }
        return stringBuilder.toString();
    }

}
