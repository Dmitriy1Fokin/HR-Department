package ru.fds.hrdepartment.domain.helpertype;

import ru.fds.hrdepartment.common.converter.enums.EnumConverter;

public enum TypeOfVacationSick {
    ON_SICK_LEAVE,
    ANNUAL_VACATION,
    ADDITIONAL_VACATION;

    public static class Converter extends EnumConverter<TypeOfVacationSick> {
        public Converter(){
            super(TypeOfVacationSick.class);
        }
    }
}
