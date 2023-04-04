package com.xxx.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xxx.constant.MessageConstant;
import com.xxx.entity.Result;
import com.xxx.pojo.Setmeal;
import com.xxx.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @RequestMapping("/getAllSetmeal")
    public Result getAllSetmeal(){
        try {
           List<Setmeal> list =  setmealService.setmealFindAll();
            return new Result(true,MessageConstant.GET_SETMEAL_LIST_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_SETMEAL_LIST_FAIL);
        }
    }

    @RequestMapping("/setmealFindById")
    public Result setmealFindById(int id){
        try {
            Setmeal setmeal = setmealService.setmealFindById(id);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }
}
