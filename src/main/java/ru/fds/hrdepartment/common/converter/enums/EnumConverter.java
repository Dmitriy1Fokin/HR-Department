package ru.fds.hrdepartment.common.converter.enums;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

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

        return Stream.of(enums)
                .filter(t -> t.name().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}