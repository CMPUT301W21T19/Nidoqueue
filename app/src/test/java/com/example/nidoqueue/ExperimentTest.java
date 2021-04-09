package com.example.nidoqueue;

import com.example.nidoqueue.activity.ExperimentCreateFragment;

import org.junit.Test;
/**
 * Classname: 	ExperimentTest.java
 * Version:		Final
 * Date:		April 9th, 2021
 * Purpose:		Tests the functionality of the Experiment process.
 */
public class ExperimentTest {
    @Test
    public void testExperiment(){
        ExperimentCreateFragment test = new ExperimentCreateFragment("", "", "", "", "" ,null, null);
        String[] name = {""};

        for(int testNum = 0; testNum < name.length; testNum++){
            displayTestName(testNum);
            //test.trySignIn(username[testNum],password[testNum], true);
        }
    }
    public void displayTestName(int num){
        String[] testMessage = {"Test 1: Short Username", "Test 2: Long Username", "Test 3: Short Email", "Test 4: Long Email",
                "Test 5: Short Password", "Test 6: Long Password", "Test 7: Password Mismatch", "Test 8: No Errors"};
        System.out.print("\n"+testMessage[num]+"\n");
    }
}
