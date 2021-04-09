package com.example.nidoqueue;

import com.example.nidoqueue.activity.SearchFragment;
import com.example.nidoqueue.activity.SignUpFragment;

import org.junit.Test;
/**
 * Classname: 	SearchTest.java
 * Version:		Final
 * Date:		April 9th, 2021
 * Purpose:		Tests the functionality of the Search process.
 */
public class SearchTest {
    @Test
    public void testSearchLength(){
        SearchFragment test = new SearchFragment("", false);
        /**
         * Manipulate these variables below to test any values you want.
         */
        String small = "";
        String large = "12345678901234567891234567891234567899999";
        String normal = "1234567890";
        String[] search = {small, large, normal};

        for(int testNum = 0; testNum < search.length; testNum++){
            displayTestName(testNum);
            test.trySearch(search[testNum], true);
        }
    }
    public void displayTestName(int num){
        String[] testMessage = {"Test 1: Short Search", "Test 2: Long Search", "Test 3: Good Search"};
        System.out.print("\n"+testMessage[num]+"\n");
    }
}
