package org.local.WeatherDataProcessor.dataModels;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WeatherData implements Serializable {
  @Getter
  @Setter
  private double lat;
  @Getter
  @Setter
  private double lon;
  @Getter
  @Setter
  private String timezone;
  @Getter
  @Setter
  private long timezone_offset;
  @Getter
  @Setter
  private List<MinutelyData> minutely;

 }
