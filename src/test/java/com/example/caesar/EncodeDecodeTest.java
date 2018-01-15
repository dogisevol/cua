package com.example.caesar;

import com.example.caesar.service.CaesarService;
import com.example.caesar.service.CaesarServiceExeption;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = CaesarCypherApp.class
)
@AutoConfigureMockMvc
public class EncodeDecodeTest {

    @Autowired
    private CaesarService caesarService;

    @Before
    public void setUp() {
    }

    @Test
    public void testEn1() throws CaesarServiceExeption {
        test("en", "z", "a");
    }

    @Test
    public void testEn2() throws CaesarServiceExeption {
        test("en", "Encode fuzzy", "Ksgrff kycbz");
    }

    @Test
    public void testEn3() throws CaesarServiceExeption {
        test("en", "Encode - fuzzy!", "Ksgrff - kycbz!");
    }


    @Test
    public void testDe1() throws CaesarServiceExeption {
        test("de", "ß", "a");
    }

    @Test
    public void testDe2() throws CaesarServiceExeption {
        test("de", "Encode fuzzy", "Ksgrff kyüöz");
    }

    @Test
    public void testDe3() throws CaesarServiceExeption {
        test("de", "spaßigen Aktivitäten", "äwgemjgo Luüqüoyawgo");
    }

    @Test
    public void testRu1() throws CaesarServiceExeption {
        test("ru", "я", "а");
    }

    @Test
    public void testRu2() throws CaesarServiceExeption {
        test("ru", "Ёлки зелёные растут!", "Йомй окрйрэё цеххху!");
    }


    private void test(String lang, String decoded, String encoded) throws CaesarServiceExeption {
        assert encoded.equals(caesarService.encode(decoded, lang));
        assert decoded.equals(caesarService.decode(encoded, lang));

    }
}
