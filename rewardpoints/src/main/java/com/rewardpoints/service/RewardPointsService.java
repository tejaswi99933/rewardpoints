package com.rewardpoints.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewardpoints.exception.RecordNotFoundException;
import com.rewardpoints.model.BillDetailsEntity;
import com.rewardpoints.repository.BillDetailsRepository;

@Service
public class RewardPointsService {

	@Autowired
	BillDetailsRepository repository;

	/**
	 * This method is to get the all data,like customer bills with their reward
	 * points
	 * 
	 * @return
	 */
	public List<BillDetailsEntity> getAllBills() {
		List<BillDetailsEntity> billList = repository.findAll();

		if (billList.size() > 0) {
			return billList;
		} else {
			return new ArrayList<BillDetailsEntity>();
		}
	}

	/**
	 * This methos is to save the Customer Bills and calculate the poins as per the
	 * amount
	 * 
	 * @param entity
	 * @return
	 * @throws RecordNotFoundException
	 */
	public BillDetailsEntity saveBill(BillDetailsEntity entity) {

		int points = pointsCalculation(entity.getBillAmounts());
		entity.setPoints(points);
		entity = repository.save(entity);
		return entity;
	}

	/**
	 * This method is to get the bills with the startDate,endDate and clientID
	 * 
	 * @param startDate
	 * @param endDate
	 * @param clientID
	 * @return
	 */

	public List<BillDetailsEntity> getBillsByDateAndClient(String startDate, String endDate, String clientID) {
		List<BillDetailsEntity> billList = repository.customerReport(startDate, endDate, clientID);

		if (billList.size() > 0) {
			return billList;
		} else {
			return new ArrayList<BillDetailsEntity>();
		}
	}

	/**
	 * This method is to get the total points based on the selected dates for a
	 * client.
	 * 
	 * @param startDate
	 * @param endDate
	 * @param clientID
	 * @return
	 */

	public int getTotalPoints(String startDate, String endDate, String clientID) {
		return repository.totalPointsByCustomer(startDate, endDate, clientID);
	}

	public int pointsCalculation(int total) {

		int incrCounter = 0;
		int finalPoints = 0;

		if (total >= 100) {
			incrCounter = (total - 100) * 2;

			int between50and100 = 0;
			int numberofhundred = total / 100;
			for (int i = 0; i < numberofhundred; i++) {
				between50and100 += 1 * 50;
			}

			int remaining = total - (100 * numberofhundred);

			int remainingAbove50point = 0;
			if (remaining > 50) {
				remainingAbove50point = (remaining - 50) * 1;
			}

			finalPoints = incrCounter + between50and100 + remainingAbove50point;
		}
		if (total < 100 && total > 50) {
			finalPoints = (total - 50) * 1;
		}

		return finalPoints;
	}

}