package kr.co.ymtech.bm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.co.ymtech.bm.repository.vo.CommentVO;

@Repository
public class CommentRepository implements ICommentRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public Integer saveComment(CommentVO comment) {

		return jdbcTemplate.update("insert into comment(board_index, ﻿content, ﻿create_Date) values(?, ?, ?)", 
//				comment.getIndex(), 
				comment.getBoardIndex(),
				comment.getText(),
//				comment.getParentIndex()
//				comment.getUserId(), 
				comment.getCreateDate()
				);
	}
}
