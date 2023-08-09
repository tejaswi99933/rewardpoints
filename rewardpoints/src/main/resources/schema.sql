DROP TABLE IF EXISTS CUSTOMER_REWARD_POINTS;
 
CREATE TABLE CUSTOMER_REWARD_POINTS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  customer_id number NOT NULL,
  bill_items VARCHAR(250) NOT NULL,
  bill_amounts number NOT NULL,
  points number DEFAULT NULL,
  purchase_date date DEFAULT CURRENT_DATE
);