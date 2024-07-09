package com.gxnu;

import com.gxnu.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ComputerManagementSystemApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(MD5Util.encrypt("123456"));
    }

}
