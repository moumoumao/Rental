package com.rental.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rental.util.PageBean;
import com.rental.bean.TblArea;
import com.rental.dao.AreaDao;
import com.rental.service.AreaService;
import com.rental.util.StringUtil;
@Service("areaService")
@Transactional
public class AreaServiceImpl implements AreaService {
	@Autowired
	@Qualifier("areaDao")
	private AreaDao areaDao;
	
	public TblArea addArea(TblArea area) {
		return areaDao.addArea(area);
	}

	public void deleteArea(TblArea area) {
		areaDao.deleteArea(area);
	}

	public List<TblArea> findAll() {
		return areaDao.findAll();
	}

	public PageBean findByMixAndPage(TblArea area, int pageSize,
			int pageNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(area!=null){
			if(!StringUtil.isNullOrEmpty(area.getAreaName())){
				map.put("areaName","%"+ area.getAreaName()+"%");
			}
			if(!StringUtil.isNullOrEmpty(area.getDescribe())){
				map.put("describe", "%"+area.getDescribe()+"%");
			}
		}
		return areaDao.findByMixAndPage(map, pageSize, pageNo);
	}

	public boolean isExit(String areaName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("areaName", areaName);
		return (areaDao.findByMixAndPage(map, -1, -1).getRecordsTotal()>0)?true:false;
	}

	public void updateArea(TblArea area) {
		TblArea obj = areaDao.findById(area.getAreaId());
		if(!StringUtil.isNullOrEmpty(area.getAreaName())){
			obj.setAreaName(area.getAreaName());
		}
		if(!StringUtil.isNullOrEmpty(area.getDescribe())){
			obj.setDescribe(area.getDescribe());
		}
		areaDao.updateArea(obj);
		
	}

	public TblArea findById(int id) {
		return areaDao.findById(id);
	}
	

}
