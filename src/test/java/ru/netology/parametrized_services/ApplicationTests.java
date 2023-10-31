package ru.netology.parametrized_services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//https://github.com/netology-code/jd-homeworks/blob/master/containers/task1/README.md
class ApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Container
    private final GenericContainer<?> myAppDev = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);

    @Container
    private final GenericContainer<?> myAppProd = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @Test
    void getDevContext() {
        ResponseEntity<String> forEntity = testRestTemplate.getForEntity("http://localhost:" +
                myAppDev.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals("Current profile is dev", forEntity.getBody());
    }

    @Test
    void getPropContext() {
        ResponseEntity<String> forEntity = testRestTemplate.getForEntity("http://localhost:" +
                myAppProd.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals("Current profile is production", forEntity.getBody());
    }
}
