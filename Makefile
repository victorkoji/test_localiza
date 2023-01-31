up:
	docker-compose rm -f & \
    docker-compose up --build

up-es:
	docker-compose rm -f & \
	docker-compose up --build -d

database-populate:
	cd ./data &
	python ./data/script_populate_database.py
	cd ..

down:
	docker-compose down
