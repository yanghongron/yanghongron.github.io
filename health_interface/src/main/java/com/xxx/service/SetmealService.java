package com.xxx.service;

import com.xxx.entity.PageResult;
import com.xxx.entity.QueryPageBean;
import com.xxx.pojo.CheckGroup;
import com.xxx.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    PageResult pageQuery(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    List<Integer> findCheckgroupIdsBySetmealId(Integer id);

    void edit(Setmeal setmeal, Integer[] checkgroupIds);

    List<CheckGroup> findAll();

    Setmeal findById(Integer id);

    List<Setmeal> setmealFindAll();

    Setmeal setmealFindById(int id);

    List<Map<String, Object>> findSetmealCount();
}
