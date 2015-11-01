package com.genericmethod.stormpathplayground.web.service;

import com.genericmethod.stormpathplayground.web.model.StormPathApplication;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.account.AccountList;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.authc.UsernamePasswordRequest;
import com.stormpath.sdk.resource.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author cfarrugia
 */
@Component
public class UserAccountService {

    @Autowired private StormPathApplication stormPathApplication;

    public void create(Account account){
        stormPathApplication.getApplication().createAccount(account);
    }

    public AccountList findAccount(Map<String,Object> queryParams){
        return stormPathApplication.getApplication().getAccounts(queryParams);
    }

    public Account authenticate(String username, String rawPassword) {

        //Create an authentication request using the credentials
        AuthenticationRequest request = new UsernamePasswordRequest(username, rawPassword);

        //Now let's authenticate the account with the application:
        Account account = null;
        try {
            AuthenticationResult result = stormPathApplication.getApplication().authenticateAccount(request);
            account = result.getAccount();
        } catch (ResourceException ex) {
            System.out.println(ex.getStatus() + " " + ex.getMessage());
        }

        return account;
    }

}
