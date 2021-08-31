package cn.dgut.edu.css.api.ar;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

import static cn.dgut.edu.css.api.ar.Api.SUCCESS_CODE;

/**
 * @author ZHBlue
 * @description api return data
 * @Date 2019/2/14 13:51
 */
@Data
@Accessors(chain = true)
public class ApiResult {
    /**
     * @Author ZHBlue
     * @Description 请求状态码
     * @Date 2019/2/15 9:07
     **/
    private int code;

    /**
     * @Author ZHBlue
     * @Description 提示信息
     * @Date 2019/2/15 9:07
     **/
    private String msg;
    /**
     * @Author ZHBlue
     * @Description 请求数据
     * @Date 2019/2/15 9:08
     **/
    private Object data;

   public static  ApiResult build(String content){
       Map res = JSON.parseObject(content, Map.class);
       if (!SUCCESS_CODE.equals(res.get("code"))) {
           return new ApiResult().setCode(Integer.parseInt(res.get("code").toString()))
                   .setMsg(res.get("msg").toString());
       }
       return new ApiResult().setCode(Integer.parseInt(res.get("code").toString()))
               .setMsg(res.get("msg").toString()).setData(res.get("data"));
   }
}
