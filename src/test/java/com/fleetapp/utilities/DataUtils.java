package com.fleetapp.utilities;

import com.github.javafaker.Faker;

import java.util.Random;

public class DataUtils {

    private static Faker faker = new Faker();

    public static String generateFirstName(){ return faker.name().firstName();}

    public static String generateLastName(){ return faker.name().lastName();}

    public static String generateRandomFullName(){ return faker.name().fullName();}

    public static String generatePhoneNumber(){ return faker.phoneNumber().cellPhone();}

    public static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateRandomCountry() {
        return faker.country().name();
    }

    public static String generateRandomStreetAddress() {
        return faker.address().streetAddress();
    }

    public static String generateRandomCity() {
        return faker.address().city();
    }

    public static String generateRandomState() {
        return faker.address().state();
    }

    public static String generateRandomZipCode() {
        return faker.address().zipCode();
    }

    public static String generateRandomNumber(int digits) {
        return faker.number().digits(digits);
    }

    public static int generateRandomNumber(int min, int max){
        Random random = new Random();
        max++;
        return random.nextInt(max - min) + min;
    }

    public static String generateRandomColor() {
        return faker.color().name();
    }
}
