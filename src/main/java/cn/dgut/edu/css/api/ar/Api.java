package cn.dgut.edu.css.api.ar;


import cn.dgut.edu.css.api.http.HttpClientResult;
import cn.dgut.edu.css.api.http.HttpClientUtils;
import cn.dgut.edu.css.api.util.FastJsonUtils;
import cn.dgut.edu.css.api.util.Md5;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZHBlue
 */
@Component
@Slf4j
public class Api {
    private static final String API_URL = "https://ar.css.dgut.edu.cn/api";
    /**
     * 正式
     */
    private static final String API_KEY = "ar系统提供";
    /**
     * 正式
     */
    private static final String API_SECRET = "ar系统提供";

    public static final Integer SUCCESS_CODE = 1000;
    /**
     * 允许每秒最多1个任务
     */

    @SuppressWarnings({"rawtypes", "unchecked"})
    public ApiResult send(String requestUrl, Map data) throws Exception {
        /**
         * 请求令牌,超过许可会被阻塞
         */
        data.put("timestamp", String.valueOf(System.currentTimeMillis()));
        data.put("noncestr", RandomUtil.randomString(15));
        String signature = sign(data);
        Map<String, String> headers = new HashMap<>();
        headers.put("apiKey", API_KEY);
        headers.put("Signature", signature);
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");

        HttpClientResult result = HttpClientUtils.doPost(API_URL + requestUrl, headers, data);
        log.info(FastJsonUtils.toJSONString(new HashMap<String,Object>(3) {{
            put("request", data);
            put("result", result.toString());
        }}));
        if (result.getCode() == 200) {
            Map res = JSON.parseObject(result.getContent(), Map.class);
            if (!SUCCESS_CODE.equals(res.get("code"))) {
                return new ApiResult((int) res.get("code"), (String) res.get("msg"));
            }
            return new ApiResult((int) res.get("code"), (String) res.get("msg"), res.get("data"));
        } else if (result.getCode() == 429) {
            throw new Exception("too many requests");
        }
        throw new Exception(result.getContent());
    }

    /**
     * @return java.lang.String
     * @Author ZHBlue
     * @Description 签名
     * @Param [data]
     * @Date 2019/2/14 13:35
     **/
    @SuppressWarnings("rawtypes")
    private String sign(Map data) {
        String signStr = ApiUtil.concatSignString(data);
        signStr += "&key=" + API_SECRET;
        return Md5.encoderByMd5(signStr).toUpperCase();
    }
}
