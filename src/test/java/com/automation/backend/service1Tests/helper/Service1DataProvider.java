package com.automation.backend.service1Tests.helper;

import com.github.javafaker.Faker;

public class Service1DataProvider {

    static Faker faker = new Faker();

    @org.testng.annotations.DataProvider(name = "createUser")
    public static Object[][] createUser() {
        return new Object[][]{{faker.name().firstName(), faker.job().title()}, {faker.name().firstName(), faker.job().title()}};
    }
}
