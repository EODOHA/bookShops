package com.bookshop01.wishlist.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface WishlistController {
	public ModelAndView myWishMain(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public @ResponseBody String addGoodsToWishlist(@RequestParam("goods_id") int goods_id, HttpServletRequest request) throws Exception;
	public ModelAndView removeGoodsFromWishlist(@RequestParam("wishlist_id") String wishlist_id,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
}