#!/bin/bash
sleep 15 # wait for jobmanager and kafka cluster to spin up
./bin/flink run /opt/flink/usrlib/WeatherDataProcessor-1.5.jar