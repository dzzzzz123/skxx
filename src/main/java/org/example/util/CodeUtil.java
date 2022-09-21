package org.example.util;

/**
 * @author qiufen
 * @date 2022-09-16
 */
/**
 * 用于生成随机字符串的工具
 * 1. 用于盐值生成
 * 2. 用于验证码生成
 */
public class CodeUtil {

    /**用于生成字符串的资源池*/
    private static String code = "";

    /**
     * 用于设置安全等级枚举
     * SIMPLE：简单级别，纯数字
     * NORMAL：普通级别，小写字符+数字
     * HARD：困难级别，大写字符+小写字符+数字
     */
    public enum SecurityLevel{
        SIMPLE,
        NORMAL,
        HARD
    }

    /**
     * 用于生成随机字符串的方法
     * @param length  字符个数
     * @param level   难度级别
     * @param isRepeat 是否允许重复字符
     * @return
     */
    public static String getCode(int length,SecurityLevel level,boolean isRepeat){
        //根据难度级别选定资源池的范围
        if(level == SecurityLevel.SIMPLE){
            code = code.substring(0,10);
        }else if(level == SecurityLevel.NORMAL){
            code = code.substring(0,36);
        }
        //准备一个字符数组存储随机生成的每一个字符
        char[] codes = new char[length];
        for (int i = 0; i < length; i++) {
            //从字符串中随机一个索引
            int index = (int)(Math.random()*code.length());
            //将随机到的字符添加到数组中
            codes[i] = code.charAt(index);
            //如果不允许重复，则应该将取到的字符从code字符串中移除
            if(!isRepeat){
                //使用空字符串替换已被选中字符
                code = code.replace(code.charAt(index)+"","");
            }
        }
        //返回字符串
        return String.valueOf(codes);
    }

    /**
     * 生成一个长度为6位的随机盐值
     * @return
     */
    public static String randomSalt6(){
        return getCode(6,SecurityLevel.HARD,false);
    }

}
