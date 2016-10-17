package com.swissquant.distance.reader;

import com.swissquant.distance.Point;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AllBytesPointsFileReaderTest {
    private static final String FILE_PATH = "points";

    @Test
    public void shouldBeAbleToReadPointByPoint() throws Exception {
        AllBytesPointsFileReader pointsFileReader = new AllBytesPointsFileReader(FILE_PATH);

        Point point = null;

        if(pointsFileReader.hasPointToRead()) {
            point = pointsFileReader.nextPoint();
        }

        assertThat(point.getX(), is(-715));
        assertThat(point.getY(), is(22165));

        point = pointsFileReader.nextPoint();
        assertThat(point.getX(), is(761));
        assertThat(point.getY(), is(-23591));
}
}