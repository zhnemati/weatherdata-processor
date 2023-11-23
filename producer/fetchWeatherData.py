import requests
from confluent_kafka import Producer
import json
import random
import os


KAFKA_TOPIC = 'weather-data-v2'
KAFKA_BROKER = 'kafka:9092'
file_path='/usr/src/app/api_response.json'
coordinates = [(random.uniform(-90, 90), random.uniform(-180, 180)) for _ in range(1)]


def fetch_precipitation_static_response(file_path):
    try:
        with open(file_path, 'r') as file:
            data = json.load(file)
        return data
    except Exception as e:
        print(f"Error reading from file: {e}")
        return None

def modify_data(static_data, iteration):
    # Randomize latitude and longitude
    static_data["lat"] = random.uniform(-90, 90)
    static_data["lon"] = random.uniform(-180, 180)
    static_data["timezone_offset"] = random.randint(-43200, 50400)

    for minute_data in static_data["minutely"]:
        minute_data["dt"] += 60 * iteration  # Increment by 60 seconds for each iteration
        minute_data["precipitation"] = random.uniform(0, 10)  # Random precipitation value

    return static_data

def send_to_kafka(producer, data):
    if data:
        try:
            message = json.dumps(data)
            print(f"Data : {message}")
            producer.produce(KAFKA_TOPIC, value=message)
            producer.flush()
            print(f"Data sent to Kafka topic '{KAFKA_TOPIC}'")
        except Exception as e:
            print(f"Error sending data to Kafka : {e}")

def main():
    producer = Producer({'bootstrap.servers': KAFKA_BROKER})
    static_data = fetch_precipitation_static_response()
    if static_data:
        for i in range(70):
            modified_data = modify_data(static_data.copy(), i)
            send_to_kafka(producer, modified_data)

if __name__ == "__main__":
    main()