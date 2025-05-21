package com.sist.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sist.web.dao.BusanInfoRepository;
import com.sist.web.vo.BusanInfoEntity;

// https://docs.spring.io/spring-data/elasticsearch/reference/repositories/query-methods-details.html
@Service
/*
 *   배민 / 쿠팡 / 카카오 
 *   인터파크 / 코난 => 검색엔진 => elasticsearch 
 *   검색 속도가 필요 ...
 *   NoSQL => CRUD / 검색엔진 => 최적화 
 *   ------------------------------- 데이터를 첨부해야 가능 
 *                                   ----------------- 클러스트이용 => 리눅스 : 암호화 
 *                                   SSH 
 *   
 */
public class BusanInfoServiceImpl implements BusanInfoService{

	@Autowired
	private BusanInfoRepository bDao; 
	
	@Override
	public List<BusanInfoEntity> busanAllData(int page) {
		// TODO Auto-generated method stub
		int rowSize=12;
		Pageable pg=PageRequest.of(page-1, rowSize,Sort.by(Sort.Direction.ASC,"id"));
		// 0 = 12 skip 1 = 12  LIMIT start,10
		Page<BusanInfoEntity> pList=bDao.findAll(pg);
		// List로 변환 
		List<BusanInfoEntity> list=new ArrayList<BusanInfoEntity>();
		if(pList!=null && pList.hasContent())
		{
			list=pList.getContent();
		}
		return list;
	}
	/*
	 *   실무 
	 *    DAO:Repository => Only DataBase연동  
	 *    Service : 필요한 데이터를 받아서 (DAO) => 요청 처리후에 Controller로 전송 
	 *    Controller => Only 브라우저 전송 
	 *    => 최적화 (결합성(의존성) 낮은 프로그램) => 수정시에 다른 클래스에 영향이 없는 : Spring 
	 *    => Spring - Framework 
	 *       ------------------- Spring-Boot
	 *       
	 */
	@Override
	public int[] getPageData(int page) {
		// TODO Auto-generated method stub
		int[] datas=new int[4];
		int count=(int)bDao.count();
		int totalpage=(int)(Math.ceil(count/12.0));
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1; // 1,11,21...
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;// 10, 20, 30....
		if(endPage>totalpage)
			endPage=totalpage;
		
		datas[0]=page;
		datas[1]=totalpage;
		datas[2]=startPage;
		datas[3]=endPage;
		return datas;
	}

	@Override
	public List<BusanInfoEntity> findByTitleContaining(String title) {
		// TODO Auto-generated method stub
		return bDao.findByTitleContaining(title);
	}

	@Override
	public List<BusanInfoEntity> busanFindData(String title) {
		// TODO Auto-generated method stub
		return bDao.busanFindData(title);
	}

	@Override
	public List<BusanInfoEntity> findByCno(Integer cno) {
		// TODO Auto-generated method stub
		return bDao.findByCno(cno);
	}

	@Override
	public List<BusanInfoEntity> busanCnoData(Integer cno) {
		// TODO Auto-generated method stub
		return bDao.busanCnoData(cno);
	}

	@Override
	public BusanInfoEntity busanDetailData(Integer id) {
		// TODO Auto-generated method stub
		return bDao.busanDetailData(id);
	}

	@Override
	public BusanInfoEntity findById(String id) {
		// TODO Auto-generated method stub
		// Optional<BusanInfoEntity> => VO 객체로 변환 
		// BusanInfoEntity b=bDao.findById(id).orElse(new BusanInfoEntity());
		return bDao.findById(id);
	}

}