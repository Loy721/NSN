package com.example.nsn.execption;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
     super(message);
    }
}
