package cn.dgut.edu.css.api.gateway;

import cn.dgut.edu.css.api.ar.Api;
import cn.dgut.edu.css.api.ar.ApiResult;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.dgut.edu.css.api.ar.Api.SUCCESS_CODE;

/**
 * @author ZHBlue
 * @since 2021/8/31 3:44 下午
 */
@Component
@Slf4j
public class GatewayRequest {

    @Value("${css-gateway-service.ak}")
    private String getewayAK;

    @Value("${css-gateway-service.sk}")
    private String gatewaySK;

    @Value("${css-gateway-service.api}")
    private String api;

    private final String version = "1.0.0";

    /**
     * 网关post请求
     * 
     * @param requestUrl
     * @param data
     * @return
     * @throws Exception
     */
    public ApiResult postForm(String requestUrl, Map data) throws Exception {
        HttpRequest httpRequest = HttpRequest.post(api + requestUrl).form(data);
        return  request(httpRequest,requestUrl);
    }

    /**
     * json的请求
     * @param requestUrl
     * @param json
     * @return
     * @throws Exception
     */
    public ApiResult postJson(String requestUrl,String json)throws Exception{

        HttpRequest httpRequest = HttpRequest.post(api + requestUrl).body(json);

        return request(httpRequest,requestUrl);
    }

    /**
     * get请求
     * @param requestUrl
     * @return
     * @throws Exception
     */
    public ApiResult get(String requestUrl) throws Exception {

        HttpRequest httpRequest = HttpRequest.get(api + requestUrl);
        return  request(httpRequest,requestUrl);
    }


    /**
     * 请求处理
     * @param httpRequest
     * @param requestUrl
     * @return
     * @throws Exception
     */
    private ApiResult request(HttpRequest httpRequest,String requestUrl) throws Exception {
        String timestamp = String.valueOf(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        httpRequest = this.header(httpRequest, timestamp, requestUrl);
        HttpResponse httpResponse = httpRequest.execute();
        if (httpResponse.isOk()) {
            String body = httpResponse.body();
            return ApiResult.build(body);
        }
        log.info("请求网关服务异常，status:{},body:{}", httpResponse.getStatus(), httpResponse.body());
        throw new Exception("请求网关服务异常");
    }

    /**
     * header处理
     * @param request
     * @param timestamp
     * @param requestUrl
     * @return
     */
    private HttpRequest header(HttpRequest request, String timestamp, String requestUrl) {
        return request.header("timestamp", timestamp).header("appKey", getewayAK)
            .header("sign", sign(requestUrl, timestamp)).header("version", version);
    }

    /**
     * 网关签名
     * 
     * @param requestUrl
     * @param timestamp
     * @return
     */
    private String sign(String requestUrl, String timestamp) {
        Map<String, String> map = new HashMap<>();
        map.put("timestamp", timestamp); // 值应该为毫秒数的字符串形式
        map.put("path", requestUrl);
        map.put("version", version);
        List<String> storedKeys = Arrays.stream(map.keySet().toArray(new String[] {})).sorted(Comparator.naturalOrder())
            .collect(Collectors.toList());
        final String sign = storedKeys.stream().map(key -> String.join("", key, map.get(key)))
            .collect(Collectors.joining()).trim().concat(gatewaySK);
        return SecureUtil.md5(sign).toUpperCase();
    }
}
