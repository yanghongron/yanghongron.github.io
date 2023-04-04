package com.xxx.jobs;

import com.xxx.Utils.QiniuUtils;
import com.xxx.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 自定义定时清理垃圾图片
 */
public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        //根据Redis中保存的两个set集合进行差值计算，获得垃圾图片名称集合
      Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if (set !=null){
            for (String picName:set) {
                //从7牛云服务器上删除图片
                QiniuUtils.deleteFileFromQiniu(picName);
                //删除Redis集合中的图片名称
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,picName);
                System.out.println("自定义任务执行清理垃圾图片："+picName);
            }
        }
    }
}
