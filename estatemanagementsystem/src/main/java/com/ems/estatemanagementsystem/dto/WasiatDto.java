package com.ems.estatemanagementsystem.dto;

import java.util.List;

import com.ems.estatemanagementsystem.entity.AnakAngkatDetail;
import com.ems.estatemanagementsystem.entity.AnakLelakiDetail;
import com.ems.estatemanagementsystem.entity.AnakPerempuanDetail;
import com.ems.estatemanagementsystem.entity.IsteriDetail;
import com.ems.estatemanagementsystem.entity.SuamiDetail;
import com.ems.estatemanagementsystem.entity.BapaDetail;
import com.ems.estatemanagementsystem.entity.IbuDetail;

import com.ems.estatemanagementsystem.entity.User;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WasiatDto {

    private Long id;
    private User user;

    @NotEmpty(message = "Content should not be empty")

    private String gender;
    private String ayah;
    private String ibu;
    private String isteri;
    private String suami;
    private String anakLelaki;
    private String anakPerempuan;
    private String anakAngkat;
    private String confirmation;
    private String perbelanjaan;
    private String anggaran;
    private String hibah;
    private List<AnakLelakiDetail> anakLelakiDetails;
    private List<AnakPerempuanDetail> anakPerempuanDetails;
    private List<AnakAngkatDetail> anakAngkatDetails;
    private List<IsteriDetail> isteriDetails;
    private List<SuamiDetail> suamiDetails;
    private List<BapaDetail> ayahDetails;
    private List<IbuDetail> ibuDetails;

    /* 
     // Fetching and calculating shares dynamically
    public void calculateFaraid(Long userId, WasiatService wasiatService) {
        // Fetch related details based on the user ID
        this.isteriDetails = wasiatService.fetchIsteriDetailsByUserId(userId);
        this.ayahDetails = wasiatService.fetchBapaDetailsByUserId(userId);
        this.ibuDetails = wasiatService.fetchIbuDetailsByUserId(userId);
        this.anakLelakiDetails = wasiatService.fetchAnakLelakiDetailsByUserId(userId);
        this.anakPerempuanDetails = wasiatService.fetchAnakPerempuanDetailsByUserId(userId);

        double totalEstate = wasiatService.fetchTotalEstateByUserId(userId);

        int numIsteri = isteriDetails != null ? isteriDetails.size() : 0;
        //int numBapa = ayahDetails != null ? ayahDetails.size() : 0;
        //int numIbu = ibuDetails != null ? ibuDetails.size() : 0;
        int numAnakLelaki = anakLelakiDetails != null ? anakLelakiDetails.size() : 0;
        int numAnakPerempuan = anakPerempuanDetails != null ? anakPerempuanDetails.size() : 0;

        double shareIsteri = 0;
        double shareIbu = 0;
        double shareBapa = 0;
        double shareAnakLelaki = 0;
        double shareAnakPerempuan = 0;

        if (numIsteri > 0 && numAnakLelaki > 1 && numAnakPerempuan == 0) {
            shareIsteri = 1.0 / 8;
            shareIbu = 1.0 / 6;
            shareBapa = 1.0 / 6;

            double totalFardhuShare = shareIsteri + shareIbu + shareBapa;
            double remainingShare = 1 - totalFardhuShare;

            // Calculate Asobah for sons only
            double asobahRatioTotal = 2 * numAnakLelaki; // Sons share 2 parts each
            shareAnakLelaki = (2 / asobahRatioTotal) * remainingShare;
            shareAnakPerempuan = 0;
        } else if (numIsteri > 0 && numAnakLelaki == 0 && numAnakPerempuan > 0) {
            shareIsteri = 1.0 / 8;
            shareIbu = 1.0 / 6;

            double totalFardhuShare = shareIsteri + shareIbu + shareBapa;
            double remainingShare = 1 - totalFardhuShare;

            shareAnakLelaki = 0;
            shareAnakPerempuan = 1.0 / 2 * remainingShare;

            if (numAnakPerempuan > 1) {
                shareAnakPerempuan = (2.0 / 3) * remainingShare / numAnakPerempuan;
            }
        } else if (numIsteri > 0 && numAnakLelaki > 0 && numAnakPerempuan > 0) {
            shareIsteri = 1.0 / 8;
            shareIbu = 1.0 / 6;
            shareBapa = 1.0 / 6;

            double totalFardhuShare = shareIsteri + shareIbu + shareBapa;
            double remainingShare = 1 - totalFardhuShare;

            double asobahRatioTotal = (2 * numAnakLelaki) + numAnakPerempuan;
            shareAnakLelaki = (2 / asobahRatioTotal) * remainingShare;
            shareAnakPerempuan = (1 / asobahRatioTotal) * remainingShare;
        }

        // Calculate share values
        double isteriShareValue = shareIsteri * totalEstate;
        double ibuShareValue = shareIbu * totalEstate;
        double bapaShareValue = shareBapa * totalEstate;
        double anakLelakiShareValue = shareAnakLelaki * totalEstate;
        double anakPerempuanShareValue = shareAnakPerempuan * totalEstate;

        // Print or return the calculated shares
        System.out.printf("Isteri Share: %.2f%n", isteriShareValue);
        System.out.printf("Ibu Share: %.2f%n", ibuShareValue);
        System.out.printf("Bapa Share: %.2f%n", bapaShareValue);
        System.out.printf("Anak Lelaki Share: %.2f%n", anakLelakiShareValue);
        System.out.printf("Anak Perempuan Share: %.2f%n", anakPerempuanShareValue);
    }

    */
}
