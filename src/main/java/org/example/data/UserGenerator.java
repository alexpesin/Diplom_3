package org.example.data;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;


public class UserGenerator {

    public static String generateUserPassword(int len){
        return RandomStringUtils.randomAlphabetic(len);
    }

    public static String generateUserEmail(){
        return RandomStringUtils.randomAlphabetic(5).toLowerCase() + "@test.com";
    }

    public static String generateUserName(){
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    public static int generateUserPasswordLength(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public static User generateUser(){
        String name = generateUserName();
        String email = generateUserEmail();
        String password = generateUserPassword(10);
        return new User(name,email,password);
    }

}
