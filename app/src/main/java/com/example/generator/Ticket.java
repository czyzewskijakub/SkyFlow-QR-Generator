package com.example.generator;

public class Ticket {
    private String name;
    private String surname;
    private String id;
    private String start;
    private String end;
    private String date;
    private String seat;
    private String flightId;

    public Ticket() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }



    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String listData() {
        return start + "-" + end + " seat: " + seat;
    }

    @Override
    public String toString() {
        return "name : " + name +'\n' +
                "surname : " + surname +'\n' +
                "id : " + id +
                "flightId: " + flightId;
    }
}
