import psycopg2
import csv
import datetime
from datetime import datetime
from dateutil import tz

class Main():
  def __init__(self):
    self.HOST = 'localhost'
    self.USER = 'postgres'
    self.DB_NAME = 'postgres'
    self.PASSWORD = 'postgres'
    self.PORT = '5432'

    self.conn = psycopg2.connect(
        host=self.HOST, dbname=self.DB_NAME, user=self.USER, password=self.PASSWORD, port=self.PORT
    )

  def convertDate(self, date):
    return datetime.strptime(date, '%a %b %d %Y %H:%M:%S GMT-0200 (Hora oficial do Brasil)').strftime('%Y-%m-%d %H:%M:%S')

  def populatePointInterestTable(self):
    cur = self.conn.cursor()

    cur.execute("TRUNCATE TABLE point_interest;")

    with open('./base_pois_def.csv', 'r') as f:
      reader = csv.reader(f)
      next(reader)
      for row in reader:
        cur.execute(
          "INSERT INTO point_interest(nomepoi, raio, latitude, longitude) VALUES (%s, %s, %s, %s)",
          row
        )

    f.close()
    self.conn.commit()

  def populatePositionTable(self):
    cur = self.conn.cursor()

    cur.execute("TRUNCATE TABLE position;")

    with open('./posicoes.csv', 'r') as f:
      reader = csv.reader(f)
      next(reader)
      for row in reader:
        row[1] = self.convertDate(row[1])
        cur.execute(
          "INSERT INTO position(placa, data_posicao, velocidade, latitude, longitude, ignicao) VALUES (%s, %s, %s, %s, %s, %s)",
          row
        )

    f.close()
    self.conn.commit()

  def execute(self):
    self.populatePointInterestTable()
    self.populatePositionTable()

Main().execute()