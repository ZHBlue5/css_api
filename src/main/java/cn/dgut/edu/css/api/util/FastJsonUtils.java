package cn.dgut.edu.css.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ZHBlue
 * @date 2020/10/11
 * @desc 
 */
public class FastJsonUtils {
    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        // 使用和json-lib兼容的日期输出格式
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
        config.setPropertyNamingStrategy(PropertyNamingStrategy.SnakeCase);
    }

    private static final SerializerFeature[] FEATURES = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.DisableCircularReferenceDetect
    };

    /**
     * JSON解析参数配置:
     *   DisableCircularReferenceDetect: 禁止循环引用的检测
     *   WriteDateUseDateFormat: 设置Date类型格式化为 yyyy-MM-dd HH:mm:ss
     */
    private static final SerializerFeature[] SERIALIZE_FEATURES = {
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteDateUseDateFormat
    };

    /**
     * 将Java对象序列化为JSON字符串
     *
     * @param object 需要序列化的对象
     * @return 序列化后的JSON字符串
     */
    public static String toJson(Object object) {
        return JSON.toJSONString(object, SERIALIZE_FEATURES);
    }

    /**
     * 转换成字符串
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, config);
    }

    /**
     * 转换成字符串 ,带有过滤器
     *
     * @param object
     * @return
     */
    public static String toJSONWithFeatures(Object object) {
        return JSON.toJSONString(object, config, FEATURES);
    }


    /**
     * 转成bean对象
     *
     * @param text
     * @return
     */
    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    /**
     * 转成具体的泛型bean对象
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * 转换为数组Array
     *
     * @param text
     * @param <T>
     * @return
     */
    public static <T> Object[] toArray(String text) {
        return JSON.parseArray(text).toArray();
    }

    /**
     * 转换为具体的泛型数组Array
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    /**
     * 转换为具体的泛型List
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     * 将javabean转化为序列化的json字符串
     *
     * @param object
     * @return
     */
    public static Object beanToJson(Object object) {
        String textJson = JSON.toJSONString(object);
        Object objectJson = JSON.parse(textJson);
        return objectJson;
    }

    /**
     * 将string转化为序列化的json字符串
     *
     * @param text
     * @return
     */
    public static Object textToJson(String text) {
        Object objectJson = JSON.parse(text);
        return objectJson;
    }

    /**
     * json字符串转化为map
     *
     * @param s
     * @return
     */
    public static Map stringToMap(String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }

    /**
     * 将map转化为string
     *
     * @param m
     * @return
     */
    public static String mapToString(Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }

    /**
     * 用fastjson 将jsonString 解析成 List<Map<String,Object>>
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> getListMap(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
