package demo;

import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.databind.util.StdDateFormat;

public class DateFormatTest {

    private static final Date EPOCH = new Date(0);

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
    public void testJodaDateTime() {
        DateTime epoch = new DateTime(EPOCH);
        System.out.println(epoch.getClass().getName() + ": " + epoch.toString());
    }

    @Test
    public void testStdDateFormat() {
        DateFormat dateFormat = new StdDateFormat();
        System.out.println(dateFormat.getClass().getName() + ": " + dateFormat.format(EPOCH.getTime()));
    }

    @Test
    public void testISO8601DateFormat() {
        DateFormat dateFormat = new ISO8601DateFormat();
        System.out.println(dateFormat.getClass().getName() + ": " + dateFormat.format(EPOCH.getTime()));
    }

    @Test
    public void testDateFormatUtils() {
        FastDateFormat dateFormat = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT;
        System.out.println(dateFormat.getClass().getName() + ": " + dateFormat.format(EPOCH));
    }
}