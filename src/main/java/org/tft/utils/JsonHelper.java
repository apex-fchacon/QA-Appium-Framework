package org.tft.utils;

//import org.checkerframework.checker.units.qual.A;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.util.*;

public class JsonHelper {
    public JsonHelper(){
    }

    public Double getDoubleValue(String filePath, Object... keys){
        Object value = this.getDataFromJsonFile(filePath, keys);
        if(value instanceof Integer){
            return ((Integer) value).doubleValue();
        }else if(value instanceof Float){
            return ((Float) value).doubleValue();
        }else if(value instanceof Double){
            return ((Double) value).doubleValue();
        }else if(value instanceof String){
            return Double.parseDouble((String) value);
        }else{
            return value instanceof BigDecimal ? ((BigDecimal) value).doubleValue() : null;
        }
    }

    public Boolean getBooleanValue(String filePath, Object... keys){
        Object value = this.getDataFromJsonFile(filePath, keys);
        return value instanceof Boolean ? (Boolean) value : null;
    }

    public boolean containsKeysAndValues(Object object, Map<String, Object> expectedResponse){
        return this.containsKeysAndValues(object, new JSONObject(expectedResponse));
    }

    public boolean containsKeysAndValues(Object object, JSONObject expectedResponse){
        List<Object> nestedItems = new ArrayList();
        Object value;
        if (object instanceof JSONArray jsonArray){
            HashMap<String, Object> rootItems = new HashMap();
            int index = 0;
            Iterator var7 = jsonArray.iterator();

            while(true){
                while (var7.hasNext()){
                    value = var7.next();
                    if (!(value instanceof JSONObject) && !(value instanceof JSONArray)){
                        rootItems.put(Integer.toString(index), value);
                        ++index;
                    }else{
                        nestedItems.add(value);
                    }
                }
                if (!rootItems.isEmpty()){
                    return this.compareValues(new JSONObject(rootItems), expectedResponse.toMap());
                }
                break;
            }
        }else{
            JSONObject jsonObject = (JSONObject) object;
            if (jsonObject.toString().equals(expectedResponse.toString())){
                return true;
            }

            if (jsonObject.keySet().containsAll(expectedResponse.keySet())){
                return this.compareValues(jsonObject, expectedResponse.toMap());
            }
            Iterator var12 = jsonObject.keySet().iterator();

            label62:
            while (true){
                do{
                    if(!var12.hasNext()){
                        break label62;
                    }
                    String key = (String) var12.next();
                    value = jsonObject.get(key);
                }while (!(value instanceof JSONObject) && !(value instanceof JSONArray));
                nestedItems.add(value);
            }
        }

        Iterator var9 = nestedItems.iterator();

        Object item;
        do{
            if (!var9.hasNext()){
                return false;
            }
            item = var9.next();
        } while (!this.containsKeysAndValues(item, expectedResponse));
        return true;
    }

    public Float getFloatValue(String filePath, Object... keys){
        Object value = this.getDataFromJsonFile(filePath, keys);
        if(value instanceof Integer){
            return ((Integer) value).floatValue();
        } else if (value instanceof Float) {
            return (Float) value;
        } else if (value instanceof Double) {
            return ((Double) value).floatValue();
        } else if (value instanceof String) {
            return Float.parseFloat((String) value);
        } else{
            return value instanceof BigDecimal ? ((BigDecimal) value).floatValue() : null;
        }
    }

    public Integer getIntegerValue(String filePath, Object... keys){
        Object value = this.getDataFromJsonFile(filePath, keys);
        return value instanceof Integer ? (Integer) value : null;
    }

    public String getStringValule(String filePath, Object... keys){
        Object value = this.getDataFromJsonFile(filePath, keys);
        return value instanceof String ? value.toString() : null;
    }

    public List<?> getItemsList(String filePath, Object... keys){
        Object value = this.getDataFromJsonFile(filePath, keys);
        List<?> list = new ArrayList();

        assert value != null;

        if (value.getClass().isArray()){
            list = List.of(value);
        }else if (value instanceof Collection){
            list = new ArrayList((Collection) value);
        }

        return (List) list;
    }

    public JSONObject getJsonObjectValue(String filePath, Object... keys){
        Object value = this.getDataFromJsonFile(filePath, keys);
        return value instanceof  JSONObject ? (JSONObject) value : null;
    }

    public JSONArray getJsonArrayObject(String filePath, Object... keys){
        Object value = this.getDataFromJsonFile(filePath, keys);
        return value instanceof JSONArray ? (JSONArray) value : null;
    }

    private boolean compareValues(JSONObject foundValues, Map<String, Object> expectedValues){
        if(foundValues.keySet().containsAll(expectedValues.keySet())){
            String currentKey = "";

            try {
                Iterator var4 = expectedValues.keySet().iterator();

                boolean status;
                do {
                    if (!var4.hasNext()){
                        return true;
                    }

                    String key = (String) var4.next();
                    status = false;
                    Object tempValue = expectedValues.get(key);
                    if(tempValue instanceof Integer){
                        status = Objects.equals(this.getIntegerValue(foundValues.toString(), key), tempValue);
                    }
                    if(tempValue instanceof Boolean){
                        status = Objects.equals(this.getBooleanValue(foundValues.toString(), key), tempValue);
                    }
                    if(tempValue instanceof Double){
                        status = Objects.equals(this.getDoubleValue(foundValues.toString(), key), tempValue);
                    }
                    if(tempValue instanceof BigDecimal){
                        status = Objects.equals(this.getDoubleValue(foundValues.toString(), key), ((BigDecimal) tempValue).doubleValue());
                    }
                    if(tempValue instanceof Float){
                        status = Objects.equals(this.getFloatValue(foundValues.toString(), key), tempValue);
                    }
                    if(tempValue instanceof String){
                        status = Objects.equals(this.getStringValule(foundValues.toString(), key), tempValue);
                    }
                }while(status);

                return status;
            }catch (JSONException var9){
                foundValues.remove(currentKey);
                Map<String, Object> tempMap = new HashMap();
                int index = 0;

                for (Iterator var7 = foundValues.toMap().values().iterator(); var7.hasNext(); ++index){
                    Object item = var7.next();
                    tempMap.put(Integer.toString(index), item);
                }
                return this.compareValues(new JSONObject(tempMap), expectedValues);
            }
        }else{
            return false;
        }
    }

    private Object getDataFromJsonFile(String filePath, Object...keys){
        Object jsonItem;
        try{
            jsonItem = new JSONObject(filePath);
        }catch (JSONException var10){
            jsonItem = this.openJsonFile(filePath);
        }

        Object[] var4 = keys;
        int var5 = keys.length;

        for(int var6=0; var6<var5; ++var6){
            Object key = var4[var6];
            String message;
            String var10000;
            if (jsonItem instanceof JSONArray){
                try{
                    if(key instanceof String){
                        key = Integer.parseInt((String) key);
                    }
                    jsonItem = ((JSONArray) jsonItem).get((Integer) key);
                }catch (JSONException var11){
                    var10000 = String.valueOf(key);
                    message = "The specified key '" + var10000 + "' is not a position in the specified array.\nException Message\n" + var11.getMessage();
                    Assert.fail(message);
                    break;
                }catch (NumberFormatException var12){
                    NumberFormatException exception = var12;
                    var10000 = String.valueOf(key);
                    message = "The specified key '" + var10000 + "' is not a number.\nException Message\n" + exception.getMessage();
                    Assert.fail(message);
                    break;
                }
            }else{
                if (!((JSONObject) jsonItem).has((String) key)){
                    Assert.fail("The specified key '" + String.valueOf(key) + "' was not found in the json file.");
                    break;
                }

                try{
                    jsonItem = ((JSONObject) jsonItem).get((String) key);
                }catch (JSONException var13){
                    var10000 = String.valueOf(key);
                    message = "The specified key '" + var10000 + "' is not a Json object.\nException Message\n"+ var13.getMessage();
                    Assert.fail(message);
                    break;
                }
            }
        }
        return jsonItem;
    }

    public JSONObject openJsonFile(String filePath){
        File file = new File(filePath);
        if (file.exists() && file.isFile() && file.canRead()){
            try{
                String jsonFile = new String(Files.readAllBytes(Paths.get(file.toURI())));
                return new JSONObject(jsonFile);
            }catch (IOException | JSONException var5){
                Exception exception = var5;
                String message = "The specified file found in '" + filePath +"' does not have the json file structure.\nException Message\n" + exception.getMessage();
                Assert.fail(message);
            }
        }else{
            Assert.fail("The specified Json file was not found in " + filePath);
        }
        return new JSONObject();
    }

    public String getJsonFileContent(String filePath){
        File file = new File(filePath);
        if (file.exists() && file.isFile() && file.canRead()){
            try{
                return new String(Files.readAllBytes(Paths.get(file.toURI())));
            }catch (IOException var5){
                IOException exception = var5;
                String message = "The Specified file found in '"+filePath+"' does not have the json file structure.\nException Message\n" + exception.getMessage();
                Assert.fail(message);
            }
        }else {
            Assert.fail("The specified Json file was not found in "+ filePath);
        }
        return "";
    }
}
