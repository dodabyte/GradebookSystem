package com.example.lab2.converters;

import com.example.lab2.AppManager;
import com.example.lab2.objects.main.Address;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class AddressConverter extends StringConverter<Address> {
    @Override
    public String toString(Address address) {
        if (address == null) return null;
        return address.toString();
    }

    @Override
    public Address fromString(String string) {
        String[] strings = string.split(" ");
        List<String> fieldsName = new ArrayList<>();
        for (String word : strings) {
            if (word.equals("г.") || word.equals("ул.") || word.equals("д.") || word.equals("кв.")) continue;
            fieldsName.add(word);
        }

        Address address = AppManager.getAddressesDao().findByFields(
                fieldsName.get(0),
                fieldsName.get(1),
                fieldsName.get(2),
                Integer.parseInt(fieldsName.get(3))
        );
        if (address == null) {
            address = new Address();
            address.setCity(fieldsName.get(0));
            address.setStreet(fieldsName.get(1));
            address.setHouseNumber(fieldsName.get(2));
            address.setApartmentNumber(Integer.parseInt(fieldsName.get(3)));
        }
        return address;
    }
}