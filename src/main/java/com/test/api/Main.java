package com.test.api;

import com.test.api.backend.dao.UserDao;
import com.test.api.entity.User;
import org.apache.log4j.Logger;
import com.test.api.util.SparkUtils;

import static spark.Spark.get;

public class Main {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Main.class);
        SparkUtils.createServerWithRequestLog(logger);

        get("/hello", (request, response) -> persistUser());



    }

    private static String persistUser(){
        User newUser = new User();
        newUser.setFirstName("Ahmed");
        newUser.setLastName("lastName");


        UserDao userDao = new UserDao();
        userDao.openCurrentSession();
        userDao.persistUser(newUser);
        userDao.closeCurrentSession();

        return "user created";
    }


}
