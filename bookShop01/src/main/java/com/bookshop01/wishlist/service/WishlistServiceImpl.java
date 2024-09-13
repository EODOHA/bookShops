package com.bookshop01.wishlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop01.wishlist.dao.WishlistDAO;
import com.bookshop01.wishlist.vo.WishlistVO;

@Service("wishlistService")
@Transactional(propagation=Propagation.REQUIRED)
public class WishlistServiceImpl implements WishlistService {
	
	@Autowired
	private WishlistDAO wishlistDAO;
	
	// 위시리스트에 아이템 추가 addItemToWishlist
	@Override
	public void addItemToWishlist(WishlistVO wishlistVO) throws Exception {
		wishlistDAO.insertItemInWishlist(wishlistVO);
	}
	
	// 사용자의 위시리스트 조회 getWishlist
	@Override
	public List<WishlistVO> getWishlist(String member_id) throws Exception {
		return wishlistDAO.selectWishlist(member_id);
	}
	
	// 위시리스트의 아이템 삭제 removeItemFromWishlist
	@Override
	public void removeWishlistGoods(String wish_id) throws Exception {
		wishlistDAO.deleteItemFromWishlist(wish_id);
	}
	
	// 위시리스트에 상품 존재여부 isItemInWishlist
	@Override
	public boolean isItemInWishlist(WishlistVO wishlistVO) throws Exception {
		return wishlistDAO.isItemInWishlist(wishlistVO);
	}
	
}