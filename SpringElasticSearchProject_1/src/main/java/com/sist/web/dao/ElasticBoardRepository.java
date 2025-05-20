package com.sist.web.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.ElasticBoard;
/*
 * 	A% , %B , %c%
 *  findByNameStartWith(String name)
 *  findByNameEndWith(String name)
 *  findByNameContainng(String name) => qntks rjator
 */
@Repository
public interface ElasticBoardRepository extends ElasticsearchRepository<ElasticBoard, Integer> {
	public ElasticBoard findById(int id); // SELECT * FROM board WHERE id=? findBy==> WHERE
	
	//CRUD설정
	/*
	 * findAll  => 전체 데이터 읽기
	 * findBy컬럼명() => 상세보기
	 * save() => insert / update
	 * delete() => delete
	 * 
	 * mybatis / hibernate
	 * 	ㅣSQL기반    ㅣ메소드 => 자동 SQL 문장을 제작
	 * 				 --- 형식
	 * 				ㅣ단점 : JOIN(O) , subquery는 없다
	 */
}
