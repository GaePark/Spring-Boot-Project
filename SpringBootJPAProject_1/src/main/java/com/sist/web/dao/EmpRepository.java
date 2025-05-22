package com.sist.web.dao;
import java.util.*;
import com.sist.web.vo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//혼합
// 가독성 (X), 소스 간결하다 , SQL을 몰라도 처리가 가능
public interface EmpRepository extends JpaRepository<Emp, Integer> {
	@Query(value="SELECT * FROM emp",nativeQuery = true)
	public List<Emp> empAllData();
	
	public Emp findByEmpno(int empno);
	
	public List<Emp> findByEnameContaining(String ename);
	
	public List<Emp> findByEnameStartsWith(String ename);
	
	//public List<Emp> findDistinctByJob();
}
