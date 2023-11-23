package org.local.WeatherDataProcessor;

import org.apache.flink.api.common.functions.MapFunction;
import org.local.WeatherDataProcessor.dataModels.FlattenedWeatherData;
import org.local.WeatherDataProcessor.dataModels.MinutelyData;
import org.local.WeatherDataProcessor.dataModels.WeatherData;

public class WeatherDataMapFunction implements
    MapFunction<WeatherData, FlattenedWeatherData> {

  @Override
  public FlattenedWeatherData map(WeatherData value) {
    double totalPrecipitation = value
        .getMinutely()
        .stream()
        .mapToDouble(MinutelyData::getPrecipitation)
        .sum();
    long timestamp = value.getMinutely().isEmpty()
        ? System.currentTimeMillis()
        : value.getMinutely().get(0).getDt();
    return new FlattenedWeatherData(value.getLat(), value.getLon(), timestamp, totalPrecipitation,
        value.getTimezone_offset());
  }
}
