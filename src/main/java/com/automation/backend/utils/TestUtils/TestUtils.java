package com.automation.backend.utils.TestUtils;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class TestUtils {

    /*
     * This method will read a .json file and returns a JSONObject to make a payload
     * */
    public static JSONObject getJsonObject(String filePath) {
        BufferedReader br = null;
        StringBuilder sb = null;
        String line = null;
        JSONObject jsonObject = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            sb = new StringBuilder();
            line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            if (sb == null) sb = new StringBuilder("");
            jsonObject = new JSONObject(sb.toString());
        } catch (Exception e) {
            log.error("JsonUtility Class, getJsonObject() method, Error=" + e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }


}
