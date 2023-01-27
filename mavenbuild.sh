#chmod +x ./mavenbuild.sh - to make executable
echo "building auth-service"
cd ./auth-service
mvn clean install
cd ..

echo "building eureka-server"
cd ./eureka-server
mvn clean install
cd ..

echo "building gateway-server"
cd ./gateway-server
mvn clean install
cd ..

echo "building repo-service"
cd ./repo-service
mvn clean install
cd ..
