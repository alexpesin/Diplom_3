package org.example.data;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;


public class UserGenerator {
    @Step("Генерация пароля пользователя")
    public static String generateUserPassword(int len) {
        return RandomStringUtils.randomAlphabetic(len);
    }

    @Step("Генерация емейла пользователя")
    public static String generateUserEmail() {
        return RandomStringUtils.randomAlphabetic(5).toLowerCase() + "@test.com";
    }

    @Step("Генерация имени пользователя")
    public static String generateUserName() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    @Step("Генерация длины пароля пользователя")
    public static int generateUserPasswordLength(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    @Step("Генерация пользователя")
    public static User generateUser() {
        String name = generateUserName();
        String email = generateUserEmail();
        String password = generateUserPassword(10);
        return new User(name, email, password);
    }

}
