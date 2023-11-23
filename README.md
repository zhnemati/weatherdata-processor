# weatherdata-processor
This is an application that processes data from a weather API on a structural basis. The API used is OpenWeatherMap (https://openweathermap.org/api/one-call-api). Currently we are mocking the API to avoid rate limit on openweather free tier account

## Processing stream of precipitation forecasts
The `minutely` field in the API gives an hour ahead of precipitation volume forecasts (in mm) at the minute granularity. This application parses the 'minutely' response field and sums the forecasted precipitation volumes (in mm) for the next hour. The application tells us in real time how much rain will fall in the coming hour.

### Criteria for implementation:
* We have 'simulated' the data being generated in streaming fashion by making a new call every minute. For this we are using a simple cron job.
* For queuing the messages we are using kafka
* For processing the stream of minute forecasts we are using Flink
* For storing the processed stream, we are using cassandra
* The application runs on your local machine, with docker-compose.
