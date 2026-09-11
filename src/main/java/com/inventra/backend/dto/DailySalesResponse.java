package com.inventra.backend.dto;

import java.time.LocalDate;

public class DailySalesResponse {

    private LocalDate date;
    private long unitsSold;

    public DailySalesResponse(LocalDate date, long unitsSold) {
        this.date = date;
        this.unitsSold = unitsSold;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getUnitsSold() {
        return unitsSold;
    }
}