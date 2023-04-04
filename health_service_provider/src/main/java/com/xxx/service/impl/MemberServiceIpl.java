package com.xxx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qiniu.util.Md5;
import com.xxx.Utils.MD5Utils;
import com.xxx.dao.MemberDao;
import com.xxx.entity.PageResult;
import com.xxx.entity.QueryPageBean;
import com.xxx.pojo.CheckGroup;
import com.xxx.pojo.Member;


import com.xxx.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员服务
 */

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceIpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    //保存会员信息
    public void add(Member member) {
        String password = member.getPassword();
        if (password!=null){
            //使用MD5加密密码
            password = MD5Utils.md5(password);
            member.setPassword(password);

        }
        memberDao.add(member);
    }

    @Override
    public List<Integer> findMemberCountByMonths(List<String> months) {
        List<Integer> memberCount = new ArrayList<>();
        for (String month : months) {
            String data = month + ".28";
            Integer count = memberDao.findMemberCountBeforeDate(data);
            memberCount.add(count);
        }
        return memberCount;
    }
//分页查询

    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<Member> page = memberDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    //添加
    public void addMember(Member member) {
        memberDao.add(member);
    }

    @Override
    public Member findMemberById(Integer memberId) {
       return memberDao.findById(memberId);
    }

    //修改
    public void edit(Member member) {
        memberDao.edit(member);
    }

    @Override
    public void deleteById(Integer id) {
        memberDao.deleteById(id);
    }


}
