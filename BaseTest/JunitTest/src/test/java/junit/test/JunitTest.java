package junit.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by aharon on 7/21/15.
 */
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations = "classpath:/META-INF/spring/sample-pu-mini.xml")
    @DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public class JunitTest {
        @Test
        public void test1() {
            System.out.println("----- test 1 -------");
        }

        @Test
        public void test2() {
            System.out.println("----- test 2 -------");
        }

    }

