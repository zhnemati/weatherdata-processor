package org.local.WeatherDataProcessor.utils;
import java.util.List;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.io.Serializable;
import org.local.WeatherDataProcessor.dataModels.WeatherData;

public class TestDataSource implements SourceFunction<WeatherData>, Serializable {
  private final List<WeatherData> testData;

  public TestDataSource(List<WeatherData> testData) {
    this.testData = testData;
  }

  @Override
  public void run(SourceContext<WeatherData> ctx) throws Exception {
    for (WeatherData data : testData) {
      ctx.collect(data);
    }
  }

  @Override
  public void cancel() {
    //Overriding but not used
  }
}


