package com.magistrate.labs.models;

import com.magistrate.labs.controllers.Parser;

public class PassengerTransport implements Transport {
    private int car_type;
    private int car_number;
    private int car_mileage;
    private int car_additional_info;
    Parser car_parser;

    public PassengerTransport(Parser entry) { car_parser = entry; }

    public void fill() {
        car_type = Integer.parseInt(car_parser.get_type());
        car_number = Integer.parseInt(car_parser.get_number());
        car_mileage = Integer.parseInt(car_parser.get_mileage());
        car_additional_info = Integer.parseInt(car_parser.get_additional_info());
    }

    public double count_amortization() {
        return 47.50 * (car_mileage * (11.5 / 100.));
    }

    public int getCar_number() {
        return car_number;
    }

    public int getCar_additional_info() {
        return car_additional_info;
    }

    public int getCar_mileage() {
        return car_mileage;
    }

    public int getCar_type() {
        return car_type;
    }
}
