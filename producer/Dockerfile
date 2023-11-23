FROM python:3.9-slim
WORKDIR /usr/src/app
RUN apt-get update && apt-get install -y cron
COPY . .
RUN pip install --upgrade pip
RUN pip install --no-cache-dir -r requirements.txt
RUN echo "PATH=/usr/local/bin:\$PATH" >> /etc/cron.d/mycron
RUN echo "* * * * * python3 /usr/src/app/fetchWeatherData.py >> /var/log/cron.log 2>&1" >> /etc/cron.d/mycron
RUN chmod 0644 /etc/cron.d/mycron
# Apply cron job
RUN crontab /etc/cron.d/mycron
RUN touch /var/log/cron.log
# Run the command on container startup
CMD cron && tail -f /var/log/cron.log

