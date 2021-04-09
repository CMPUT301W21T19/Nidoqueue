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
    public void testExperimentLength(){
        ExperimentCreateFragment test = new ExperimentCreateFragment("", "", "", "", "" ,null, null);
        /**
         * Manipulate these variables below to test any values you want.
         */
        String small = "1234";
        String empty = "";
        String large = "12345678901234567891234567891234567899999";
        String massive = "123456789012345678912345678912345678999991234567890123456789123456789123456789999912345678999";
        String normal = "1234567890";
        String[] name = {small, large, normal, normal, normal, normal, normal, normal, normal};
        String[] desc = {normal, normal, empty, massive, normal, normal, normal, normal, normal};
        int[] minTrials = {8,8,8,8,0,21,8,8,8};
        String[] regionInfo = {"Canada", "Canada", "Canada", "Canada", "Canada", "Canada", "None", "Canada", "Canada"};
        String[] typeInfo = {"Count", "Count", "Count", "Count", "Count", "Count", "Count", empty, "Count"};
        Boolean geo[] = {true, false, true, false, true, false, true, false, true};

        for(int testNum = 0; testNum < name.length; testNum++){
            displayTestName(testNum);
            test.tryExperiment(name[testNum], desc[testNum], regionInfo[testNum], minTrials[testNum], typeInfo[testNum], geo[testNum],true);
        }
    }
    public void displayTestName(int num){
        String[] testMessage = {"Test 1: Short Name", "Test 2: Long Name", "Test 3: Empty Description", "Test 4: Long Description",
                "Test 5: Short Trials", "Test 6: Long Trials", "Test 7: No Region Selected", "Test 8: No Type Selected", "Test 9: No Errors"};
        System.out.print("\n"+testMessage[num]+"\n");
    }
}
