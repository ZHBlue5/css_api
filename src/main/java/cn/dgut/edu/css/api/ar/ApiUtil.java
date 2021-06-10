package cn.dgut.edu.css.api.ar;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ZHBlue
 * @description TOOO
 * @Date 2019/2/17 9:30
 */
public class ApiUtil {
    /**
     * 按参数名升续拼接参数
     *
     * @param request
     * @return
     */
    public static String concatSignString(HttpServletRequest request) {
        Map<String, String> paramterMap = new HashMap<>();
        request.getParameterMap().forEach((key, value) -> paramterMap.put(key, value[0]));
        // 按照key升续排序，然后拼接参数
        Set<String> keySet = paramterMap.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyArray.length; i++) {
            // 参数值为空，则不参与签名 这个方法trim()是去空格
            Object value = paramterMap.get(keyArray[i]);
            if (value == null || "".equals(value.toString()) || value.equals(0) || value.equals(false) || "0".equals(value)) {
                continue;
            }
            if (!"".equals(sb.toString())) {
                sb.append("&");
            }
            if (value.toString().trim().length() > 0) {
                sb.append(keyArray[i]).append("=").append(value.toString().trim());
            }
        }

        return sb.toString();
    }

    /**
     * map 转sign string
     *
     * @param map
     * @return
     */

    public static String concatSignString(Map map) {
        Map paramterMap = new HashMap<>();
        map.forEach((key, value) -> paramterMap.put(key, value));
        // 按照key升续排序，然后拼接参数
        Set<String> keySet = paramterMap.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyArray.length; i++) {
            // 参数值为空，则不参与签名 这个方法trim()是去空格
            Object value = map.get(keyArray[i]);
            if (value == null || value == "null" || "".equals(value.toString()) || value.equals(0) || value.equals(false) || "0".equals(value)) {
                continue;
            }
            if (value.toString().trim().length() > 0) {
                if (!"".equals(sb.toString())) {
                    sb.append("&");
                }
                sb.append(keyArray[i]).append("=").append(map.get(keyArray[i]).toString().trim());
            }
        }
        return sb.toString();
    }
}
