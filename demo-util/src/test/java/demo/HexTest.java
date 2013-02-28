package demo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class HexTest {

    @Test
    public void testHex() throws IOException, DecoderException {
        String hexStr = "EBB7EF914C29A6459A34EDCB61EB8C8F";
        byte[] data = Hex.decodeHex(hexStr.toCharArray());

        File outputFile = new File("temp.dat");
        FileUtils.writeByteArrayToFile(outputFile, data);

        String base64Str = Base64.encodeBase64String(data);
        System.out.println(base64Str);
    }

    @Test
    public void testBase64() throws DecoderException {
        String uuid = "ED11C880-FA56-11DD-A604001EC203A3AD"; // 36
        String hexStr = uuid.replace("-", ""); // 32
        byte[] data = Hex.decodeHex(hexStr.toCharArray());


        String base64Str = Base64.encodeBase64URLSafeString(data);
        System.out.println(base64Str);
    }

    @Test
    public void testC22() {
        String c22Str = "oucIZjgq4Tg62803kedwLG";
        byte[] data = Base64.decodeBase64(c22Str);
        String hexStr = Hex.encodeHexString(data);
        System.out.println("C22:" + c22Str);
        System.out.println("Hex:" + hexStr);

        //CB89928EDAB411DA86088003BA89FA55
    }
}