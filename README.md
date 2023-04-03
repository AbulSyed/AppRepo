# AppRepo

### Purpose
Application that allows users to share and find useful code repositories. Users can login using their GitHub credentials to share repositories.

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

- Endpoint invoked by GitHub after a user logs in
- GitHub makes a GET request, passing a `code` in the request param
- `Code` is then used to retrieve `access_token` from GitHub API

```
@Get localhost:8080/user/signin/callback?code=__code here__
```

- Endpoint then saves user details into database containing the fields:

```
private String login;
private String avatar_url;
private String html_url;
private String repos_url;
```

- Endpoint finally sends back a cookie containing `access_token` to client

###### Get user endpoints

- 2 endpoint were created to fetch the user details
- Initially client was calling (1), as at the time - Callback API endpoint was sending back the username to the client, but I then updated Callback endpoint to send back `access_token`
- Endpoint (2) requires `access_token`
- Both endpoints make a call to database to fetch user information

```
(1) @Get localhost:8080/getUser/{username}

(2) @Post localhost:8080/getUser

> request for (2)
{
    "token": "__access token__"
}

> expected response for (1) & (2)
{
    "login": "AbulSyed",
    "avatar_url": "https://avatars.githubusercontent.com/u/49309184?v=4",
    "html_url": "https://github.com/AbulSyed",
    "repos_url": "https://api.github.com/users/AbulSyed/repos"
}
```

##### Repo Service

###### Get repos

- Makes a call to GitHub API, to fetch the users repositories
- Response from GitHub API is then mapped to a POJO
- @JsonIncludeProperties annotation used to include certain fields

```
@Get localhost:8081/getRepos/{username}

> response is a list of objects
> each object represents a repository created by the user on GitHub
[
    {
        "name": "post-app",
        "html_url": "https://github.com/AbulSyed/post-app",
        "description": "App which allows users to share posts built using MEVN stack",
        "clone_url": "https://github.com/AbulSyed/post-app.git",
        "language": "Vue"
    }
...
]
```

###### Share repo

- Endpoint which allows users to share their repositories

```
@Post localhost:8081/shareRepo

> request body
{
    "name": "spring boot API",
    "description": "my first API",
    "html_url": "http://...",
    "language": "Java",
    "clone_url": "http://...",
    "category": "API",
    "tech": [
      "Java",
      "Spring Boot",
      "JPA"
    ]
}

> response
{
    "id": 61,
    "name": "spring boot API",
    "description": "my first API",
    "html_url": "http://...",
    "language": "Java",
    "clone_url": "http://...",
    "category": "API",
    "tech": [
        "Java",
        "Spring Boot",
        "JPA"
    ],
    "starred": false
}
```

- Starred field added, which checks if the repository has been `starred`, in other words - liked by the current user

###### Get shared repositories

- Endpoint fetches all shared repositories from database

```
@Post localhost:8081/getSharedRepos

> request body
{
    "username": "AbulSyed"
}

> response
[
    {
      "id": 61,
      "name": "spring boot API",
      "description": "my first API",
      "html_url": "http://...",
      "language": "Java",
      "clone_url": "http://...",
      "category": "API",
      "tech": [
            "Java",
            "Spring Boot",
            "JPA"
      ],
      "starred": false
    }
...
]
```

###### Get starred repositories

- Endpoint uses the previous endpoint and just returns repositorys `starred` by a user
- Also formats to a map data structure

```
@Post localhost:8081/getStarredRepos

> request body
{
    "username": "AbulSyed"
}

> response

Map<String, List<RepoDtos>>

{
    "API": [
      {...},
      ...
    ],
    ...
}

Where key: Category (API, IAC, SCRIPT, etc.), value: list of repositories
```

###### Star a shared repository

- Endpoint used to `star` / `unstar` a repository

```
@Post localhost:8081/starRepo

> request body
{
    "starredBy": "AbulSyed",
    "repoId": 46
}

> response

String response "Repo added to favourites" | "Repo removed from favourites"
```
