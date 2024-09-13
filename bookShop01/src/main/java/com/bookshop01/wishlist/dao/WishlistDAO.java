package com.bookshop01.wishlist.dao;


import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bookshop01.wishlist.vo.WishlistVO;

public interface WishlistDAO {
	public void insertItemInWishlist(WishlistVO wishlistVO) throws DataAccessException;
	public List<WishlistVO> selectWishlist(String member_id) throws DataAccessException;
	public void deleteItemFromWishlist(String wish_id) throws DataAccessException;
	public boolean isItemInWishlist(WishlistVO wishlistVO) throws DataAccessException;
	public String selectMaxWishlistId() throws DataAccessException;
}