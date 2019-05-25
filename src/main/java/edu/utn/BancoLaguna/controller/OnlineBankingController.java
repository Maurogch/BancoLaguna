package edu.utn.BancoLaguna.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/online")
public class OnlineBankingController {
    //needed repos

    @PostMapping("")
    public void index(){}

}
