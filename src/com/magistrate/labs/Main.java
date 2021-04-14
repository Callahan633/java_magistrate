package com.magistrate.labs;

import com.magistrate.labs.controllers.Parser;
import com.magistrate.labs.controllers.Visualizer;
import com.magistrate.labs.models.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

	    String[] data = {"C100_1-100", "C200_1-120-1200", "C300_1-120-30", "C400_1-80-20", "C100_2-50", "C200_2-40-1000",
                "C300_2-200-45", "C400_2-10-20", "C100_3-10", "C200_3-170-1100", "C300_3-150-29", "C400_3-100-28",
                "C100_1-300", "C200_1-100-750", "C300_1-32-15"};

        ArrayList<Transport> parsed_data = new ArrayList<>();

        for (String datum : data) {

            Parser car_data = new Parser(datum);

            switch (car_data.get_type()) {
                case "100" -> {
                    LightTransport light_car = new LightTransport(car_data);
                    light_car.fill();
                    parsed_data.add(light_car);
                }

                case "200" -> {
                    FreightCar freight_car = new FreightCar(car_data);
                    freight_car.fill();
                    parsed_data.add(freight_car);
                }

                case "300" -> {
                    PassengerTransport passenger_transport = new PassengerTransport(car_data);
                    passenger_transport.fill();
                    parsed_data.add(passenger_transport);
                }

                case "400" -> {
                    Crane crane = new Crane(car_data);
                    crane.fill();
                    parsed_data.add(crane);
                }
            }
        }

//        Summary of amortization costs
        double sum = parsed_data.stream().mapToDouble(Transport::count_amortization).sum();
        System.out.println("Overall costs: " + sum);

//        Grouping elements by sums and find max and min
        Map<Integer, Double> sums_dict = parsed_data.stream().collect(Collectors.groupingBy(Transport::getCar_type, Collectors.summingDouble(Transport::count_amortization)));

        Integer max_key = sums_dict.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
        Integer min_key = sums_dict.entrySet().stream().min((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();

//        type 100 amortization costs
        System.out.println("Light cars amortization costs: " + sums_dict.get(100));

//        type 200 amortization costs
        System.out.println("Freight cars amortization costs: " + sums_dict.get(200));

//        type 300 amortization costs
        System.out.println("Passenger cars amortization costs: " + sums_dict.get(300));

//        type 400 amortization costs
        System.out.println("Crane cars amortization costs: " + sums_dict.get(400));
        System.out.println("Car type with max amortization costs: " + max_key);
        System.out.println("Car type with min amortization costs: " + min_key);

//        Sorting by mileage and additional_info
        Visualizer light_car_visualizer = new Visualizer(100, parsed_data);
        Visualizer freight_car_visualizer = new Visualizer(200, parsed_data);
        Visualizer passenger_car_visualizer = new Visualizer(300, parsed_data);
        Visualizer crane_car_visualizer = new Visualizer(400, parsed_data);
        light_car_visualizer.custom_print();
        freight_car_visualizer.custom_print();
        passenger_car_visualizer.custom_print();
        crane_car_visualizer.custom_print();
    }
}
