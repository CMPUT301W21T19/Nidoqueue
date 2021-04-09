package com.example.nidoqueue;

import com.example.nidoqueue.activity.SignUpFragment;


import org.junit.Test;
/**
 * Classname: 	SignUpTest.java
 * Version:		Final
 * Date:		April 9th, 2021
 * Purpose:		Tests the functionality of the Sign Up process.
 */
public class SignUpTest {
    @Test
    public void testSignUpLength(){
        SignUpFragment test = new SignUpFragment("", "", "", "", false);
        /**
         * Manipulate these variables below to test any values you want.
         */
        String small = "1234";
        String large = "12345678901234567891234567891234567899999";
        String normal = "1234567890";
        String misMatch = "123456790";
        String[] username = {small, large, normal, normal, normal, normal, normal, normal};
        String[] email = {normal, normal, small, large, normal, normal, normal, normal};
        String[] password = {normal, normal, normal, normal, small, large, normal, normal};
        String[] passwordRe = {normal, normal, normal, normal, normal, normal, misMatch, normal};

        for(int testNum = 0; testNum < username.length; testNum++){
            displayTestName(testNum);
            test.trySignUp(username[testNum], email[testNum],password[testNum],passwordRe[testNum], true);
        }
    }
    public void displayTestName(int num){
        String[] testMessage = {"Test 1: Short Username", "Test 2: Long Username", "Test 3: Short Email", "Test 4: Long Email",
                "Test 5: Short Password", "Test 6: Long Password", "Test 7: Password Mismatch", "Test 8: No Errors"};
        System.out.print("\n"+testMessage[num]+"\n");
    }
}
