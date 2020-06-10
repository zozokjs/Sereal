package com.sereal.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sereal.board.mapper.TBoardContentMapper;
import com.sereal.board.model.TBoardContentModel;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class TBoardContentServiceImpl implements TBoardContentService{
	
	@Setter(onMethod_ = @Autowired)
	private TBoardContentMapper mapper;
	
	@Override	
	public TBoardContentModel selectBoardContent(TBoardContentModel model) {
		
		log.info("service selectBoardContent Read...");
		
		return mapper.selectBoardContent(model);
	}

	@Override
	public List<TBoardContentModel> selectBoardContentAll(String grp_id) {
		log.info("service selectBoardContentAll Read...");
		
		return mapper.selectBoardContentAll(grp_id);
	}

	@Override
	public Integer insertBoardContent(TBoardContentModel model) {
		log.info("service insertBoardContent Read...");
		
		return mapper.insertBoardContent(model);
		
	}

	@Override
	public int modifyBoardContent(TBoardContentModel model) {
		log.info("service modifyBoardContent Read...");
		return mapper.modifyBoardContent(model);
	}

	@Override
	public int deleteBoardContent(TBoardContentModel model) {
		log.info("service deleteBoardContent Read...");
		return mapper.deleteBoardContent(model);
	}

	@Override
	public void modifyBoardCount(TBoardContentModel model) {
		log.info("service modifyBoardCount Read...");
		mapper.modifyBoardCount(model);
	}

	@Override
	public Integer selectBoardContentAllCount(String grp_id) {
		log.info("service selectBoardContentAllCount Read...");
		mapper.selectBoardContentAllCount(grp_id);
		return mapper.selectBoardContentAllCount(grp_id);
	}

	@Override
	public void modifyBoardDelete(TBoardContentModel model) {
		log.info("service modifyBoardDelete Read...");
		mapper.modifyBoardDelete(model);
		
	}

	@Override
	public List<TBoardContentModel> selectBoardContentReply(TBoardContentModel model) {
		
		log.info("service selectBoardContentReply Read...");
		
		return mapper.selectBoardContentReply(model);
	
	}

	@Override
	public TBoardContentModel selectBoardContentReplyPassword(TBoardContentModel model) {
		log.info("service selectBoardContentReplyPassword Read...");
		
		return mapper.selectBoardContentReplyPassword(model);
	}

	@Override
	public List<TBoardContentModel> selectBoardContentAllPaging(TBoardContentModel model) {
		log.info("service selectBoardContentAllPaging Read...");
		return mapper.selectBoardContentAllPaging(model);
	}

}
