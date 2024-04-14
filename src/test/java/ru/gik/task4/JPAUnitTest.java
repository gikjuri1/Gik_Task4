package ru.gik.task4;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.util.DateUtil.now;
import static org.junit.jupiter.api.Assertions.assertNull;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class JPAUnitTest {
    // @Autowired
    //private TestEntityManager entityManager;

    @Autowired
    private MyDBWork repository;

    @Test
    public void should_store_a_user() {
        User user = repository.save(new User("TestUser", "TestFIO"));

        assertThat(user).hasFieldOrPropertyWithValue("username", "TestUser");
        assertThat(user).hasFieldOrPropertyWithValue("fio", "TestFIO");
    }

}
