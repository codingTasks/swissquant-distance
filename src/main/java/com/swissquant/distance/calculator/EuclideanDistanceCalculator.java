package com.swissquant.distance.calculator;

import com.swissquant.distance.Point;

public class EuclideanDistanceCalculator implements DistanceCalculator {
    @Override
    public double calculate(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) + Math.pow(point2.getY() - point1.getY(), 2));
    }
}
