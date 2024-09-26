package com.bookshop01.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.bookshop01.board.vo.ArticleVO;

public interface BoardDAO {
	public List selectAllArticlesList(Map pagingMap) throws DataAccessException;
	
	public int selectTotArticles() throws DataAccessException;
	
//	public List selectAllArticlesList() throws DataAccessException;

	public int insertNewArticle(Map articleMap) throws DataAccessException;

	public ArticleVO selectArticle(int articleNO) throws DataAccessException;

	public void updateArticle(Map articleMap) throws DataAccessException;

	public void deleteArticle(int articleNO) throws DataAccessException;

	public void insertNewImage(Map articleMap) throws DataAccessException;

	public List selectImageFileList(int articleNO) throws DataAccessException;

}
