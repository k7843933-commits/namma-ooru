package com.example.Namma_Ooru.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Namma_Ooru.entity.Place;
import java.util.List;
public interface PlaceRepository extends JpaRepository<Place, Integer> {
	List<Place> findBycityId(int cityId);
}
