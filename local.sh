# !/bin/sh

./gradlew clean
./gradlew build

docker compose down

docker build -t mrdang .
docker compose up -d --force-recreate