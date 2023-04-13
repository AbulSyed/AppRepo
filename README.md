# AppRepo

### Purpose

Application enabling code share and re-use. Users can login using their GitHub credentials to share, find & discover repositories.

### Architecture overview

```
                                     |----------|     |--------------|
                               |-----| AUTH API |-----| GITHUB OAUTH |
|--------|     |---------|     |     |----------|     |--------------|
| Client |-----| GATEWAY |-----|
|--------|     |---------|     |     |----------|
                               |-----| REPO API |
                                     |----------|
```

### Technologies

#### UI
- React & TypeScript
- Redux Toolkit
- Ant Design

#### Backend
- Spring Boot
- Spring Cloud Gateway
- Spring Cloud Netflix Eureka Server
- MySQL/Spring Data JPA

API endpoints:

##### Auth Service

###### Callback endpoint

```
@Get localhost:8080/user/signin/callback?code=__code here__
```

- Endpoint invoked by GitHub after a user logs in
- GitHub makes a GET request, passing a `code` in the request param
- `Code` is then used to retrieve `access_token` from GitHub API
- Endpoint then saves user details into database
- Endpoint finally sends back a cookie containing `access_token` to client

###### Get user endpoints

- 2 endpoint were created to fetch the user details
- Initially client was calling (1), as at the time - Callback API endpoint was sending back the username to the client, but I then updated Callback endpoint to send back `access_token`
- Endpoint (2) requires `access_token`
- Both endpoints make a call to database to fetch user information

```
(1) @Get localhost:8080/getUser/{username}

(2) @Post localhost:8080/getUser
```

##### Repo Service

###### Get repos

- Makes a call to GitHub API, to fetch the users repositories
- Response from GitHub API is then mapped to a POJO
- @JsonIncludeProperties annotation used to include certain fields

```
@Get localhost:8081/getRepos/{username}
```

###### Share repo

- Endpoint which allows users to share their repositories

```
@Post localhost:8081/shareRepo
```

###### Get shared repositories

- Endpoint fetches all shared repositories from database

```
@Post localhost:8081/getSharedRepos
```

###### Get starred repositories

- Endpoint uses the previous endpoint and just returns repositorys `starred` by a user
- Also formats to a map data structure

```
@Post localhost:8081/getStarredRepos
```

###### Star a shared repository

- Endpoint used to `star` / `unstar` a repository

```
@Post localhost:8081/starRepo
```