package demo;

import java.io.IOException;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import demo.model.User;

public class JacksonTest {

    private static ObjectMapper mapper = new ObjectMapper(); // create once, reuse

    @BeforeClass
    public static void setUp() {
        // SerializationFeature for changing how JSON is written

        // to enable standard indentation ("pretty-printing"):
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // to allow serialization of "empty" POJOs (no properties to serialize)
        // (without this setting, an exception is thrown in those cases)
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // to write java.util.Date, Calendar as number (timestamp):
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // DeserializationFeature for changing how JSON is read as POJOs:

        // to prevent exception when encountering unknown property:
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // to allow coercion of JSON empty String ("") to null Object value:
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    @Test
    public void testDataBinding() throws JsonGenerationException, JsonMappingException, IOException {
        DateTime birthday = new DateTime(1984, 11, 30, 0, 0);
        int age = DateTime.now().getYear() - birthday.getYear();

        User user = new User();
        user.setId("0001");
        user.setName("Roger");
        user.setBirthday(birthday.toGregorianCalendar());

        user.setAge(age);
        user.setLastLoginTime(new Date(System.currentTimeMillis()));
        user.setOnline(true);

        String result = mapper.writeValueAsString(user);
        System.out.println(result);
    }

    @Test
    public void testJodaTime() {
        System.out.println(DateTime.now().toString());
    }
}