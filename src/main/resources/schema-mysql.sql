CREATE TABLE IF NOT EXISTS providers (
  id VARCHAR(20) PRIMARY KEY,
  prov_name varchar(20),
  trans_date date,
  Transaction_Info varchar(200)
);

CREATE TABLE IF NOT EXISTS provider_transaction (
  ID VARCHAR(20),
  Name_Provider varchar(20),
  Is_Fraud varchar(10),
  Amount long,
  TIN varchar(200)
);


