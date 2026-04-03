package com.example.Namma_Ooru.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Namma_Ooru.entity.Guide;
import com.example.Namma_Ooru.entity.Tourist;

import java.util.Optional;
public interface GuideRepository extends JpaRepository<Guide,Integer>{
	List<Guide> findByCityId(int cityId);
	Optional<Guide> findByPhoneAndPassword(String phone, String password);

}