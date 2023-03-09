package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public class UserService {
    private String authToken;
    RestTemplate restTemplate = new RestTemplate();

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    private final String BASE_URL;

    public UserService(String url){
        BASE_URL = url;
    }

    public BigDecimal getBalance(int id){
        BigDecimal balance = null;
        try{
            balance  = restTemplate.getForObject(BASE_URL+"/user/"+id+"/balance", BigDecimal.class);
        }catch (RestClientResponseException| ResourceAccessException e){
            BasicLogger.log(e.getMessage()+"-error getting balance");
        }
        return balance;

    }
    public User[] getAllUser(){
        User[] users = null;
        try{
            users = restTemplate.getForObject(BASE_URL+"/user/", User[].class);

        }catch (RestClientResponseException| ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return users;
    }
    public void printOtherUsers(int id){
        for(User user : getAllUser()){
            if(user.getId()==id)continue;
            System.out.println(user.getUsername() + " : "+ user.getId());
        }
    }
    public int userToAccount(int userId){
        int accountId = 0;
        try{
            accountId = restTemplate.getForObject(BASE_URL+"/user/"+userId+"/account/", int.class);

        }catch (RestClientResponseException| ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return accountId;
    }

    public int createTransfer(int status, int type, int fromAccount, int toAccount, BigDecimal amount){
        int transferId = 0;
        try{
            //transferId = restTemplate.postForObject(BASE_URL+"/transfer/", )

        }catch (RestClientResponseException| ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return 0;
    }

    private HttpEntity<Void> makeAuthEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        return new HttpEntity<>(headers);
    }

    private HttpEntity<Transfer> transferEntity (String token, Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        return new HttpEntity<>(transfer, headers);
    }
    private HttpEntity<User> userEntity (String token, User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        return new HttpEntity<>(user, headers);
    }
}
/*

Blank try catch for easy copy-paste
try{

        }catch (RestClientResponseException| ResourceAccessException e){
        BasicLogger.log(e.getMessage());
        }
        return
 */