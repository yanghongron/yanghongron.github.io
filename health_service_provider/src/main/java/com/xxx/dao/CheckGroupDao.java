package com.xxx.dao;

import com.github.pagehelper.Page;
import com.xxx.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {

    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map map);


    Page<CheckGroup> findByIdCondition(String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(CheckGroup checkGroup);

    void deleteAssocication(Integer id);


    void deleteById(Integer id);

    long findCountByCheckGroupId(Integer id);

    List<CheckGroup> findAll();

}
