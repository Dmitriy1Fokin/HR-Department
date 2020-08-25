package ru.fds.hrdepartment.domain.helpertype;

import ru.fds.hrdepartment.common.converter.enums.EnumConverter;

public enum TypeOfVacation {

    ANNUAL_VACATION,
    ADDITIONAL_VACATION;

    public static class Converter extends EnumConverter<TypeOfVacation> {
        public Converter(){
            super(TypeOfVacation.class);
        }
    }
}
