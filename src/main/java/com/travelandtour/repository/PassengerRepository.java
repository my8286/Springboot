package com.travelandtour.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.travelandtour.model.*;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {

}