package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;
    private String serviceCode;

    @Override
    public List<Account> findAccounts() {
       return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        // for academic purpose ... simulates an exception
        if(tripWire){
            throw new RuntimeException("No soup for you !!!");
        }
        List<Account> myAccount = new ArrayList<>();

        //create sample accounts
        Account temp1 = new Account("John", "Silver");
        Account temp2 = new Account("Madhu", "Platinum");
        Account temp3 = new Account("Luca", "Gold");
        // add them to our accounts list
        myAccount.add(temp1);
        myAccount.add(temp2);
        myAccount.add(temp3);

        return myAccount;
    }

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() +" : Doing my db work an account");

    }
    @Override
    public boolean doWork() {
        System.out.println(getClass() +" : doWork()");

        return false;
    }
    public String getName() {
        System.out.println(getClass() +" : getName()");
        return name;
    }
    public void setName(String name) {
        System.out.println(getClass() +" : setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() +" : getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() +" : setServiceCode()");
        this.serviceCode = serviceCode;
    }
}

