package com.magistrate.labs.controllers;

import com.magistrate.labs.models.Transport;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Visualizer {
    private final ArrayList<Transport> sortable_data;
    private final Integer car_type;

    public Visualizer(Integer type, ArrayList<Transport> cars_data) {
        car_type = type;
        sortable_data = cars_data;
    }

    private ArrayList<Transport> sort_data_by_mileage() {
        return sortable_data
                .stream()
                .filter(transport -> transport.getCar_type() == car_type)
                .sorted(Comparator.comparingInt(Transport::getCar_mileage))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Transport> sort_data_by_add_info() {
        return sortable_data
                .stream()
                .filter(transport -> transport.getCar_type() == car_type)
                .sorted(Comparator.comparingInt(Transport::getCar_additional_info))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void custom_print() {
        if (car_type == 100) {
            System.out.println("Sorted by mileage");
            sorted_by_mileage_light_cars_print();
        } else {
            System.out.println("Sorted by mileage");
            sorted_by_mileage_print();
            System.out.println("------------------------------------------------------");
            System.out.println("Sorted by additional info");
            sorted_by_additional_info_print();
        }
        System.out.println("------------------------------------------------------");
    }

    private void sorted_by_mileage_light_cars_print() {
        ArrayList<Transport> cars_sorted = sort_data_by_mileage();
        for (Transport car : cars_sorted) {
            System.out.printf("Car type: %d, car number: %d, car mileage: %d \n", car.getCar_type(),
                    car.getCar_number(), car.getCar_mileage());
        }
    }

    private void sorted_by_mileage_print() {
        ArrayList<Transport> freight_cars_sorted = sort_data_by_mileage();
        for (Transport car : freight_cars_sorted) {
            System.out.printf("Car type: %d, car number: %d, car mileage: %d, car additional info: %d \n", car.getCar_type(),
                    car.getCar_number(), car.getCar_mileage(), car.getCar_additional_info());
        }
    }

    private void sorted_by_additional_info_print() {
        ArrayList<Transport> freight_cars_sorted_by_mileage = sort_data_by_add_info();
        for (Transport car : freight_cars_sorted_by_mileage) {
            System.out.printf("Car type: %d, car number: %d, car mileage: %d, car additional info: %d \n", car.getCar_type(),
                    car.getCar_number(), car.getCar_mileage(), car.getCar_additional_info());
        }
    }
}
