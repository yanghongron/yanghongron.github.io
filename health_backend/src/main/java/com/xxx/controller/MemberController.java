package com.xxx.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xxx.constant.MessageConstant;
import com.xxx.entity.PageResult;
import com.xxx.entity.QueryPageBean;
import com.xxx.entity.Result;
import com.xxx.pojo.Member;
import com.xxx.service.MemberService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Reference
    private MemberService memberService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean pageBean){
        return memberService.pageQuery(pageBean);
    }

    //add
    @RequestMapping("addMember")
    public Result addMember(@RequestBody Member member){
        try {
            memberService.addMember(member);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_MEMBER_FAIL);
        }
        return new Result(true, MessageConstant.ADD_MEMBER_SUCCESS);
    }

    @RequestMapping("/findMemberById")
    public Result findMemberById(Integer memberId){
        try {
            Member member = memberService.findMemberById(memberId);
            return new Result(true, MessageConstant.GET_MEMBER_SUCCESS,member);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_MEMBER_FAIL);
        }


    }

    @RequestMapping("/editMember")
    public Result editMember(@RequestBody Member member){
        try {
            memberService.edit(member);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_MEMBER_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_MEMBER_SUCCESS);
    }

    @RequestMapping("/deleteMember")
    public Result deleteMember(Integer id){
        try {
            memberService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.DELETE_MEMBER_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_MEMBER_SUCCESS);
    }



}
