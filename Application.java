package com.anmol.jobtracker;

import java.io.Serializable;

public class Application implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int id;
    private final String company;
    private final String role;
    private final String location;
    private final String appliedDate;
    private String status;

    public Application(int id, String company, String role, String location, String appliedDate, String status) {
        this.id = id;
        this.company = company;
        this.role = role;
        this.location = location;
        this.appliedDate = appliedDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%-4d %-22s %-24s %-16s %-14s %s", id, company, role, location, appliedDate, status);
    }
}
