package cn.dgut.edu.css.api.ar;

/**
 * @author ZHBlue
 * @description api return data
 * @Date 2019/2/14 13:51
 */
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

    public ApiResult() {

    }

    public ApiResult(int ret, String msg) {
        this.msg = msg;
        this.code = ret;
    }

    public ApiResult(int ret, String msg, Object data) {
        this.code = ret;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public Object getData() {
        return this.data;
    }
}
