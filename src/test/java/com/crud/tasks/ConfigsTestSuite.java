package com.crud.tasks;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.config.TrelloConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigsTestSuite {

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private TrelloConfig trelloConfig;
    @Autowired
    private CompanyConfig companyConfig;

    @Test
    public void testGetAdminConfig() {
        //Given
        //When
        String mail = adminConfig.getAdminMail();
        String name = adminConfig.getAdminName();
        //Then
        Assert.assertNotNull(mail);
        Assert.assertNotNull(name);
    }
    @Test
    public void testGetCompanyConfig() {
        //Given
        //When
        String mail = companyConfig.getCompanyMail();
        String name = companyConfig.getCompanyName();
        String phone = companyConfig.getCompanyPhone();
        //Then
        Assert.assertNotNull(mail);
        Assert.assertNotNull(name);
        Assert.assertNotNull(phone);
    }
    @Test
    public void testGetTrelloConfig() {
        //Given
        //When
        String endpoint = trelloConfig.getTrelloApiEndpoint();
        String appkey = trelloConfig.getTrelloAppKey();
        String token = trelloConfig.getTrelloToken();
        String user = trelloConfig.getUsername();
        //Then
        Assert.assertNotNull(endpoint);
        Assert.assertNotNull(appkey);
        Assert.assertNotNull(token);
        Assert.assertNotNull(user);
    }
}
