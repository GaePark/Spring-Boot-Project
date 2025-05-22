package com.sist.web.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Dept {
	@Id
	private int deptno;
	private int dname,loc;
}
