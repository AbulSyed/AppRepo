#chmod +x ./mavenbuild.sh - to make executable
echo "building auth-service"
cd ./auth-service
mvn package -DskipTests
cd ..

echo "building eureka-server"
cd ./eureka-server
mvn package -DskipTests
cd ..

echo "building gateway-server"
cd ./gateway-server
mvn package -DskipTests
cd ..

echo "building repo-service"
cd ./repo-service
mvn package -DskipTests
cd ..

echo "building feedback-service"
cd ./feedback-service
mvn package -DskipTests
cd ..