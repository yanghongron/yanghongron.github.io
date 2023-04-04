package com.xxx.dao;

import com.github.pagehelper.Page;
import com.xxx.pojo.CheckGroup;
import com.xxx.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {

    void add(Setmeal setmeal);

    void setSetmealAndCheckGroup(Map map);

    Page<Setmeal> findByCondition(String queryString);

    long findCountByCheckGroupId(Integer id);

    void deleteById(Integer id);

    List<Integer> findCheckgroupIdsBySetmealId(Integer id);

    void edit(Setmeal setmeal);

    void deleteAssocication(Integer id);

    List<CheckGroup> findAll();

    Setmeal findById(Integer id);

    List<Setmeal> setmealFindAll();

    Setmeal setmealFindById(Integer id);

    List<Map<String, Object>> findStemalCount();
}
