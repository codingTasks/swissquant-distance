package com.swissquant.distance;

import com.aliasi.util.BoundedPriorityQueue;
import com.swissquant.distance.calculator.DistanceCalculator;
import com.swissquant.distance.reader.PointsFileReader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;

public class BestPointsFinder {
    private static final Logger LOGGER = Logger.getLogger(BestPointsFinder.class);

    private PointsFileReader pointsFileReader;
    private DistanceCalculator distanceCalculator;

    public BestPointsFinder(PointsFileReader pointsFileReader, DistanceCalculator distanceCalculator) {
        this.pointsFileReader = pointsFileReader;
        this.distanceCalculator = distanceCalculator;
    }

    public Collection<Point> getClosest(Point referencePoint, int count) throws IOException {
        LOGGER.debug(String.format("[CLOSEST INVOKED] %s, count: %d", referencePoint, count));
        return getPoints(referencePoint, count, new Point.DescendingComparator());
    }

    public Collection<Point> getFurthest(Point referencePoint, int count) throws IOException {
        LOGGER.debug(String.format("[FURTHEST INVOKED] %s, count: %d", referencePoint, count));
        return getPoints(referencePoint, count, new Point.AscendingComparator());
    }

    private Collection<Point> getPoints(Point referencePoint, int count, Comparator<Point> comparator) throws IOException {
        BoundedPriorityQueue<Point> queue = new BoundedPriorityQueue<>(comparator, count);

        while (pointsFileReader.hasPointToRead()) {
            Point nextPoint = pointsFileReader.nextPoint();
            nextPoint.setDistance(distanceCalculator.calculate(nextPoint, referencePoint));
            queue.offer(nextPoint);
        }

        LOGGER.debug(String.format("[POINTS TO RETURN] %s", queue));
        return queue;
    }
}
