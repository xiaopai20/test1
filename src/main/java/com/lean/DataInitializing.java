package com.lean;

import com.lean.data.AccountManager;
import com.lean.data.CustomerManager;
import com.lean.data.Tokens;
import com.lean.data.TransactionManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DataInitializing implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        // NOTE: the resource seems only work with Gradle, doesn't work with mvn.
        // Please run test and run with Gradle
        AccountManager.getInstance().LoadData(new ClassPathResource("account.csv").getFile().getAbsolutePath());
        CustomerManager.getInstance().LoadData(new ClassPathResource("customer.csv").getFile().getAbsolutePath());
        TransactionManager.getInstance().LoadData(new ClassPathResource("transaction.csv").getFile().getAbsolutePath());
        Tokens.getInstance().LoadData(new ClassPathResource("whitelist.txt").getFile().getAbsolutePath());
    }
}
