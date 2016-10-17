package com.swissquant.distance.calculator;

import com.swissquant.distance.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DistanceCalculatorTest {
    @Test
    public void test() throws Exception {
        DistanceCalculator distanceCalculator = new EuclideanDistanceCalculator();

        Point point1 = new Point(0, 0);
        Point point2 = new Point(2, 2);
        double distance = distanceCalculator.calculate(point1, point2);

        assertEquals(2.828427, distance, 0.001);
    }
}
