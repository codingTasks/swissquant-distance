package com.swissquant.distance;

import com.swissquant.distance.calculator.DistanceCalculator;
import com.swissquant.distance.calculator.EuclideanDistanceCalculator;
import com.swissquant.distance.reader.AllBytesPointsFileReader;
import com.swissquant.distance.reader.PointsFileReader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BestPointsFinderTest {
    private static final int TIMEOUT_SECONDS = 10;
    @Rule
    public Timeout globalTimeout = Timeout.seconds(TIMEOUT_SECONDS);
    private String filePath = "points";
    private PointsFileReader pointsFileReader = new AllBytesPointsFileReader(filePath);
    private DistanceCalculator distanceCalculator = new EuclideanDistanceCalculator();

    @Test
    public void shouldFindTenClosestPoints() throws Exception {
        BestPointsFinder bestPointsFinder = new BestPointsFinder(pointsFileReader, distanceCalculator);
        Point p = new Point(-200, 300);
        int count = 10;
        Collection<Point> closest = bestPointsFinder.getClosest(p, count);

        assertThat(closest.size(), is(count));
        assertThat(closest.containsAll(getExpectedClosestPoints()), is(true));
    }

    @Test
    public void shouldFindTwentyFurthestPoints() throws Exception {
        BestPointsFinder bestPointsFinder = new BestPointsFinder(pointsFileReader, distanceCalculator);
        Point p = new Point(1000, 25);
        int count = 20;
        Collection<Point> furthest = bestPointsFinder.getFurthest(p, count);

        assertThat(furthest.size(), is(count));
        assertThat(furthest.containsAll(getExpectedFurthestPoints()), is(true));
    }

    private Collection<Point> getExpectedClosestPoints() {
        Collection<Point> expected = new ArrayList<>();
        expected.add(new Point(-198, 292));
        expected.add(new Point(-194, 307));
        expected.add(new Point(-191, 285));
        expected.add(new Point(-191, 319));
        expected.add(new Point(-220, 289));
        expected.add(new Point(-224, 317));
        expected.add(new Point(-170, 292));
        expected.add(new Point(-232, 311));
        expected.add(new Point(-190, 334));
        expected.add(new Point(-194, 265));
        return expected;
    }

    private Collection<Point> getExpectedFurthestPoints() {
        Collection<Point> expected = new ArrayList<>();
        expected.add(new Point(-32761, -32735));
        expected.add(new Point(-32764, -32731));
        expected.add(new Point(-32724, -32759));
        expected.add(new Point(-32764, -32706));
        expected.add(new Point(-32761, 32751));
        expected.add(new Point(-32766, 32739));
        expected.add(new Point(-32743, -32710));
        expected.add(new Point(-32715, -32727));
        expected.add(new Point(-32745, 32745));
        expected.add(new Point(-32746, -32691));

        expected.add(new Point(-32671, -32767));
        expected.add(new Point(-32722, -32712));
        expected.add(new Point(-32711, -32723));
        expected.add(new Point(-32764, -32659));
        expected.add(new Point(-32751, -32672));
        expected.add(new Point(-32766, 32704));
        expected.add(new Point(-32719, -32697));
        expected.add(new Point(-32732, 32731));
        expected.add(new Point(-32729, 32734));
        expected.add(new Point(-32681, -32731));

        return expected;
    }
}
