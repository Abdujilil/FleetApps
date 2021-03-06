package com.fleetapp.utilities;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class GlobalDataUtils {

    //Vehicles
    private String licensePlate;
    private String vehicleType;
    private String driver;
    private String location;
    private String modelYear;
    private String chassisNumber;
    private String lastOdometer;
    private String immatriculationDate;
    private String firstContractDate;
    private String catalogValue;
    private String seatsNumber;
    private String doorsNumber;
    private String color;
    private String transmission;
    private String fuelType;
    private String c02Emissions;
    private String horsepower;
    private String horsepowerTaxation;
    private String power;

    //Vehicle Contracts
    private String responsible;
    private String odometerDetails;
    private String contractStartDate;
    private Map<String, Object> rowMap;

}
