package com.ems.estatemanagementsystem.repository.propertyrepository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.estatemanagementsystem.entity.User;
import com.ems.estatemanagementsystem.entity.property.Cash;

@Repository
public interface CashRepository extends JpaRepository<Cash, Long>{
    List<Cash> findByUser(User user);

    List<Cash> findByUserId(Long userId);
}
