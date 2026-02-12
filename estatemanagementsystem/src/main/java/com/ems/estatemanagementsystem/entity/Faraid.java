package com.ems.estatemanagementsystem.entity;

import java.util.HashMap;
import java.util.Map;

public class Faraid {
    
        public static Map<String, Double> calculateFaraid(double totalEstate, int numIsteri, int numAnakLelaki, int numAnakPerempuan) {
            Map<String, Double> distribution = new HashMap<>();
            
            //Base case shares
            double shareIsteri = 0;
            double shareIbu = 0;
            double shareBapa = 0;
            double shareAnakLelaki = 0;
            double shareAnakPerempuan = 0;
            
        // Check for basic scenario
        if (numIsteri > 0 && numAnakLelaki > 0 && numAnakPerempuan > 0) {
            shareIsteri = 1.0 / 8.0;
            shareIbu = 1.0 / 6.0;
            shareBapa = 1.0 / 6.0;

            double totalFardhuShare = shareIsteri + shareIbu + shareBapa;
            double remainingShare = 1 - totalFardhuShare;

            // Calculate Asobah shares
            double asobahRatioTotal = (2 * numAnakLelaki) + numAnakPerempuan;
            shareAnakLelaki = (2 / asobahRatioTotal) * remainingShare;
            shareAnakPerempuan = (1 / asobahRatioTotal) * remainingShare;
        }

        // Calculate the exact share values
        distribution.put("Isteri", shareIsteri * totalEstate);
        distribution.put("Ibu", shareIbu * totalEstate);
        distribution.put("Bapa", shareBapa * totalEstate);
        distribution.put("Anak Lelaki", shareAnakLelaki * totalEstate);
        distribution.put("Anak Perempuan", shareAnakPerempuan * totalEstate);

        return distribution;
    }

    public static void main(String[] args) {
        double totalEstate = 1000000; // Example total estate value
        int numIsteri = 1;
        int numAnakLelaki = 2;
        int numAnakPerempuan = 1;

        Map<String, Double> distribution = calculateFaraid(totalEstate, numIsteri, numAnakLelaki, numAnakPerempuan);

        // Print distribution
        distribution.forEach((waris, share) -> {
            System.out.println(waris + ": " + share);
        });
    }
}
