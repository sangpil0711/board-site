package kr.co.ymtech.bm.repository;


import kr.co.ymtech.bm.repository.vo.CommentVO;

public interface ICommentRepository {

	public Integer saveComment(CommentVO comment);

	public CommentVO findComment(Integer boardIndex);

	public Integer updateComment(CommentVO comment);
	
	public Integer deleteComment(Integer index);


	
	
	
	
}
