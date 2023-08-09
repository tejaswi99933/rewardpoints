package com.rewardpoints.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_REWARD_POINTS")
public class BillDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "bill_items")
	private String billItems;

	@Column(name = "customer_id")
	private Integer customerId;

	@Column(name = "bill_amounts")
	private Integer billAmounts;

	@Column(name = "points")
	private Integer points;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBillItems() {
		return billItems;
	}

	public void setBillItems(String billItems) {
		this.billItems = billItems;
	}

	public Integer getBillAmounts() {
		return billAmounts;
	}

	public void setBillAmounts(Integer billAmounts) {
		this.billAmounts = billAmounts;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

}