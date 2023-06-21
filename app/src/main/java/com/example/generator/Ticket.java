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
    private String ticketId;

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

    public void setDate(String date) {
        this.date = date;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }


    public String listData() {
        return start + "-" + end + " seat:" + seat;
    }

    public static TicketBuilder builder = new TicketBuilder();

    public static final class TicketBuilder {
        private String name;
        private String surname;
        private String id;
        private String start;
        private String end;
        private String date;
        private String seat;
        private String flightId;
        private String ticketId;


       public Ticket build() {
           Ticket ticket = new Ticket();
           ticket.name = name;
           ticket.surname = surname;
           ticket.id = id;
           ticket.start = start;
           ticket.end = end;
           ticket.date = date;
           ticket.seat = seat;
           ticket.flightId = flightId;
           ticket.ticketId = ticketId;
           return ticket;
       }



        public TicketBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public TicketBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public TicketBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public TicketBuilder setStart(String start) {
            this.start = start;
            return this;
        }

        public TicketBuilder setEnd(String end) {
            this.end = end;
            return this;
        }

        public TicketBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public TicketBuilder setSeat(String seat) {
            this.seat = seat;
            return this;
        }

        public TicketBuilder setFlightId(String flightId) {
            this.flightId = flightId;
            return this;
        }

        public TicketBuilder setTicketId(String ticketId) {
            this.ticketId = ticketId;
            return this;
        }
    }


    @Override
    public String toString() {
        return "name : " + name +'\n' +
                "surname : " + surname +'\n' +
                "user_id : " + id + '\n' +
                "departure_airport: " + start + '\n' +
                "arrival_airport: " + end + '\n' +
                "departure date: " + date + '\n' +
                "ticket_id: " + ticketId + '\n' +
                "flight_id: " + flightId + '\n';
    }
}
