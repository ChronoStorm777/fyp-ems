package com.ems.estatemanagementsystem.service.propertyservice;

import java.util.List;

import com.ems.estatemanagementsystem.entity.User;
import com.ems.estatemanagementsystem.entity.property.Debenture;

public interface DebentureService {
    List<Debenture> getAllDebentures();

	List<Debenture> getDebenturesByUser(User user);

	List<Debenture> getDebenturesByUserId(Long userId);
	
	Debenture saveDebenture(Debenture debenture);
	
	Debenture getDebentureById(Long id);
	
	Debenture updateDebenture(Debenture debenture);
	
	void deleteDebentureById(Long id);

}
