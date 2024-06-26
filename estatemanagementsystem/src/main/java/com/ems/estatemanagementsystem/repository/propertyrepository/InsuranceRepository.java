package com.ems.estatemanagementsystem.repository.propertyrepository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.estatemanagementsystem.entity.User;
import com.ems.estatemanagementsystem.entity.property.Insurance;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long>{
    List<Insurance> findByUser(User user);

    List<Insurance> findByUserId(Long userId);
}
