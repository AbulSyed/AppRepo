#chmod +x ./mavenclean.sh - to make executable
echo "clean auth-service"
cd ./auth-service
mvn clean
cd ..

echo "clean eureka-server"
cd ./eureka-server
mvn clean
cd ..

echo "clean gateway-server"
cd ./gateway-server
mvn clean
cd ..

echo "clean repo-service"
cd ./repo-service
mvn clean
cd ..

echo "clean feedback-service"
cd ./feedback-service
mvn clean
cd ..
