package com.genericmethod.stormpathplayground.web;

import com.genericmethod.stormpathplayground.web.model.StormpathClient;
import com.genericmethod.stormpathplayground.web.service.UserAccountService;
import com.stormpath.sdk.account.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StormpathPlaygroundWebApplication.class)
@WebAppConfiguration
public class StormpathPlaygroundWebApplicationTests {

	@Autowired
	UserAccountService userAccountService;
	@Autowired
	StormpathClient stormpathClient;

	@Test
	public void testCreateAccount() {

		Account account =  stormpathClient.getClient().instantiate(Account.class);
		account.setUsername("cfarrugia");
		account.setEmail("cfarrugia@gmail.com");
		account.setPassword("Cfarrugia123");
		account.setGivenName("Christopher");
		account.setSurname("Farrugia");
		userAccountService.create(account);
	}

	@Test
	public void testAuthenticateAccount() {
		Account account = userAccountService.authenticate("cfarrugia","Cfarrugia123");
		assertNotNull(account);
	}

	@Test
	public void testFindAccount(){
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("surname", "Farrugia");
		Account account = userAccountService.findAccount(queryParams).single();
		assertNotNull(account);
	}

}
