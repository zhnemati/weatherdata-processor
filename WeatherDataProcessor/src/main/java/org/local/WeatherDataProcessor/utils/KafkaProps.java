package org.local.WeatherDataProcessor.utils;

import java.util.Properties;

public class KafkaProps {
  public static String TOPIC_NAME="weather-data-v2";
  private static String BOOTSTRAP_SERVER="kafka:9092";
  private static String CONSUMER_GROUP="weather-consumer-group-flink-v1";


  public static Properties configureKafkaProperties() {
    Properties properties = new Properties();
    properties.setProperty("bootstrap.servers", BOOTSTRAP_SERVER);
    properties.setProperty("group.id", CONSUMER_GROUP);
    return properties;
  }
}
