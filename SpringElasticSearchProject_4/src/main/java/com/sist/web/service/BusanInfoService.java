package com.sist.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.sist.web.vo.BusanInfoEntity;

public interface BusanInfoService {
	   public List<BusanInfoEntity> busanAllData(int page);
	   // findAll()
	   public int[] getPageData(int page);
	   // count()
	   // save , delete 
	   public List<BusanInfoEntity> findByTitleContaining(String title);
	   public List<BusanInfoEntity> busanFindData(String title);
	   
	   public List<BusanInfoEntity> findByCno(Integer cno);
	   public List<BusanInfoEntity> busanCnoData(Integer cno);
	   
	   public BusanInfoEntity busanDetailData(Integer id);
	   public BusanInfoEntity findById(String id);
}