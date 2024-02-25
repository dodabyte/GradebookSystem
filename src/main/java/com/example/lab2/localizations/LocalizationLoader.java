package com.example.lab2.localizations;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationLoader {
    public static final String RU_BUNDLE_NAME = "localizations/ru_ru";
    private final String language;
    private static ResourceBundle messages;

    public LocalizationLoader(String localeBundleName) {
        messages = ResourceBundle.getBundle(localeBundleName, Locale.getDefault());
        language = localeBundleName.split("/")[1];
    }

    public String getMessage(String key) {
        return messages.getString(key);
    }

    public String getLanguage() {
        return language;
    }
}
