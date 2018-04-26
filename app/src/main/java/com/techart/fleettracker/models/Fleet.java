package com.techart.fleettracker.models;

/**
 * Object for a Fleet
 * Created by Kelvin on 05/06/2017.
 */

public class Fleet {
    private String plateNumber;
    private String driver;
    private String trailor;
    private String gps;
    private Double v;
    private Double v2;
    private String status;
    private Long lastOnLine;
    private String lastLocation;
    private String from;
    private String to;
    private Long amountPaid;
    private String rate;
    private Long timeCreated;


    public Fleet() {

    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getLastOnLine() {
        return lastOnLine;
    }

    public void setLastOnLine(Long lastOnLine) {
        this.lastOnLine = lastOnLine;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Long timeCreated) {
        this.timeCreated = timeCreated;
    }


    public String getTrailor() {
        return trailor;
    }

    public void setTrailor(String trailor) {
        this.trailor = trailor;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }


    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Long getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Long amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Double getV() {
        return v;
    }

    public void setV(Double v) {
        this.v = v;
    }

    public Double getV2() {
        return v2;
    }

    public void setV2(Double v2) {
        this.v2 = v2;
    }
}
