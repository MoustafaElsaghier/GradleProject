package com.example.engahmed.fun;

import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by ELSaghier on 1/9/2018.
 */
@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    private EndpointsAsyncTask task;

    @Before
    public void setUp() throws Exception {
        task = new EndpointsAsyncTask();
    }

    @Test
    public void runCloudModuleTest() {
        String joke = null;
        try {
            joke = task.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertNotNull(joke);
    }
}
