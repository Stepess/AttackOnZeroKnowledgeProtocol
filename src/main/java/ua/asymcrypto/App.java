package ua.asymcrypto;

import ua.asymcrypto.model.util.GsonParser;
import ua.asymcrypto.model.util.NumUtil;
import ua.asymcrypto.model.util.URLUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App
{
    private final static String URL_TO_MODULUS = "http://asymcryptwebservice.appspot.com/znp/serverKey";
    public static final String URL_TO_ROOT_WT_PARAMETER = "http://asymcryptwebservice.appspot.com/znp/challenge?y=";
    public static final String MODULUS = "modulus";
    public static final String ROOT = "root";

    public static void main( String[] args )
    {

//        String jsonData = URLUtil.readJSONFromURL(URL_TO_MODULUS);
//        BigInteger modulus = GsonParser.parseBigIntegerFieldFromJson(jsonData, MODULUS);
//        System.out.println(modulus.toString(16));

//        BigInteger modulus = new BigInteger("88DB9682EB47EAB9AFFA378EBD26944368126765630E5315EA8038CDFC922ED6757A056A8346CBBD4B73001685DA9659AC3D6781FA2D844E287220BFC6846A661F33D7CA8F8DE2E690DAC1F0A24EA87CC65512A813C5C97FD0365EBBA152310E23A1CB6156E8198E12CDEB2B9CDE0B4559E44BFF9080E496C2AFD557FAA04DDFB5874EA538294B256245EEE2920BE11C67D0CBA0F4A873C18204C9DB7E79CD9EF35E4B2EF73415A4CA31EF700CEE9E4FFEAD657F697AD55D7008CEDF7190BA841821C78DA5924AF3549112433E7C7DAE4DCC0DADCB949B8E36D4664F0397B8933DC210F7CC200C581743138EDCE6A64F4D6BA90276094CA666112B1CED3E7D79", 16);
//
//        int counter = 0;
//        BigInteger answer = BigInteger.ZERO;
//        BigInteger shot;
//
//        do {
//
//            counter++;
//            do {
//                //shot= NumUtil.generateRandomBigInteger(modulus);
//                shot= NumUtil.generateRandomBigInteger(modulus);
//                final BigInteger two = BigInteger.valueOf(2);
//                BigInteger y = shot.modPow(two, modulus);
//                //System.out.println(y.toString(16));
//                String query = URL_TO_ROOT_WT_PARAMETER + y.toString(16);
//                String answerJson = URLUtil.readJSONFromURL(query);
//                if (!answerJson.isEmpty()) {
//                    counter--;
//                    answer = GsonParser.parseBigIntegerFieldFromJson(answerJson, ROOT);
//                    System.out.println(answer.toString(16));
//                    break;
//                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } while (true);
//
//
//            System.out.println(shot);
//            System.out.println(answer);
//
//
//        } while (answer.equals(shot) || answer.equals(shot.subtract(modulus)));
//
//        BigInteger p = shot.add(answer).gcd(modulus);
//        BigInteger q = modulus.divide(p);
//
//        System.out.println("attempts: " + counter);
//        System.out.println("modulus = " + modulus.toString(16));
//        System.out.println("p = " + p.toString(16));
//        System.out.println("q = " + p.toString(16));
//        System.out.println("p*q = " + p.multiply(q).toString(16));

//        String jsonData = URLUtil.readJSONFromURL(URL_TO_MODULUS);
//        BigInteger modulus = GsonParser.parseBigIntegerFieldFromJson(jsonData, MODULUS);
//        System.out.println(modulus.toString(16));
//
//        String query = URL_TO_ROOT_WT_PARAMETER + "100000000";
//        String answerJson = URLUtil.readJSONFromURL(query);
//        System.out.println(GsonParser.parseStringFieldFromJson(answerJson,"root"));

        try {
            sendGet();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void sendGet() throws Exception {

        String url = URL_TO_ROOT_WT_PARAMETER + "100000000";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
}
