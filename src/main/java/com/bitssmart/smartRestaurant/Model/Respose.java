package com.bitssmart.smartRestaurant.Model;

import lombok.Data;

@Data
public class Respose {
    int status;
    String message;
    Object data;

    public Respose(int status,String message)
    {
        this.status = status;
        this.message = message;
    }
}
