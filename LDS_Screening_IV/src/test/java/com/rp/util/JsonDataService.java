package com.rp.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class JsonDataService {

    /**
     * Get the context data from the given JSON file.
     * @param rootFolder
     * @param jsonFileName
     * @param jsonNodeName
     * @param methodName
     * @return
     * @throws IOException
     */
    public Iterator<Object[]> getDataFromJson(String rootFolder, String jsonFileName, String jsonNodeName, String methodName)
            throws IOException {

        String testDataFolderPath = System.getProperty("user.dir") + "/" + rootFolder;
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> testData;
        String jsonContent;
        String jsonData = null;
        for (File testDataFile : Objects.requireNonNull(new File(testDataFolderPath).listFiles())) {
            if (!testDataFile.getAbsolutePath().contains("$")) {
                if (testDataFile.getPath().contains(jsonFileName)) {
                    try {
                        String filePath = testDataFolderPath + "/" + jsonFileName ;
                        jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
                        JsonNode jsonNode = mapper.readValue(jsonContent, JsonNode.class);
                       // jsonData=  jsonNode.toString();
                        jsonData = jsonNode.get(jsonNodeName).get(methodName).toString();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(jsonData == null){
            String filePath = testDataFolderPath + "/" + jsonFileName ;
            testData = getDataFromSingleJsonFile(filePath,jsonNodeName,methodName);
        }else{
            testData = mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>() {
            });
        }
        return getIterator(testData);
    }

    /**
     * An iterator over the JSON.
     * @param testDataList
     * @return
     */
    private Iterator<Object[]> getIterator(List<HashMap<String, String>> testDataList) {
        List<Object[]> iteratorList = new ArrayList<>();
        for (Map map : testDataList) {
            if (map.get("RunIteration").equals("Yes"))
                iteratorList.add(new Object[] { map });
        }
        return iteratorList.iterator();
    }

    /**
     * Map the data from a single JSON file.
     * @param filePath
     * @param nodeName
     * @param methodName
     * @return
     * @throws IOException
     */
    private List<HashMap<String, String>> getDataFromSingleJsonFile(String filePath, String nodeName, String methodName) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> testData;
        String jsonContent;
        String jsonData = null;
        try {
            jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
            JsonNode jsonNode = mapper.readValue(jsonContent, JsonNode.class);
            jsonData = jsonNode.get(nodeName).get(methodName).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        testData = mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>() {
        });

        return testData;
    }
}
