package ru.job4j.io;

import org.junit.Test;

import java.io.IOException;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class ConfigTest {

    /**
     * Check read operations.
     * @throws IOException possible.
     */
    @Test
    public void whenReadConfig() throws IOException {
        Config config = new Config("./../app.properties");
        config.load();
        System.out.println(config);
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }

}