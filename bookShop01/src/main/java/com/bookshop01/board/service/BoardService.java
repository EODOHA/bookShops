package com.bookshop01.board.service;

import java.util.Map;

public interface BoardService {
	public Map listArticles(Map pagingMap) throws Exception;
	
//	public List<ArticleVO> listArticles() throws Exception;

	public int addNewArticle(Map articleMap) throws Exception;

	public Map viewArticle(int articleNO) throws Exception;

//	public ArticleVO viewArticle(int articleNO) throws Exception;

	public void modArticle(Map articleMap) throws Exception;

	public void removeArticle(int articleNO) throws Exception;
}
