package com.syed.reposervice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepoController {

    @GetMapping
    public String hi() {
        return "hi from repo server";
    }
}
