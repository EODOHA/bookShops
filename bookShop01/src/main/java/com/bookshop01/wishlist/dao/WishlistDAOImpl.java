package com.bookshop01.wishlist.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bookshop01.wishlist.vo.WishlistVO;

@Repository("wishlistDAO")
public class WishlistDAOImpl implements WishlistDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 위시리스트 상품 추가 insertItemInWishlist
	@Override
	public void insertItemInWishlist(WishlistVO wishlistVO) throws DataAccessException {
		String wish_id = selectMaxWishlistId();
		wishlistVO.setWish_id(wish_id);
		sqlSession.insert("mapper.wishlist.insertItemInWishlist", wishlistVO);
	}
	
	// 위시리스트 조회 selectWishlist
	@Override
	public List<WishlistVO> selectWishlist(String member_id) throws DataAccessException {
		return sqlSession.selectList("mapper.wishlist.selectWishlist", member_id);
	}
	
	// 위시리스트에서 특정 상품 삭제 deleteItemFormWishlist
	@Override
	public void deleteItemFromWishlist(String wish_id) throws DataAccessException {
		sqlSession.delete("mapper.wishlist.deleteItemFromWishlist", wish_id);
	}
	
	// 위시리스트에 상품 존재여부 isItemInWishlist
	@Override
	public boolean isItemInWishlist(WishlistVO wishlistVO) throws DataAccessException {
	    Integer result = sqlSession.selectOne("mapper.wishlist.isItemInWishlist", wishlistVO);
	    return (result != null && result > 0);
	}
	
	// 위시리스트의 최대값 selectMaxWishId
	@Override
	public String selectMaxWishlistId() throws DataAccessException {
		return sqlSession.selectOne("mapper.wishlist.selectMaxWishlistId");
	}
	

}