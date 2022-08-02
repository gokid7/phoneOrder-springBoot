package com.vDeneme.vdeneme.errorHandling;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorObject {
    private Date timeStamp;
    private String message;
    private String details;

    public ErrorObject(Date timeStamp,String message, String details){
        super();
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

}
