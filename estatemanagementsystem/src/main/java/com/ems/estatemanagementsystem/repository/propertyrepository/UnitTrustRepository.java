package com.ems.estatemanagementsystem.repository.propertyrepository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.estatemanagementsystem.entity.User;
import com.ems.estatemanagementsystem.entity.property.UnitTrust;

@Repository
public interface UnitTrustRepository extends JpaRepository<UnitTrust, Long>{
    List<UnitTrust> findByUser(User user);

    List<UnitTrust> findByUserId(Long userId);
}
