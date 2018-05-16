package com.example.a447224.weathersample.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {
    @Test
    public void testKelvinToCelciusConversionSuccess(){
        String actual = Utils.convertKelvinToDegree(310.0);
        String expected = "37°C";
        assertEquals(expected,actual);
    }
    @Test
    public void testKelvinToCelciusConversionFailure(){
        String actual = Utils.convertKelvinToDegree(310.0);
        String expected = "38°C";
        assertNotEquals(expected,actual);
    }
    @Test
    public void testDateConversionSuccess(){
        String actual = Utils.convertTimeInMilliSecsTODateTime(1526467229340L);
        String expected = "Wed, 16 May";
        assertEquals(expected,actual);
    }
    @Test
    public void testDateConversionFailure(){
        String actual = Utils.convertTimeInMilliSecsTODateTime(1526467229340L);
        String expected = "Thu, 17 May";
        assertNotEquals(expected,actual);
    }

}