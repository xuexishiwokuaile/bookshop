package com.example.bookshop.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Alu
 * @date 2020/4/7 17:02
 */
public class MD5Util {

    private static final String salt = "vdu0769fief4";

    /**
     * 用md5进行加密处理
     * @param str 需要加密的字段
     * @return 加密处理结果
     */
    public static String md5Encryption(String str){
        return DigestUtils.md5Hex(str);
    }

    //前端输入的密码到表单提交的密码(第一次加密) 用于校验前端传入的密码与该方法返回的密码是否相等
    public static String inputPassToFormPass(String pass){
        String s = "" + salt.charAt(0) + salt.charAt(2) + salt.charAt(5) + pass + salt.charAt(7) + salt.charAt(8);
        return md5Encryption(s);
    }
    //表单提交的密码(第一次加密的密码)到数据库存储的密码(第二次加密)
    public static String formPassToDb(String formPass,String salt){
        String s = "" + salt.charAt(0) + salt.charAt(2) + salt.charAt(5) + formPass + salt.charAt(7) + salt.charAt(8);
        return md5Encryption(s);
    }

    //前端输入的明文密码到数据库存储的密码 可用于注册

    public static String inputPassToDbPass(String input,String salt){
        String form = inputPassToFormPass(input);
        return formPassToDb(form,salt);
    }
}