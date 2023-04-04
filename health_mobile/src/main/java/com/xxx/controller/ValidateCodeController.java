package com.xxx.controller;

import com.xxx.Utils.ValidateCodeUtils;
import com.xxx.constant.MessageConstant;
import com.xxx.constant.RedisMessageConstant;
import com.xxx.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;

    //体检预约发送验证码

    @RequestMapping("/send4Order")
    public Result send4Order(String telephone) throws Exception {
        //随机生成四位验证码
        Integer validateCode = ValidateCodeUtils.generateValidateCode(4);
        //给用户发送验证码(条件欠缺使用流打印到文件output.txt)
        try {
            FileWriter fw = new FileWriter("G:\\output.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.print(validateCode);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //将验证码保存到redis(5m)
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_ORDER,300,validateCode.toString());
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    //手机快速登录

    @RequestMapping("/send4Login")
    public Result send4Login(String telephone) throws IOException {
        //随机生成6位验证码
        Integer validateCode = ValidateCodeUtils.generateValidateCode(6);
        //给用户发送验证码(条件欠缺使用流打印到文件output.txt)
        try {
            FileWriter fw = new FileWriter("G:\\outputPhotos.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.print(validateCode);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //将验证码保存到redis(5m)
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_LOGIN,300,validateCode.toString());
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
