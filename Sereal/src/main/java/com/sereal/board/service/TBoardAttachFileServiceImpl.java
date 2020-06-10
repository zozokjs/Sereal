package com.sereal.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sereal.board.mapper.TBoardAttachFileMapper;
import com.sereal.board.model.TBoardAttachFileModel;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@Service
@Log4j
public class TBoardAttachFileServiceImpl implements TBoardAttachFileService{

	
	@Setter(onMethod_ = @Autowired)
	private TBoardAttachFileMapper mapper;
	
	@Override
	public List<TBoardAttachFileModel> selectBoardAttachFileAll(Integer brd_idx) {
		log.info("service selectBoardAttachFileAll Read...");
		return mapper.selectBoardAttachFileAll(brd_idx);
	}

	@Override
	public void insertBoardAttachFile(TBoardAttachFileModel model) {
		log.info("service insertBoardAttachFile Read...");
		mapper.insertBoardAttachFile(model);
		
	}

	@Override
	public void modifyBoardAttachFile(TBoardAttachFileModel model) {
		log.info("service modifyBoardAttachFile Read...");
		mapper.modifyBoardAttachFile(model);
		
	}

}
