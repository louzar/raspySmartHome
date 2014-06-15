CREATE TABLE IF NOT EXISTS TemperatureHistory
(
  id                    NUMBER(19) NOT NULL,
  temperature           NUMBER(19) NOT NULL,
  measure_date          DATETIME,
);