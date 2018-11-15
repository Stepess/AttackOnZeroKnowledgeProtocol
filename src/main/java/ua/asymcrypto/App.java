package ua.asymcrypto;

import ua.asymcrypto.model.util.GsonParser;
import ua.asymcrypto.model.util.NumUtil;
import ua.asymcrypto.model.util.URLUtil;

import java.math.BigInteger;

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

        String jsonData = URLUtil.readJSONFromURL(URL_TO_MODULUS);

        BigInteger modulus = GsonParser.parseBigIntegerFieldFromJson(jsonData, MODULUS);
        System.out.println(modulus.toString(16));


        int counter = 0;
        BigInteger answer = BigInteger.ZERO;
        BigInteger shot;

        do {

            counter++;
            do {
                //shot= NumUtil.generateRandomBigInteger(modulus);
                shot= NumUtil.generateQuadraticDeduction(modulus);
                BigInteger y = shot.pow(2);
                System.out.println(NumUtil.calculateJacobiSymbol(y, modulus));
                String query = URL_TO_ROOT_WT_PARAMETER + y.toString(16);
                String answerJson = URLUtil.readJSONFromURL(query);
                if (!answerJson.isEmpty()) {
                    counter--;
                    answer = GsonParser.parseBigIntegerFieldFromJson(answerJson, ROOT);
                    break;
                }
            } while (true);


            System.out.println(shot);
            System.out.println(answer);


        } while (answer.equals(shot) || answer.equals(shot.subtract(modulus)));

        BigInteger p = shot.add(answer).gcd(modulus);
        BigInteger q = modulus.divide(p);

        System.out.println("attempts: " + counter);
        System.out.println("modulus = " + modulus.toString(16));
        System.out.println("p = " + p.toString(16));
        System.out.println("q = " + p.toString(16));
        System.out.println("p*q = " + p.multiply(q).toString(16));



    }
}
