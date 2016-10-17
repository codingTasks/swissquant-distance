package com.swissquant.distance;

import com.swissquant.distance.calculator.DistanceCalculator;
import com.swissquant.distance.calculator.EuclideanDistanceCalculator;
import com.swissquant.distance.reader.AllBytesPointsFileReader;
import com.swissquant.distance.reader.PointsFileReader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Collection;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static final String filePath = "points";
    private static final PointsFileReader reader = new AllBytesPointsFileReader(filePath);
    private static final DistanceCalculator calc = new EuclideanDistanceCalculator();

    public static void main(String[] args) {
        BestPointsFinder bestPointsFinder = new BestPointsFinder(reader, calc);
        Point point = new Point(0, 0);

        try {
            Collection<Point> closest = bestPointsFinder.getClosest(point, 10);
            LOGGER.info(closest);

            point = new Point(999999999, 99999999);
            Collection<Point> furthest = bestPointsFinder.getFurthest(point, 10000);
            LOGGER.info(furthest);
        } catch (IOException e) {
            LOGGER.warn(e);
        }
    }
}
