# !/bin/sh

./gradlew clean
./gradlew build

docker buildx build --platform=linux/amd64 -t anzanda/mrdang-backend --no-cache .
docker push anzanda/mrdang-backend
