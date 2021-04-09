package com.example.nidoqueue;

import com.example.nidoqueue.activity.RecoveryFragment;
import com.example.nidoqueue.activity.SignUpFragment;

import org.junit.Test;
/**
 * Classname: 	RecoveryTest.java
 * Version:		Final
 * Date:		April 9th, 2021
 * Purpose:		Tests the functionality of the Account Recovery process.
 */
public class RecoveryTest {
    @Test
    public void testRecoveryLength(){
        RecoveryFragment test = new RecoveryFragment("", false);
        /**
         * Manipulate these variables below to test any values you want.
         */
        String small = "1234";
        String large = "12345678901234567891234567891234567899999";
        String normal = "1234567890";
        String[] email = {small, large, normal};

        for(int testNum = 0; testNum < email.length; testNum++){
            displayTestName(testNum);
            test.tryRecovery(email[testNum],true);
        }
    }
    public void displayTestName(int num){
        String[] testMessage = {"Test 1: Short Email", "Test 2: Long Email", "Test 3: Good Email"};
        System.out.print("\n"+testMessage[num]+"\n");
    }
}
