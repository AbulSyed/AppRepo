#chmod +x ./start.sh - to make executable
echo "starting eureka-server"
cd ./eureka-server
mvn spring-boot:run &
EUREKA_SERVER_PID=$!
cd ..

echo "starting gateway-server"
cd ./gateway-server
mvn spring-boot:run &
GATEWAY_SERVER_PID=$!
cd ..

echo "starting auth-service"
cd ./auth-service
mvn spring-boot:run &
AUTH_SERVICE_PID=$!
cd ..

echo "starting repo-service"
cd ./repo-service
mvn spring-boot:run &
REPO_SERVICE_PID=$!
cd ..

echo "starting feedback-service"
cd ./feedback-service
mvn spring-boot:run &
FEEDBACK_SERVICE_PID=$!
cd ..

echo "starting client"
cd ./client
npm run dev
CLIENT_PID=$!
cd ..

# Trap command used to capture the interrupt signal (ctrl + c) and execute the specified command to shut down all processes
trap "echo 'Shutting down'; pkill -P $EUREKA_SERVER_PID; pkill -P $GATEWAY_SERVER_PID; pkill -P $AUTH_SERVICE_PID; pkill -P $REPO_SERVICE_PID; pkill -P $FEEDBACK_SERVICE_PID; pkill -P $CLIENT_PID; exit 0" INT

# Wait for all processes to finish
wait