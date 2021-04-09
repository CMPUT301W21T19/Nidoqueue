package com.example.nidoqueue;

import android.widget.Toast;

import com.example.nidoqueue.activity.SignInFragment;
import com.example.nidoqueue.controller.ContextManager;
import com.example.nidoqueue.controller.RequestManager;
import com.example.nidoqueue.model.DatabaseManager;
import com.example.nidoqueue.model.User;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.junit.Test;
/**
 * Classname: 	SignInTest.java
 * Version:		Final
 * Date:		April 9th, 2021
 * Purpose:		Tests the functionality of the Sign In process.
 */
public class SignInTest {
    @Test
    public void testSignInLength(){
        SignInFragment test = new SignInFragment("", "", false);
        /**
         * Manipulate these variables below to test any values you want.
         */
        String small = "1234";
        String large = "12345678901234567891234567891234567899999";
        String normal = "1234567890";
        String[] username = {small, large, normal, normal, normal};
        String[] password = {normal, normal, small, large, normal};

        for(int testNum = 0; testNum < username.length; testNum++){
            displayTestName(testNum);
            test.trySignIn(username[testNum],password[testNum], true);
        }
    }
    public void displayTestName(int num){
        String[] testMessage = {"Test 1: Short Username", "Test 2: Long Username",
                "Test 3: Short Password", "Test 4: Long Password", "Test 5: Good Username & Password"};
        System.out.print("\n"+testMessage[num]+"\n");
    }
}
