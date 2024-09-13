package com.bookshop01.wishlist.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bookshop01.wishlist.vo.WishlistVO;

public interface WishlistService {
	public void addItemToWishlist(WishlistVO wishlistVO) throws Exception;
	public List<WishlistVO> getWishlist(String member_id) throws Exception;
	public void removeWishlistGoods(String wish_id) throws Exception;
	public boolean isItemInWishlist(WishlistVO wishlistVO) throws Exception;
}