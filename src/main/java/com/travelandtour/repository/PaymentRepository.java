package com.travelandtour.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.travelandtour.model.*;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

}