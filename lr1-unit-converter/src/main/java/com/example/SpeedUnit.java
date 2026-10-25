package com.example;

/**
 * Одиниці швидкості (варіант 5). Кожна одиниця знає свій множник до базової одиниці m/s,
 * тож будь-яку пару можна перевести через базову: value → m/s → target.
 */
public enum SpeedUnit {
    METERS_PER_SECOND("m/s", 1.0),
    KILOMETERS_PER_HOUR("km/h", 1000.0 / 3600.0),
    MILES_PER_HOUR("mph", 1609.344 / 3600.0);

    private final String symbol;
    private final double toMetersPerSecond;

    SpeedUnit(String symbol, double toMetersPerSecond) {
        this.symbol = symbol;
        this.toMetersPerSecond = toMetersPerSecond;
    }

    public String symbol() {
        return symbol;
    }

    public double convert(double value, SpeedUnit target) {
        double metersPerSecond = value * toMetersPerSecond;
        return metersPerSecond / target.toMetersPerSecond;
    }
}
