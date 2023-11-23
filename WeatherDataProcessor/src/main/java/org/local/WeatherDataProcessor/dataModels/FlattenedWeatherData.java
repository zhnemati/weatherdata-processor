package org.local.WeatherDataProcessor.dataModels;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(keyspace = "weather_data_v0", name = "precipitation_measurements")
public class FlattenedWeatherData {
  @Getter
  @Setter
  @Column(name = "lat")
  private double lat;
  @Getter
  @Setter
  @Column(name = "lon")
  private double lon;
  @Getter
  @Setter
  @Column(name = "timestamp")
  private long dt;
  @Getter
  @Setter
  @Column(name = "precipitation")
  private double precipitation;
  @Getter
  @Setter
  @Column(name = "timezone_offset")
  private long timezone_offset;
  @Override
  public String toString() {
    return "FlatWeatherData{" +
        "lat=" + lat +
        ", lon=" + lon +
        ", dt=" + dt +
        ", precipitation=" + precipitation +
        ", timezone=" + timezone_offset +
        '}';
  }

  }

