CREATE TABLE IF NOT EXISTS TemperatureHistory
(
  id           NUMBER(19) NOT NULL,
  temperature  NUMBER(19) NOT NULL,
  measure_date DATETIME,
);

CREATE TABLE IF NOT EXISTS HumidityHistory
(
  id           NUMBER(19) NOT NULL,
  humidity     NUMBER(19) NOT NULL,
  measure_date DATETIME,
);

CREATE SEQUENCE IF NOT EXISTS TemperatureHistory_seq;
CREATE SEQUENCE IF NOT EXISTS HumidityHistory_seq;