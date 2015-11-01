package com.genericmethod.stormpathplayground.web.model;

import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.application.ApplicationList;
import com.stormpath.sdk.application.Applications;
import com.stormpath.sdk.tenant.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author cfarrugia
 */
@Component
public class StormPathApplication {

    @Autowired
    StormpathClient stormpathClient;
    private Application application;
    private Tenant tenant;

    public StormPathApplication(){}

    @PostConstruct
    public void init(){
        Tenant tenant = stormpathClient.getClient().getCurrentTenant();
        ApplicationList applications = tenant.getApplications(
                Applications.where(Applications.name().eqIgnoreCase("My Application"))
        );
        application = applications.iterator().next();
    }

    public Application getApplication() {
        return application;
    }

    public Tenant getTenant() {
        return tenant;
    }
}
