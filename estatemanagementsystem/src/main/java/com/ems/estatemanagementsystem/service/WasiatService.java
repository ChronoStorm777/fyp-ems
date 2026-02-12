package com.ems.estatemanagementsystem.service;

import java.util.List;

import com.ems.estatemanagementsystem.dto.WasiatDto;
import com.ems.estatemanagementsystem.entity.User;
import com.ems.estatemanagementsystem.entity.Wasiat;

public interface WasiatService {

    List<Wasiat> getWasiatByUser(User user);

    Wasiat saveWasiat(Wasiat wasiat);

    Wasiat findWasiatById(Long id);

    void updateWasiat(Long id, String content);

    void deleteWasiat(Long id);

    void deleteUserWasiat(Long userId);

    List<WasiatDto> getAllWasiat();

    Wasiat getWasiatByUserId(Long userId);

    void updateWasiat(Wasiat wasiat);

    // Wasiat getWasiatDetailsByUserId(Long userId);

    User getWasiatDetailsByUserId(Long userId);    
}