package com.sereal.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sereal.board.mapper.TBoardGoodHistoryMapper;
import com.sereal.board.model.TBoardGoodHistoryModel;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class TBoardGoodHistoryServiceImpl implements TBoardGoodHistoryService {

	@Setter(onMethod_ = @Autowired)
	private TBoardGoodHistoryMapper mapper;
	
	@Override
	public void insertBoardGoodHistory(TBoardGoodHistoryModel model) {
		log.info("service insertBoardGoodHistory Read...");
		mapper.insertBoardGoodHistory(model);
		
	}

	@Override
	public int deleteBoardGoodHistory(TBoardGoodHistoryModel model) {
		log.info("service deleteBoardGoodHistory Read...");
		return mapper.deleteBoardGoodHistory(model);
	}

	@Override
	public TBoardGoodHistoryModel selectBoardGoodHistory(TBoardGoodHistoryModel model) {
		log.info("service selectBoardGoodHistory Read...");
		return mapper.selectBoardGoodHistory(model);
	}


}
