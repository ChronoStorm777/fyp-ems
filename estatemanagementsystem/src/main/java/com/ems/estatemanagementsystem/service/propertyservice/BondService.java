package com.ems.estatemanagementsystem.service.propertyservice;

import java.util.List;

import com.ems.estatemanagementsystem.entity.User;
import com.ems.estatemanagementsystem.entity.property.Bond;

public interface BondService {
    List<Bond> getAllBonds();

	List<Bond> getBondsByUser(User user);

	List<Bond> getBondsByUserId(Long userId);
	
	Bond saveBond(Bond bond);
	
	Bond getBondById(Long id);
	
	Bond updateBond(Bond bond);
	
	void deleteBondById(Long id);
}
