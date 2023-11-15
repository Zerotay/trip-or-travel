package com.pjt.triptravel.attraction.repository.region;

import java.util.List;

import com.pjt.triptravel.attraction.entity.region.Gugun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GugunRepository extends JpaRepository<Gugun, Long> {

	@Query("select g from Gugun g join fetch g.sido s where s.code = :sidoCode")
	List<Gugun> findBySidoCode(@Param("sidoCode") Long sidoCode);
}