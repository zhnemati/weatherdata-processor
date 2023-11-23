package org.local.WeatherDataProcessor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.test.util.AbstractTestBase;
import org.local.WeatherDataProcessor.dataModels.FlattenedWeatherData;
import org.local.WeatherDataProcessor.dataModels.WeatherData;
import org.local.WeatherDataProcessor.utils.SampleWeatherDataGenerator;
import org.local.WeatherDataProcessor.utils.TestDataSource;
import org.junit.Test;

@Slf4j
public class MainTest extends AbstractTestBase {
    @Test
    public void testWeatherDataProcessing() throws Exception {
      List<WeatherData> testData = SampleWeatherDataGenerator.createSampleWeatherData();

      StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
      env.setParallelism(1);
      DataStream<WeatherData> testDataStream = env.addSource(new TestDataSource(testData));
      DataStream<FlattenedWeatherData> processedDataStream = Main.processWeatherData(testDataStream);
      processedDataStream.addSink(new ListCollector<FlattenedWeatherData>());
      env.execute("Integration Test");
      List<FlattenedWeatherData> results = ListCollector.values;
      assertThat(results).isNotEmpty();
      for (FlattenedWeatherData data : results) {
        assertThat(data.getLon()).isNotNull();
        assertThat(data.getLat()).isNotNull();
        assertThat(data.getDt()).isNotNull();
      }
      //for element 1 the value for precipitation is 13 -->System.out.println(results.get(1);
      assertThat(results.get(1).getPrecipitation()).isEqualTo(13.0);

    }

  public static class ListCollector<F> implements SinkFunction<FlattenedWeatherData> {
    public static final List<FlattenedWeatherData> values = new ArrayList<>();

    public synchronized void invoke(FlattenedWeatherData value, Context context) {
      values.add(value);
    }
  }


  }

