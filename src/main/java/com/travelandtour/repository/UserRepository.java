package com.travelandtour.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.travelandtour.model.*;

public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmailAndPassword(String email,String password);
	User findByEmail(String email);
	User findByPhoneno(String phoneno);
	User findByUserId(Long id);
	

}
