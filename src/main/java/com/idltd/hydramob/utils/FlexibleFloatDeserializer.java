package com.idltd.hydramob.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class FlexibleFloatDeserializer extends JsonDeserializer<Float> {

    @Override
    public Float deserialize(JsonParser parser, DeserializationContext context)
            throws IOException {
        String floatString = parser.getText();
        if (!floatString.equalsIgnoreCase("")) {
            if (floatString.contains(",")) {
                floatString = floatString.replace(",", ".");
            }
        }else floatString="0";
        return Float.valueOf(floatString);
    }
}
