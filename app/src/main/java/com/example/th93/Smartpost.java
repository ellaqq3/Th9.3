package com.example.th93;

public class Smartpost {

    String name;
    String location;
    String address;
    String availability;

    public Smartpost(String a, String b, String c, String d) {
        name = a;
        location = b;
        address = c;
        availability = d;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

    public String getAvailability() {
        return availability;
    }
}
