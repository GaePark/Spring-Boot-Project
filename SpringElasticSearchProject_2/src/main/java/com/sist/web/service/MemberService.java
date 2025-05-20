package com.sist.web.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.vo.*;
import com.sist.web.dao.*;
//JPA방식 => 1. return형, 2. 매개변수 , 3. 메소드명 => 자동 지정
// 필요시에 의하면 메소드 규칙에 따라서 제각 가능	
//WHERE 문장 => findBy변수명명령어 WHERE name like= > less then=>작다, greater then=> 크다
//SQL문장이 필요시에는 반드시 native query
@Service
public class MemberService {
	@Autowired
	private MemberRepository memberReplository;
	
	public void saveMember(Member member)
	{
		memberReplository.save(member);
	}
	public void deleteMember(Member member)
	{
		memberReplository.delete(member);
	}
	public Iterable<Member> getAllMembers()
	{
		return memberReplository.findAll();
	}
	public Member getMemberId(String id)
	{
		return memberReplository.findById(id).orElse(null);
	}
}
