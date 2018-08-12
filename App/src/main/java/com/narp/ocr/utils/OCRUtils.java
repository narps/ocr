package com.narp.ocr.utils;

import com.google.gson.Gson;

public class OCRUtils {
	
	private static Gson gson = new Gson();
	
	public static String convertObjectToJSON(Object object) throws Exception {
        if (object == null) {
            return null;
        }
        String jsonResponse = gson.toJson(object);
        return jsonResponse;
    }
	
	public static Object convertJSONToObject(String jsonRequest, Class objectClassType) throws Exception {
        Object object = gson.fromJson(jsonRequest, objectClassType);
        return object;
    }
}