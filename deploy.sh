# !/bin/sh

./gradlew clean
./gradlew build

docker buildx build --platform=linux/amd64 -t mrdang-backend --no-cache .
docker tag mrdang-backend skkussa/mrdang-backend:0.0.1
docker push skkussa/mrdang-backend:0.0.1
