FROM python:latest
WORKDIR /app

RUN pip install psycopg2
RUN pip install python-dateutil

COPY script_populate_database.py script_populate_database.py
CMD ["python", "./script_populate_database.py"]