package com.example.nidoqueue;

import com.example.nidoqueue.model.ExpCount;
import com.example.nidoqueue.model.Experiment;
import com.example.nidoqueue.model.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        Experiment test = new ExpCount(new User("June", "@gmail.com", "0000000"), "Testing", "testing now", true);
        assertEquals("count", test.getType());
    }
}