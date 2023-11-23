package org.local.WeatherDataProcessor.dataModels;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MinutelyData implements Serializable {
  @Getter
  @Setter
  private long dt;
  @Getter
  @Setter
  private double precipitation;

}
