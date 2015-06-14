package com.hg;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hg on 6/2/2015.
 */
public class CountInversionTest {

    @Test
    public void testCountInversion() throws Exception {
        Integer [] input = {15, 1, 0, 30, 7,9,47,4,3,26,6,7, 98};
        int result = CountInversion.count(input);
    }
}