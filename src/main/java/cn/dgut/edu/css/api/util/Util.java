package cn.dgut.edu.css.api.util;

import java.util.Random;

/**
 * @author ZHBlue
 * @todo 工具类
 * @copyright： ZHBlue
 * @date：2021/6/10
 */
public class Util {
    /**
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
