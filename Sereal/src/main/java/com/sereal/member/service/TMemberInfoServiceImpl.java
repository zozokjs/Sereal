package com.sereal.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sereal.member.mapper.TMemberInfoMapper;
import com.sereal.member.model.TMemberInfoModel;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class TMemberInfoServiceImpl implements TMemberInfoService{

	@Setter(onMethod_ = @Autowired)
	private TMemberInfoMapper mapper;
	
	@Override
	public TMemberInfoModel selectMember(TMemberInfoModel model) {

		log.info("selectMember Service....");
		
		return mapper.selectMember(model);
	}

}
