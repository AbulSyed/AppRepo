FROM mysql

ENV MYSQL_ROOT_PASSWORD=my-secret-pw

# COPY ./database_user.sql /docker-entrypoint-initdb.d/
# COPY ./database_repo.sql /docker-entrypoint-initdb.d/
COPY ./database_apprepo.sql /docker-entrypoint-initdb.d/

# docker build . -t mysql_db
# docker run -d -p 3306:3306 mysql_db
# docker exec -it <container-id> /bin/bash
# mysql -pmy-secret-pw
# show databases; use apprepo; show tables; SELECT * FROM apprepo.user;