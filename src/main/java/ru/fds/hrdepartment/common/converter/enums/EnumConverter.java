package ru.fds.hrdepartment.common.converter.enums;

import javax.persistence.AttributeConverter;

public abstract class EnumConverter<T extends Enum<T>> implements AttributeConverter<T, String> {
    private final Class<T> clazz;

    public EnumConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {

        if(attribute == null){
            return null;
        }
        return attribute.name();
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }

        T[] enums = clazz.getEnumConstants();

        for (T e : enums) {
            if (e.name().equals(dbData)) {
                return e;
            }
        }

        throw new UnsupportedOperationException();
    }
}