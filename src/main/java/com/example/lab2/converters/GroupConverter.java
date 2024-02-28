package com.example.lab2.converters;

import com.example.lab2.AppManager;
import com.example.lab2.objects.main.Group;
import javafx.util.StringConverter;

public class GroupConverter extends StringConverter<Group> {
    @Override
    public String toString(Group group) {
        if (group == null) return null;
        return group.toString();
    }

    @Override
    public Group fromString(String string) {
        return AppManager.getGroupsDao().findByField("name", string).get(0);
    }
}
