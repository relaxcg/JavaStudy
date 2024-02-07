package org.relaxcg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author relaxcg
 * @date 2024/1/30 14:56
 */
public class JSON {

    private static final ObjectMapper DEFAULT = new ObjectMapper();


    public static String toJSONString(Object o) {
        try {
            return DEFAULT.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object parse(String jsonStr) {
        try {
            return DEFAULT.readValue(jsonStr, Object.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> parseArray(String jsonStr, Class<T> tClass) {
        try {
            return DEFAULT.readValue(jsonStr, new TypeReference<List<T>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode parseObject(String jsonStr) {
        try {
            return DEFAULT.readTree(jsonStr);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode parseObject(InputStream is) {
        try {
            return DEFAULT.readTree(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String json = """
                    {
                        "key": "123",
                        "value": 123,
                        "data": [
                            {
                                "key": 123,
                                "value": 123
                            }, {
                                "key": 124,
                                "value": 124
                            }
                        ]
                    }
                """;

        JsonNode jsonNode = JSON.parseObject(json);
        System.out.println(jsonNode.get("key").asText());
        jsonNode.fieldNames().forEachRemaining(System.out::println);
        JsonNode arrayNode =  jsonNode.get("data");
        System.out.println(arrayNode.getClass());
        arrayNode.forEach(node->{
            node.fieldNames().forEachRemaining(System.out::println);
            node.asText();
        });

        // List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("data");
        //
        // list.forEach(o -> {
        //     o.forEach((k, v) -> {
        //         System.out.println(k + ", " + v);
        //     });
        // });


        // map.forEach((k, v)->{
        //     System.out.println(v.getClass());
        //     System.out.println(k + ", " + v );
        //     if (v instanceof List<?>) {
        //         System.out.println(((List<?>) v).get(0).getClass());
        //     }
        // });
    }
}
