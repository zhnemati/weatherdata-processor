package org.local.WeatherDataProcessor;

import static org.local.WeatherDataProcessor.utils.KafkaProps.TOPIC_NAME;

import java.util.Objects;
import java.util.Properties;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.local.WeatherDataProcessor.dataModels.FlattenedWeatherData;
import org.local.WeatherDataProcessor.dataModels.WeatherDataDeserializationSchema;
import org.local.WeatherDataProcessor.dataModels.WeatherData;
import org.local.WeatherDataProcessor.sink.CassandraSinkSetup;

import org.local.WeatherDataProcessor.utils.KafkaProps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) throws Exception {
    final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    Properties kafkaProperties = KafkaProps.configureKafkaProperties();
    FlinkKafkaConsumer<WeatherData> kafkaSource = new FlinkKafkaConsumer<>(
        TOPIC_NAME,
        new WeatherDataDeserializationSchema(),
        kafkaProperties);
    kafkaSource.setStartFromEarliest();

    DataStream<WeatherData> weatherDataStream = env.addSource(kafkaSource).disableChaining();
    DataStream<FlattenedWeatherData> aggregatedStream = processWeatherData(weatherDataStream);
    CassandraSinkSetup.setupCassandraSink(aggregatedStream);

    try {
      env.execute("Weather Data Processor");
    } catch (Exception e) {
      LOG.error("Error executing Flink job: {}", e.getMessage(), e);
    }
  }

  public static DataStream<FlattenedWeatherData> processWeatherData(
      DataStream<WeatherData> weatherDataStream) {
    return weatherDataStream
        .filter(Objects::nonNull)
        .map(new WeatherDataMapFunction()).disableChaining();
  }
}

