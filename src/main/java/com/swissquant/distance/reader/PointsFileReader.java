package com.swissquant.distance.reader;

import com.swissquant.distance.Point;

import java.io.IOException;

public interface PointsFileReader {
    Point nextPoint() throws IOException;
    boolean hasPointToRead() throws IOException;
}
