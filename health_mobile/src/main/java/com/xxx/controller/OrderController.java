package com.xxx.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xxx.constant.MessageConstant;
import com.xxx.constant.RedisMessageConstant;
import com.xxx.entity.Result;
import com.xxx.pojo.Order;
import com.xxx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map){
        String telephone = (String) map.get("telephone");
        String validaCodeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");
        if (validaCodeInRedis != null &&validateCode != null &&validateCode.equals(validaCodeInRedis)){
            map.put("orderType", Order.ORDERTYPE_WEIXIN);//设置预约类型
            Result  result = null;
            try {
                result = orderService.order(map);
            } catch (Exception e) {
                e.printStackTrace();
                return result;
            }
            if (result.isFlag()){
                //预约成功给用户发送短信
            }
            return result;
        }else {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }

    //根据预约id查询预约信息
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Map map = orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);

        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }

    }
}
