package com.gl.webautomation.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility Class that provides the implementation to read a JSON file and
 * return a corresponding JSON object as defined in org.json package
 */
public class JSONParser {
    /**
     * Reads a JSON file and return a JSON object
     *
     * @param path of the JSON file, including the name
     * @return the json object corresponding to the JSON file
     * @throws IOException   the io exception
     * @throws JSONException the json exception
     */
    public static JSONObject getJSONObjectForAPage(String path)
            throws IOException, JSONException
    {
        String fileContent =   new String(Files.readAllBytes(Paths.get(path)));
        return (new JSONObject(fileContent));
    }

    public static JSONObject getJSONObjectForAResource(URL resourceURL)
            throws IOException, JSONException, URISyntaxException {
        String fileContent =   new String(Files.readAllBytes(Paths.get(resourceURL.toURI())));
        return (new JSONObject(fileContent));
    }
}
