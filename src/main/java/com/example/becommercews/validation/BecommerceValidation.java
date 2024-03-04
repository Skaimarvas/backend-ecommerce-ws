package com.example.becommercews.validation;

import com.example.becommercews.entity.User;
import com.example.becommercews.exception.BecommerceException;
import org.springframework.http.HttpStatus;

public class BecommerceValidation {

    public static final String MUST_NOT_BE_NULL_EMPTY = "Must not be null or empty";
    public static final String MUST_BE_BIGGER_THAN_ZERO = "Must be bigger than zer";
    public static final String ENTER_VALID_CATEGORY_ID = "Must be valid category id";
    public static final String IS_EMAIL_PRESENT = "Email is already taken!";
    public static final String IS_USER_PRESENT = "User is not found!";
    public static final String IS_USER_VALID = "User is not valid!";
    public static  final String IS_PRODUCT_PRESENT="The product with given name is already exist";
    public static final String IS_NOT_PRODUCT_PRESENT="The product is not found!";
    public static final String IS_NOT_PRESENT_ADDRESS="The address is not found!";

    public static void checkEmptyOrNull(String value, String field) {
        if (value == null || value.isEmpty())
            throw new BecommerceException(field + MUST_NOT_BE_NULL_EMPTY, HttpStatus.BAD_REQUEST);
    }
    public static void isValid(double value, String field) {
        if (value < 0) throw new BecommerceException(field + MUST_BE_BIGGER_THAN_ZERO, HttpStatus.BAD_REQUEST);
    }
    public static void isCategoryIdValid(String value, long id){
        if (id < 0 || id > 14 ) throw new BecommerceException(value + ENTER_VALID_CATEGORY_ID + id, HttpStatus.BAD_REQUEST);
    }

    public static void isUserPresent(User user){
        if(user == null){
            throw new BecommerceException(IS_USER_PRESENT, HttpStatus.NOT_FOUND);
        }
    }
}
