package com.example.nidoqueue;

import com.example.nidoqueue.activity.SignInFragment;

import org.junit.Test;
/**
 * Classname: 	SignInTest.java
 * Version:		Final
 * Date:		April 9th, 2021
 * Purpose:		Tests the functionality of the Sign In process.
 */
public class SignInTest {
    @Test
    public void testSignIn(){
        SignInFragment test = new SignInFragment("", "", false);
        String small = "1234";
        String large = "12345678901234567891234567891234567899999";
        String normal = "1234567890";
        String misMatch = "123456790";
        String[] username = {small, large, normal, normal, normal, normal, normal, normal};
        String[] password = {normal, normal, normal, normal, small, large, normal, normal};

        for(int testNum = 0; testNum < username.length; testNum++){
            displayTestName(testNum);
            test.trySignIn(username[testNum],password[testNum], true);
        }
    }
    public void displayTestName(int num){
        String[] testMessage = {"Test 1: Short Username", "Test 2: Long Username", "Test 3: Short Email", "Test 4: Long Email",
                "Test 5: Short Password", "Test 6: Long Password", "Test 7: Password Mismatch", "Test 8: No Errors"};
        System.out.print("\n"+testMessage[num]+"\n");
    }
}
