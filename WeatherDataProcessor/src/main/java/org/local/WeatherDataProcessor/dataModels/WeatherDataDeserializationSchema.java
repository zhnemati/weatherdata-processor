package org.local.WeatherDataProcessor.dataModels;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.local.WeatherDataProcessor.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherDataDeserializationSchema implements DeserializationSchema<WeatherData> {
  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public WeatherData deserialize(byte[] message) throws IOException {
    try {
      return objectMapper.readValue(message, WeatherData.class);
    } catch (Exception e) {
      LOG.error("Failed to deserialize message: {}", new String(message), e);
      return null;
    }
  }

  @Override
  public boolean isEndOfStream(WeatherData nextElement) {
    return false;
  }

  @Override
  public TypeInformation<WeatherData> getProducedType() {
    return TypeInformation.of(WeatherData.class);
  }
}


