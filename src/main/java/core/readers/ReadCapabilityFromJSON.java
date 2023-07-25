package core.readers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReadCapabilityFromJSON {

    final static Logger log = Logger.getLogger(ReadCapabilityFromJSON.class);

    private static Map<String, Object> capabilityMap = null;
    private static boolean IOSDriver = false;
    private static boolean AndroidDriver = false;

    public ReadCapabilityFromJSON() { }

    public static DesiredCapabilities getDeviceCapabilities(String file) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilityMap = new HashMap<>();
        String filePath = System.getProperty("user.dir") + File.separator + "devices" + File.separator + file;
        log.info("Reading capabilities from file: " + filePath);
        try {
            JsonParser jsonParser = new JsonParser();
            FileReader fileReader = new FileReader(filePath);
            Object obj = jsonParser.parse(fileReader);
            JsonObject jsonObject = (JsonObject) obj;
            Set<String> keySet = jsonObject.keySet();
            for(String key: keySet) {
                JsonPrimitive jObject = jsonObject.get(key).getAsJsonPrimitive();
                String value = jObject.toString().replace("\"", "");
                if(key.contains("app")) {
                    if(value.startsWith("/apps") || value.startsWith("//apps"))
                        value = System.getProperty("user.dir") + File.separator + value;
                }
                if(key.contains("platformName")) {
                    if(value.equalsIgnoreCase("ios")) {
                        log.info("Platform name: ios");
                        IOSDriver = true;
                    }
                    else if(value.equalsIgnoreCase("android")) {
                        log.info("Platform name: android");
                        AndroidDriver = true;
                    }
                }
                log.info(key + " = " + value);
                capabilityMap.put(key, value);
                capabilities.setCapability(key, value);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return capabilities;
    }

    public static boolean is_IOSDriver() {
        return IOSDriver;
    }

    public static boolean is_AndroidDriver() {
        return AndroidDriver;
    }

    public static void main(String[] args)  {
        DesiredCapabilities capabs = ReadCapabilityFromJSON.getDeviceCapabilities("iphone13Simulator.json");
    }

}
