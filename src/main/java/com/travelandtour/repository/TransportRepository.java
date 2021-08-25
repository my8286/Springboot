package com.travelandtour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelandtour.model.*;

public interface TransportRepository extends JpaRepository<Transport,Long> {
	List<Transport> findBySourceAndDestinationAndType(String from,String to,Integer type);
	Transport findByTransportId(Long id);

}

