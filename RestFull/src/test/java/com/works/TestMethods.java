package com.works;

import com.works.models.Currency;
import com.works.services.XmlService;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestMethods {

    XmlService xml = null;
    Random random = null;

    public TestMethods() {
        xml = new XmlService();
        System.out.println("TestMethods Call");
    }

    // Bütün test methodlarından önce bir kez çalış
    @BeforeAll
    public void beforeAll() {
        System.out.println("beforeAll Call");
    }

    // bütün test methodlarından önce her method için bir kez çalış
    @BeforeEach
    public void beforeEach() {
        random = new Random();
        System.out.println("beforeEach Call");
    }

    @Test
    @DisplayName("Xml Currency Size 10 Control ")
    public void test_1() {
        System.out.println( xml.hashCode() );
        List<Currency> ls = xml.result();
        System.out.println( random.nextInt(100) );
        Assertions.assertTrue( ls.size() > 10, "Currency Size Error" );
    }

    @DisplayName("i Value 20 equals ")
    @Test
    public void test_2() {
        System.out.println( xml.hashCode() );
        int i = 20;
        System.out.println( random.nextInt(100) );
        Assertions.assertTrue( i == 20, "i not equals 20");
    }

    @Test
    public void test_3() {
        int a = 10;
        Assertions.assertTrue(a > 10, "a not a > 10");
    }


    // bütün test methodlarından sonra her method için bir kez çalış
    @AfterEach
    public void afterEach() {
        random = null;
        System.out.println("afterEach Call");
    }

    @AfterAll
    public void afterAll() {
        xml = null;
        System.out.println("afterAll Call");
    }


}
