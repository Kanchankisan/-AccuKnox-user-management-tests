import os
import subprocess
import logging
from datetime import datetime

# Configure logging
logging.basicConfig(filename='backup.log', level=logging.INFO, format='%(asctime)s - %(message)s')

# Backup configuration
SOURCE_DIR = '/path/to/source/directory'
REMOTE_USER = 'remote_user'
REMOTE_HOST = 'remote_host'
REMOTE_DIR = '/path/to/remote/directory'
SSH_KEY = '/path/to/ssh/key'

def backup_directory():
    timestamp = datetime.now().strftime('%Y-%m-%d_%H-%M-%S')
    backup_command = [
        'rsync', '-avz', '-e', f'ssh -i {SSH_KEY}',
        SOURCE_DIR,
        f'{REMOTE_USER}@{REMOTE_HOST}:{REMOTE_DIR}_{timestamp}'
    ]

    try:
        result = subprocess.run(backup_command, check=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        logging.info(f'Backup successful: {result.stdout}')
        print(f'Backup successful: {result.stdout}')
    except subprocess.CalledProcessError as e:
        logging.error(f'Backup failed: {e.stderr}')
        print(f'Backup failed: {e.stderr}')

def main():
    logging.info('Starting backup operation')
    print('Starting backup operation')
    backup_directory()

if __name__ == '__main__':
    main()
