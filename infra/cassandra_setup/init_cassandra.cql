CREATE KEYSPACE IF NOT EXISTS weather_data_v0
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE weather_data_v0;

CREATE TABLE IF NOT EXISTS weather_data_v0.precipitation_measurements (
    lat double,
    lon double,
    timestamp bigint,
    precipitation double,
    timezone_offset bigint,
    PRIMARY KEY (lat, lon, timestamp)
);