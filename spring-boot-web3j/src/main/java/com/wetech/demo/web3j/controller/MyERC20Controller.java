package com.wetech.demo.web3j.controller;

import com.wetech.demo.web3j.service.MyERC20Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/erc20")
public class MyERC20Controller {

    @Autowired
    private MyERC20Service myERC20Service;

    @PostMapping("/deploy")
    public String deploy() {
        try {
            return myERC20Service.deploy();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/mint")
    public String mint(@RequestParam("amount") BigInteger amount) {
        try {
            return myERC20Service.mint(amount);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam String to, @RequestParam BigInteger amount) {
        try {
            return myERC20Service.transfer(to, amount);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/balance")
    public BigInteger balanceOf(@RequestParam String address) {
        try {
            return myERC20Service.balanceOf(address);
        } catch (Exception e) {
            return BigInteger.valueOf(-1);
        }
    }

    @PostMapping("/approve")
    public String approve(@RequestParam String spender, @RequestParam BigInteger amount) {
        try {
            return myERC20Service.approve(spender, amount);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/transferFrom")
    public String transferFrom(@RequestParam String from, @RequestParam String to, @RequestParam BigInteger amount) {
        try {
            return myERC20Service.transferFrom(from, to, amount);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}