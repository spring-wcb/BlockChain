package com.wetech.demo.web3j.service;

import com.wetech.demo.web3j.contracts.myerc20.MyERC20;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

@Service
public class MyERC20Service {

    @Autowired
    private Web3j web3j;

    private MyERC20 contract;

    public String deploy() throws Exception {
        contract = MyERC20.deploy(web3j, credentials(), new DefaultGasProvider()).send();
        return contract.getContractAddress();
    }

    public String mint(BigInteger amount) throws Exception {
        TransactionReceipt receipt = contract.mint(amount).send();
        return receipt.getTransactionHash();
    }

    public String transfer(String to, BigInteger amount) throws Exception {
        TransactionReceipt receipt = contract.transfer(to, amount).send();
        return receipt.getTransactionHash();
    }

    public BigInteger balanceOf(String address) throws Exception {
        return contract.balanceOf(address).send();
    }

    public String approve(String spender, BigInteger amount) throws Exception {
        TransactionReceipt receipt = contract.approve(spender, amount).send();
        return receipt.getTransactionHash();
    }

    public String transferFrom(String from, String to, BigInteger amount) throws Exception {
        TransactionReceipt receipt = contract.transferFrom(from, to, amount).send();
        return receipt.getTransactionHash();
    }

    // 从配置中获取凭证（需要添加这个方法）
    private org.web3j.crypto.Credentials credentials() {
        // 这里需要从配置中获取私钥
        return org.web3j.crypto.Credentials.create("feac9322909284790540daea9019ae3bb3cbe9712f77c8392a7db06e4909605a");
    }
}