package com.example.lab2.predicates;

import com.example.lab2.objects.global.CustomObject;

import java.util.function.Predicate;

public class CustomPredicate<T extends CustomObject> {
    public Predicate<T> createPredicate(String searchText) {
        return predicate -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return predicate.toStringFields().toLowerCase().contains(searchText.toLowerCase());
        };
    }
}
