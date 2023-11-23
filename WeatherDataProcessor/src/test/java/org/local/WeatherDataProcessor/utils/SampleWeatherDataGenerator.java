package org.local.WeatherDataProcessor.utils;

import java.util.ArrayList;
import java.util.List;
import org.local.WeatherDataProcessor.dataModels.MinutelyData;
import org.local.WeatherDataProcessor.dataModels.WeatherData;

public class SampleWeatherDataGenerator {

  public static List<WeatherData> createSampleWeatherData() {
    List<WeatherData> sampleData = new ArrayList<>();

    for (int i = 0; i < 70; i++) {
      WeatherData sampleWeatherData = new WeatherData();
      sampleWeatherData.setLat(52.0845 + i * 0.01);
      sampleWeatherData.setLon(5.1155 + i * 0.01);

      List<MinutelyData> minutelyDataList = new ArrayList<>();

      for (long time = 1700329800; time <= 1700330520; time += 60) {
        minutelyDataList.add(new MinutelyData(time, i));
      }

      sampleWeatherData.setMinutely(minutelyDataList);
      sampleData.add(sampleWeatherData);
    }

    return sampleData;
  }
}
