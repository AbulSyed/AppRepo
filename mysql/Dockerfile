FROM mysql

ENV MYSQL_ROOT_PASSWORD=my-secret-pw

COPY ./database_user.sql /docker-entrypoint-initdb.d/
COPY ./database_repo.sql /docker-entrypoint-initdb.d/