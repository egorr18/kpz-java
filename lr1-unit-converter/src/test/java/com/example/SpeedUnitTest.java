package com.example;

import static com.example.SpeedUnit.KILOMETERS_PER_HOUR;
import static com.example.SpeedUnit.METERS_PER_SECOND;
import static com.example.SpeedUnit.MILES_PER_HOUR;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpeedUnitTest {

    @ParameterizedTest(name = "{0} {1} -> {2} = {3}")
    @CsvSource({
            "1,      METERS_PER_SECOND,   METERS_PER_SECOND,   1",
            "1,      METERS_PER_SECOND,   KILOMETERS_PER_HOUR, 3.6",
            "1,      METERS_PER_SECOND,   MILES_PER_HOUR,      2.2369363",
            "100,    KILOMETERS_PER_HOUR, METERS_PER_SECOND,   27.7777778",
            "100,    KILOMETERS_PER_HOUR, KILOMETERS_PER_HOUR, 100",
            "100,    KILOMETERS_PER_HOUR, MILES_PER_HOUR,      62.1371192",
            "60,     MILES_PER_HOUR,      METERS_PER_SECOND,   26.8224",
            "60,     MILES_PER_HOUR,      KILOMETERS_PER_HOUR, 96.56064",
            "60,     MILES_PER_HOUR,      MILES_PER_HOUR,      60"
    })
    void convertsBetweenAllUnits(double value, SpeedUnit from, SpeedUnit to, double expected) {
        assertEquals(expected, from.convert(value, to), 1e-6);
    }

    @ParameterizedTest
    @CsvSource({"0", "12.5", "300"})
    void roundTripReturnsOriginalValue(double value) {
        double there = MILES_PER_HOUR.convert(KILOMETERS_PER_HOUR.convert(value, MILES_PER_HOUR), KILOMETERS_PER_HOUR);
        assertEquals(value, there, 1e-9);
        double back = MILES_PER_HOUR.convert(METERS_PER_SECOND.convert(value, MILES_PER_HOUR), METERS_PER_SECOND);
        assertEquals(value, back, 1e-9);
    }
}
