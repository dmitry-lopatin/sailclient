package com.sailclient.util;

import org.json.simple.parser.JSONParser;

public class FabricJsonParser extends JSONParser {

    private FabricJsonParser() {

    }

    public static JSONParser createInstance() {
        return new JSONParser();
    }
}
