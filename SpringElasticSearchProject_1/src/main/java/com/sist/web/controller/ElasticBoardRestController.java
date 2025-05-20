package com.sist.web.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.*;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ElasticBoardRestController {
	@Autowired 
	private ElasticBoardRepository bDao;
	
	@PostMapping("/eboard/update_ok")
	public void eboard_update_ok(ElasticBoard vo, HttpServletResponse response) throws IOException {
	    ElasticBoard dbvo = bDao.findById(vo.getId());
	    String script;

	    if (dbvo != null && dbvo.getPwd().equals(vo.getPwd())) {
	        vo.setHit(dbvo.getHit());
	        vo.setRegdate(dbvo.getRegdate());
	        bDao.save(vo);
	        // 성공 시 디테일 페이지로 이동
	        script = "<script>"
	               + "location.href='/eboard/detail?id=" + vo.getId() + "';"
	               + "</script>";
	    } else {
	        // 비밀번호가 틀린 경우
	        script = "<script>"
	               + "alert('비밀번호가 틀립니다.');"
	               + "history.back();"
	               + "</script>";
	    }

	    // 자바스크립트 출력
	    response.setContentType("text/html; charset=UTF-8");
	    response.getWriter().write(script);
	}
	@PostMapping("/eboard/delete_ok")
	public String eboard_delete_ok(@RequestParam("id") int id, @RequestParam("pwd") String pwd)
	{
		String result="";
		ElasticBoard vo=bDao.findById(id);
		if(vo.getPwd().equals(pwd))
		{
			bDao.delete(vo);
			 result = "<script>"
		               + "location.href=\"/eboard/list\""
		               + "</script>";
		}
		else {
			result = "<script>"
		               + "alert('비밀번호가 틀립니다.');"
		               + "history.back();"
		               + "</script>";
		}
		return result;
	}
}
