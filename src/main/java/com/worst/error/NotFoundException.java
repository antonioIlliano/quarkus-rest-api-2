package com.worst.error;

import lombok.Data;

@Data
public class NotFoundException extends Exception {

    int status;

    public NotFoundException(){}

    public NotFoundException(int status, String message){
        super(message);
        this.status = status;
    }

}
