package com.xxx.service;

import com.xxx.entity.PageResult;
import com.xxx.entity.QueryPageBean;
import com.xxx.pojo.Member;

import java.util.List;

public interface MemberService {
    //根据手机号查询会员
    public Member findByTelephone(String telephone);
    public void add(Member member);
    List<Integer> findMemberCountByMonths(List<String> months);

    PageResult pageQuery(QueryPageBean queryPageBean);

    void addMember(Member member);

    Member findMemberById(Integer memberId);

    void edit(Member member);

    void deleteById(Integer id);
}
