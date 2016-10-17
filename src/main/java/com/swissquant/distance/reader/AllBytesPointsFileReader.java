package com.swissquant.distance.reader;

import com.swissquant.distance.Point;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

public class AllBytesPointsFileReader implements PointsFileReader {
    private static final Logger LOGGER = Logger.getLogger(AllBytesPointsFileReader.class);
    private static final int BYTES_IN_INT = 2;
    private int currentPosition = 0;
    private byte[] bytes;
    private String filePath;

    public AllBytesPointsFileReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Point nextPoint() {
        return new Point((int) getNextCoordinate(), (int) getNextCoordinate());
    }

    @Override
    public boolean hasPointToRead() throws IOException {
        if (bytes == null) {
            LOGGER.debug(String.format("[READ FILE] %s", filePath));
            bytes = Files.readAllBytes(FileSystems.getDefault().getPath(filePath));
        }

        boolean hasPoint = currentPosition <= bytes.length - 2 * BYTES_IN_INT;

        if (!hasPoint)
            currentPosition = 0;

        return hasPoint;
    }

    private short getNextCoordinate() {
        short coordinate = (short) (((bytes[currentPosition] & 0xFF) << 8) | (bytes[currentPosition + 1] & 0xFF));
        currentPosition += BYTES_IN_INT;

        return coordinate;
    }
}
