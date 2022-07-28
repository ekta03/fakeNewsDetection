package com.sales.generalutils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

public class JsonParsing {

	public static HashMap<?, ?> convertFileToJSON(String fileName) {
		Gson gson = new Gson();
		HashMap<?, ?> hm = null;
		try {
			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(new FileReader(fileName));
			hm = gson.fromJson(jsonElement, HashMap.class);
		} catch (FileNotFoundException e) {
		}
		return hm;
	}
}