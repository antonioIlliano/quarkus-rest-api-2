package com.worst.error;

import lombok.Data;

@Data
public class FoundException extends Exception{

    int status;

    public FoundException(){}
    public FoundException(int status, String message){
        super(message);
        this.status=status;

    }


}
