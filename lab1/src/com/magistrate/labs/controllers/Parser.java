package com.magistrate.labs.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final Pattern type = Pattern.compile("(?<=C)\\d{3}");
    private final Pattern number = Pattern.compile("(?<=_)\\d*");
    private final Pattern mileage = Pattern.compile("(?<=C\\d{3}_\\d-)\\d*");
    private final Pattern additional_info = Pattern.compile("(?<=-)\\d*$");
    String parsable_string;

    public Parser(String entry) {
        parsable_string = entry;
    }

    private String parse(Pattern pattern) {

        String parsed_string = null;

        try {
            Matcher matcher = pattern.matcher(parsable_string);
            if (matcher.find()) {
                parsed_string = matcher.group();
            }
        } catch (IllegalStateException exception) {
            exception.printStackTrace();
        }

        return parsed_string;
    }

    public String get_type() {
        return parse(type);
    }

    public String get_number() {
        return parse(number);
    }

    public String get_mileage() {
        return parse(mileage);
    }

    public String get_additional_info() {
        return parse(type).equals("100") ? "0" : parse(additional_info);
    }
}
