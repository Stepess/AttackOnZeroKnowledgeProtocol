package ua.asymcrypto.model.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.math.BigInteger;

public class GsonParser {
    public static BigInteger parseBigIntegerFieldFromJson(String jsonLine, String field) {
        String result = parseStringFieldFromJson(jsonLine, field);
        return new BigInteger(result, 16);
    }

    public static String parseStringFieldFromJson(String jsonLine, String field) {
        JsonElement jsonElement = new JsonParser().parse(jsonLine);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        return jsonObject.get(field).getAsString();
    }

}
