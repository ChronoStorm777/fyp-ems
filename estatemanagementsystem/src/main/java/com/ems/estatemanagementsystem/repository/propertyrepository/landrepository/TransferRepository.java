package com.ems.estatemanagementsystem.repository.propertyrepository.landrepository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.estatemanagementsystem.entity.User;
import com.ems.estatemanagementsystem.entity.property.land.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long>{
    List<Transfer> findByUser(User user);

    List<Transfer> findByUserId(Long userId);
}
