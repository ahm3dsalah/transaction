package com.test.api.util;

import com.test.api.entity.BankAccount;
import com.test.api.entity.BankTransaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        configuration.addAnnotatedClass(BankAccount.class);
        configuration.addAnnotatedClass(BankTransaction.class);
        return configuration.buildSessionFactory(builder.build());
    }
}
