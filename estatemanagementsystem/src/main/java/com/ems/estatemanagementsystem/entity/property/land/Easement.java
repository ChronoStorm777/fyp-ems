package com.ems.estatemanagementsystem.entity.property.land;

import com.ems.estatemanagementsystem.entity.Land;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "easement")
public class Easement extends Land{

}
