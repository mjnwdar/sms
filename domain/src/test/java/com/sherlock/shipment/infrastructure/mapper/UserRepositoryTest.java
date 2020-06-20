package com.sherlock.shipment.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.sherlock.shipment.common.model.entity.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/15 20:19
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserRepositoryTest {
    @Autowired
    private UserMapper userRepository;

    @Test
    public void test_load_user_data() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("admin");
        user.setPassword("123456");
        user.setSalt("xdeess");
        user.setEmail("test@aaa");
        user.setStatus(1);
        userRepository.save(user);
        final List<User> all = userRepository.findAll();
        assertEquals(all.size(), 2);
        all.forEach(System.out::println);
    }
}