package ru.fds.hrdepartment.domain.helpertype;

import ru.fds.hrdepartment.common.converter.enums.EnumConverter;

public enum TypeOfAttendance {

    AT_WORK,
    ABSENTEEISM,
    ON_SICK_LEAVE,
    ON_VACATION;

    public static class Converter extends EnumConverter<TypeOfAttendance> {
        public Converter(){
            super(TypeOfAttendance.class);
        }
    }
}
