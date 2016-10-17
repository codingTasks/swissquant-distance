package com.swissquant.distance;

import java.util.Comparator;

public class Point {
    private final int x;
    private final int y;
    private double distance;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public int getY() {
        return y;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    static class DescendingComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            return Double.compare(p2.getDistance(), p1.getDistance());
        }
    }

    static class AscendingComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            return Double.compare(p1.getDistance(), p2.getDistance());
        }
    }
}
