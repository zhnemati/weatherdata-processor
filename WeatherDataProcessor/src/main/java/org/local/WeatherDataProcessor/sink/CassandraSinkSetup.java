package org.local.WeatherDataProcessor.sink;

import com.datastax.driver.mapping.Mapper;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.connectors.cassandra.CassandraSink;
import org.local.WeatherDataProcessor.dataModels.FlattenedWeatherData;

public class CassandraSinkSetup {

  public static void setupCassandraSink(DataStream<FlattenedWeatherData> stream) throws Exception {
    CassandraSink.addSink(stream)
        .setHost("cassandra", 9042)  // Set the address of your Cassandra cluster
        .setMapperOptions(() -> new Mapper.Option[]{Mapper.Option.saveNullFields(true)})
        .build()
        .disableChaining();
  }
}

