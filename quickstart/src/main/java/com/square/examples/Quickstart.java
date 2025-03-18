package com.square.examples;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.squareup.square.AsyncSquareClient;
import com.squareup.square.core.Environment;
import com.squareup.square.types.Location;
import com.squareup.square.types.Error;
import com.squareup.square.core.SquareApiException;
import com.squareup.square.types.Address;

public class Quickstart {
    public static void main(String[] args) {
        InputStream inputStream =
            Quickstart.class.getResourceAsStream("/config.properties");
        Properties prop = new Properties();

        try {
            prop.load(inputStream);
        } catch (IOException e) {
            System.out.println("Error reading properties file");
            e.printStackTrace();
        }

        AsyncSquareClient client = AsyncSquareClient.builder()
            .token(prop.getProperty("SQUARE_ACCESS_TOKEN"))
            .environment(Environment.SANDBOX)
            .build();

        client.locations().list()
            .thenAccept(result -> {
                System.out.println("Location(s) for this account:");
                result.getLocations().ifPresent(locations -> {
                    for (Location l : locations) {
                        // Build location details safely
                        StringBuilder locationInfo = new StringBuilder();
                        
                        // Add ID if present
                        l.getId().ifPresent(id -> locationInfo.append("ID: ").append(id));
                        
                        // Add name if present
                        l.getName().ifPresent(name -> 
                            locationInfo.append(locationInfo.length() > 0 ? ", " : "")
                                     .append("Name: ").append(name));
                        
                        // Handle address details if present
                        l.getAddress().ifPresent(address -> {
                            // Add address line 1 if present
                            address.getAddressLine1().ifPresent(line1 -> 
                                locationInfo.append(locationInfo.length() > 0 ? ", " : "")
                                         .append("Address: ").append(line1));
                            
                            // Add locality (city) if present
                            address.getLocality().ifPresent(locality -> 
                                locationInfo.append(locationInfo.length() > 0 ? ", " : "")
                                         .append("City: ").append(locality));
                        });

                        // Print the complete location information
                        System.out.println(locationInfo.toString());
                    }
                });
            })
            .exceptionally(exception -> {
                if (exception.getCause() instanceof SquareApiException) {
                    SquareApiException apiException = (SquareApiException) exception.getCause();
                    apiException.errors().forEach(error -> {
                        System.out.println("Category: " + error.getCategory());
                        System.out.println("Code: " + error.getCode());
                        System.out.println("Detail: " + error.getDetail());
                    });
                } else {
                    System.out.println("An unexpected error occurred:");
                    exception.printStackTrace();
                }
                return null;
            })
            .join();
    }
}