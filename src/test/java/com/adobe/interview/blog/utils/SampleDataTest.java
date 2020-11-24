package com.adobe.interview.blog.utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class SampleDataTest {

    @Test
    public void sampleDataGenerated(){
        Assert.assertTrue(SampleData.getSampleData().length()>0);
    }
}
