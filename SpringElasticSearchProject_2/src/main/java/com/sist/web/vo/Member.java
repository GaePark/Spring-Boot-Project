package com.sist.web.vo;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Document(indexName="member")//elasticsearch 지정
//@Table => JPA
//@Entity(name="busan_food") => JPA에서 vo 지정할떄 사용
@Getter
@Setter
public class Member {
	@Id
	private int id;
	private String name;
	private Integer age;
}
