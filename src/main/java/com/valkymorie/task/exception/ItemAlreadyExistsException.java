package com.valkymorie.task.exception;

public class ItemAlreadyExistsException extends RuntimeException{

    public ItemAlreadyExistsException(String mesage){
        super(mesage);
    }
}
