package ru.fds.hrdepartment.domain.helpertype;

import ru.fds.hrdepartment.common.converter.enums.EnumConverter;

public enum TypeOfSickLeave {

    BASIC,
    FAMILY_MEMBER,
    CHILD;

    public static class Converter extends EnumConverter<TypeOfSickLeave> {
        public Converter(){
            super(TypeOfSickLeave.class);
        }
    }
}
