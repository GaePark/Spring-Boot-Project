package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.vo.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Controller
public class EmpController {
	@Autowired
	private EntityManager em;//조인시 필요한 클래스
	
	@Autowired
	private EmpRepository eDao;
	
	@GetMapping("/emp/list")
	public String emp_list(Model model)
	{
		List<Emp> list = eDao.empAllData();
		model.addAttribute("list",list);
		return "emp/list";
	}
	@GetMapping("/emp/detail")
	public String emp_detail(@RequestParam("empno") int empno,  Model model)
	{
		String sql="SELECT s FROM Emp s JOIN s.dept d WHERE s.empno=:empno";
		//Emp emp=eDao.findByEmpno(empno);
		Emp e=em.createQuery(sql,Emp.class).setParameter("empno", empno).getSingleResult();
		model.addAttribute("vo",e);
		//getResults(): List ,getSingleResult() : VO
		
		return"emp/detail";
	}
	@GetMapping("/emp/join")
	public String emp_join(Model model)
	{
		//JPQL구사
		String sql="SELECT s FROM Emp s JOIN Fetch s.dept";
		TypedQuery<Emp> query=em.createQuery(sql,Emp.class);
		List<Emp> list=query.getResultList();
		model.addAttribute("list",list);
		return "emp/join";
	}
}
