package cn.dgut.edu.css.api;

import cn.dgut.edu.css.api.ar.Api;
import cn.dgut.edu.css.api.ar.ApiResult;
import cn.dgut.edu.css.api.constant.HttpUrlConstant;
import cn.dgut.edu.css.api.dto.Student;
import cn.dgut.edu.css.api.gateway.GatewayRequest;
import cn.dgut.edu.css.api.util.FastJsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ApiApplicationTests {

    @Autowired
    private Api api;

    @Autowired
    private GatewayRequest gatewayRequest;

    @Test
    void contextLoads() {}

    @Test
    void test() throws Exception {

        ApiResult apiResult = api.send(Api.STUDENT_INFO, new HashMap<String, String>() {
            {
                put("number", "202041302328");
            }
        });

        if (Api.SUCCESS_CODE.equals(apiResult.getCode())) {
            List<Student> students = FastJsonUtils.toList(apiResult.getData().toString(), Student.class);
            System.out.println(students.size());
        }
    }

    void form() throws Exception {
        ApiResult apiResult = gatewayRequest.postForm(HttpUrlConstant.STUDENT_WARNING, new HashMap());
        if (Api.SUCCESS_CODE.equals(apiResult.getCode())) {
            // todo 请求成功的逻辑处理
        }
    }

    void json() throws Exception {
        Map map = new HashMap();
        map.put("number", "2222");
        ApiResult apiResult = gatewayRequest.postJson(HttpUrlConstant.STUDENT_WARNING, FastJsonUtils.toJson(map));
        if (Api.SUCCESS_CODE.equals(apiResult.getCode())) {
            // todo 请求成功的处理
        }

    }

}
