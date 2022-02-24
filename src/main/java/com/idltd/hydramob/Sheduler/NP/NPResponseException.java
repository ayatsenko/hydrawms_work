package com.idltd.hydramob.Sheduler.NP;

import static com.idltd.hydramob.utils.StaticUtils.ConvertObjectToJson;

public class NPResponseException extends Exception {
    public NPResponseException(NPResponse npResponse) {
        super(ConvertObjectToJson(npResponse.getErrors()));
    }
}
