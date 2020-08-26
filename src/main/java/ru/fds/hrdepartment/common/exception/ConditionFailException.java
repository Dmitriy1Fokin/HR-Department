package ru.fds.hrdepartment.common.exception;

public class ConditionFailException extends RuntimeException {
    public ConditionFailException(String message){
        super(message);
    }
}
