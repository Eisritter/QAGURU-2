package properties;

import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {
        @Test
        void someTest1() {
            String browser = System.getProperty("browser");
            System.out.println(browser); // null

        }
        @Test
        void someTest2() {
            System.setProperty("some_browser", "safari");
            String browser = System.getProperty("browser");
            System.out.println(browser); //safari
        }
        @Test
        void someTest3() {
            String browser = System.getProperty("browser", "opera");
            System.out.println(browser); // opera
        }
    }
