package com.rewardpoints.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rewardpoints.exception.RecordNotFoundException;
import com.rewardpoints.model.BillDetailsEntity;
import com.rewardpoints.service.RewardPointsService;

@RestController
@RequestMapping("/rewardpoints")
public class RewardPointsController {

	RewardPointsService service;

	RewardPointsController(RewardPointsService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<BillDetailsEntity>> getAllBills() {
		List<BillDetailsEntity> list = service.getAllBills();

		return new ResponseEntity<List<BillDetailsEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/totalPoints")
	public ResponseEntity<Object> getTotalPoints(@RequestParam String startDate, @RequestParam String endDate,
			@RequestParam String clientid) {
		int totalPoints = service.getTotalPoints(startDate, endDate, clientid);
		return new ResponseEntity<Object>(totalPoints, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BillDetailsEntity> createBills(@RequestBody BillDetailsEntity bill)
			throws RecordNotFoundException {
		BillDetailsEntity updated = service.saveBill(bill);
		return new ResponseEntity<BillDetailsEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/customerReport")
	public ResponseEntity<List<BillDetailsEntity>> getCustomerBills(@RequestParam String startDate,
			@RequestParam String endDate, @RequestParam String clientid) {
		List<BillDetailsEntity> entityList = service.getBillsByDateAndClient(startDate, endDate, clientid);
		return new ResponseEntity<List<BillDetailsEntity>>(entityList, new HttpHeaders(), HttpStatus.OK);
	}
}