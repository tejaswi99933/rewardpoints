package com.rewardpoints.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rewardpoints.model.BillDetailsEntity;

@Repository
public interface BillDetailsRepository extends JpaRepository<BillDetailsEntity, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM CUSTOMER_REWARD_POINTS WHERE PURCHASE_DATE BETWEEN :startDate and :endDate and CUSTOMER_ID=:clientId")
	List<BillDetailsEntity> customerReport(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("clientId") String clientId);

	@Query(nativeQuery = true, value = "SELECT SUM(POINTS) FROM CUSTOMER_REWARD_POINTS WHERE PURCHASE_DATE BETWEEN :startDate and :endDate and CUSTOMER_ID=:clientId")
	int totalPointsByCustomer(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("clientId") String clientId);

}
