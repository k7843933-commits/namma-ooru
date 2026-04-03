package com.example.Namma_Ooru.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Namma_Ooru.entity.Tourist;

public interface TouristRepository extends JpaRepository<Tourist,Integer> {
	Optional<Tourist> findByPhoneAndPassword(String phone, String password);
	Optional<Tourist> findByPhone(String phone);
}
