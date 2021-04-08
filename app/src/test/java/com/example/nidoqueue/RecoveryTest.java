package com.example.nidoqueue;

import com.example.nidoqueue.activity.RecoveryFragment;
import com.example.nidoqueue.activity.SignUpFragment;

import org.junit.Test;
/**
 * Incomplete - April 7th, Colin
 */
public class RecoveryTest {
    @Test
    public void testRecovery(){
        RecoveryFragment test = new RecoveryFragment("", false);
        String small = "1234";
        String large = "12345678901234567891234567891234567899999";
        String normal = "1234567890";
        String[] email = {normal, normal, small, large, normal, normal, normal, normal};

        for(int testNum = 0; testNum < email.length; testNum++){
            displayTestName(testNum);
            test.tryRecovery(email[testNum],true);
        }
    }
    public void displayTestName(int num){
        String[] testMessage = {"Test 1: Short Username", "Test 2: Long Username", "Test 3: Short Email", "Test 4: Long Email",
                "Test 5: Short Password", "Test 6: Long Password", "Test 7: Password Mismatch", "Test 8: No Errors"};
        System.out.print("\n"+testMessage[num]+"\n");
    }
}
