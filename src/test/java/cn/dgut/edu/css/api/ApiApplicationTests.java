package cn.dgut.edu.css.api;

import cn.dgut.edu.css.api.ar.Api;
import cn.dgut.edu.css.api.ar.ApiResult;
import cn.dgut.edu.css.api.dto.Student;
import cn.dgut.edu.css.api.util.FastJsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class ApiApplicationTests {

    @Autowired
    private Api api;

    @Test
    void contextLoads() {
    }

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

}
