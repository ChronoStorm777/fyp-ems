package com.ems.estatemanagementsystem.repository.propertyrepository.landrepository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.estatemanagementsystem.entity.User;
import com.ems.estatemanagementsystem.entity.property.land.RightOfWay;

@Repository
public interface RightOfWayRepository extends JpaRepository<RightOfWay, Long>{
    List<RightOfWay> findByUser(User user);

    List<RightOfWay> findByUserId(Long userId);
}
