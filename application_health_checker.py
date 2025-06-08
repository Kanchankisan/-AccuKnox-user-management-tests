import requests
import logging
import time

# Configure logging
logging.basicConfig(filename='application_health.log', level=logging.INFO,
                    format='%(asctime)s - %(levelname)s - %(message)s')

# Application URL and Check Interval
APP_URL = 'https://opensource-demo.orangehrmlive.com/web/index.php/auth/login'
CHECK_INTERVAL = 60  # in seconds

def check_application_health():
    try:
        response = requests.get(APP_URL, timeout=10)
        if 200 <= response.status_code < 300:
            return 'up'
        else:
            return 'down'
    except requests.RequestException as e:
        logging.error(f"Error connecting to {APP_URL}: {e}")
        return 'down'

def main():
    logging.info("Application Health Checker Script Started")
    while True:
        status = check_application_health()
        if status == 'up':
            logging.info(f"Application is UP. URL: {APP_URL}")
        else:
            logging.warning(f"Application is DOWN. URL: {APP_URL}")

        # Sleep for the specified interval before next check
        time.sleep(CHECK_INTERVAL)

if __name__ == "__main__":
    main()
