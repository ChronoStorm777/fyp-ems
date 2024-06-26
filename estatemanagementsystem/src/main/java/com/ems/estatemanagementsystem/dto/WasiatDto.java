package com.ems.estatemanagementsystem.dto;

import java.util.List;

import com.ems.estatemanagementsystem.entity.AnakAngkatDetail;
import com.ems.estatemanagementsystem.entity.AnakLelakiDetail;
import com.ems.estatemanagementsystem.entity.AnakPerempuanDetail;
import com.ems.estatemanagementsystem.entity.IsteriDetail;
import com.ems.estatemanagementsystem.entity.SuamiDetail;
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
}
