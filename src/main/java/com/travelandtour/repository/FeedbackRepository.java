package com.travelandtour.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.travelandtour.model.*;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

}
