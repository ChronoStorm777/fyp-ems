package com.ems.estatemanagementsystem.service.propertyservice.landservice;

import java.util.List;

import com.ems.estatemanagementsystem.entity.User;
import com.ems.estatemanagementsystem.entity.property.land.Waqf;

public interface WaqfService {
    List<Waqf> getAllWaqfs();

	List<Waqf> getWaqfsByUser(User user);

	List<Waqf> getWaqfsByUserId(Long userId);
	
	Waqf saveWaqf(Waqf waqf);
	
	Waqf getWaqfById(Long id);
	
	Waqf updateWaqf(Waqf waqf);
	
	void deleteWaqfById(Long id);
}
