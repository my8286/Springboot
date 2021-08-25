package com.travelandtour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.travelandtour.model.*;

public interface BookingRepository extends JpaRepository<Booking,Long> {

}
