package com.ems.estatemanagementsystem.service.propertyservice.landservice;

import java.util.List;

import com.ems.estatemanagementsystem.entity.User;
import com.ems.estatemanagementsystem.entity.property.land.RightOfWay;

public interface RightOfWayService {
    List<RightOfWay> getAllRightOfWays();

	List<RightOfWay> getRightOfWaysByUser(User user);

	List<RightOfWay> getRightOfWaysByUserId(Long userId);
	
	RightOfWay saveRightOfWay(RightOfWay rightOfWay);
	
	RightOfWay getRightOfWayById(Long id);
	
	RightOfWay updateRightOfWay(RightOfWay rightOfWay);
	
	void deleteRightOfWayById(Long id);
}
