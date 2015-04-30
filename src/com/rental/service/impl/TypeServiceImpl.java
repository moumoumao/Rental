package com.rental.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rental.util.PageBean;
import com.rental.bean.TblType;
import com.rental.dao.TypeDao;
import com.rental.service.TypeService;
import com.rental.util.StringUtil;
@Service("typeService")
@Transactional
public class TypeServiceImpl implements TypeService {
	@Autowired
	@Qualifier("typeDao")
	private TypeDao typeDao;
	
	public TblType addType(TblType type) {
		return typeDao.addType(type);
	}

	public void deleteType(TblType type) {
		typeDao.deleteType(type);
	}

	public List<TblType> findAll() {
		return typeDao.findAll();
	}

	public PageBean findByMixAndPage(TblType Type, int pageSize,
			int pageNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(Type!=null){
			if(!StringUtil.isNullOrEmpty(Type.getTypeName())){
				map.put("typeName","%"+ Type.getTypeName()+"%");
			}
			if(!StringUtil.isNullOrEmpty(Type.getDescribe())){
				map.put("describe","%"+ Type.getDescribe()+"%");
			}
		}
		return typeDao.findByMixAndPage(map, pageSize, pageNo);
	}

	public boolean isExit(String TypeName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeName", TypeName);
		return (typeDao.findByMixAndPage(map, -1, -1).getRecordsTotal()>0)?true:false;
	}

	public void updateType(TblType type) {
		TblType obj = typeDao.findById(type.getTypeId());
		if(!StringUtil.isNullOrEmpty(type.getTypeName())){
			obj.setTypeName(type.getTypeName());
		}
		if(!StringUtil.isNullOrEmpty(type.getDescribe())){
			obj.setDescribe(type.getDescribe());
		}
		typeDao.updateType(obj);
		
	}

	public TblType findById(int id) {
		return typeDao.findById(id);
	}
	

}
