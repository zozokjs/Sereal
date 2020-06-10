package com.sereal.member.mapper;

import com.sereal.member.model.TMemberInfoModel;

public interface TMemberInfoMapper {
	
	//멤버 1개 조회	
	public TMemberInfoModel selectMember(TMemberInfoModel model);
	
	//idx로 멤버 1개 조회
	public TMemberInfoModel selectMember2(Integer user_idx);
	
	
}
