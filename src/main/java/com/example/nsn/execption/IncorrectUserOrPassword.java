package com.example.nsn.execption;

public class IncorrectUserOrPassword  extends RuntimeException{
    public IncorrectUserOrPassword(String message, Exception e){
        super(message);
    }
}
