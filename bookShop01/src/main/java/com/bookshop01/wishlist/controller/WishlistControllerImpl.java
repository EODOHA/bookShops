package com.bookshop01.wishlist.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.member.vo.MemberVO;
import com.bookshop01.wishlist.service.WishlistService;
import com.bookshop01.wishlist.vo.WishlistVO;

@Controller("wishlistController")
@RequestMapping(value = "/wishlist")
public class WishlistControllerImpl implements WishlistController {

    @Autowired
    private WishlistService wishlistService;
  
    @Autowired
    private WishlistVO wishlistVO;

    @Autowired
    private MemberVO memberVO;
	
	// 위시리스트 보기 myWishlist
    @RequestMapping(value="/myWishlist.do", method=RequestMethod.GET)
    public ModelAndView myWishMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String viewName=(String)request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);
        HttpSession session = request.getSession();
        MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
        
        if (memberVO != null) {
            String member_id = memberVO.getMember_id();
            List<WishlistVO> wishlist = wishlistService.getWishlist(member_id);
            mav.addObject("wishlist", wishlist);
        } else {
            // 로그인되지 않은 경우의 처리
            mav.setViewName("redirect:/login"); // 로그인 페이지로 리다이렉트
        }
        
        return mav;     
    }

    
    // 위시리스트에 상품 추가 addGoodsToWishlist
    @RequestMapping(value = "/addGoodsToWishlist.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
    public @ResponseBody String addGoodsToWishlist(@RequestParam("goods_id") int goods_id, 
            HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        memberVO = (MemberVO) session.getAttribute("memberInfo");

        if (memberVO == null) {
            return "not_logged_in"; // 로그인하지 않은 경우
        }

        String member_id = memberVO.getMember_id();

        wishlistVO.setMember_id(member_id);
        wishlistVO.setGoods_id(goods_id);

        // 상품 존재 여부 확인
        boolean isAlreadyInWishlist = wishlistService.isItemInWishlist(wishlistVO);
        
        if (isAlreadyInWishlist) {
            return "already_existed";
        } else {
            wishlistService.addItemToWishlist(wishlistVO);
            return "add_success";
        }
    }

    // 위시리스트에 상품 삭제 removeGoodsFromWishlist
	@RequestMapping(value="removeGoodsFromWishlist.do", method = RequestMethod.POST)
	public ModelAndView removeGoodsFromWishlist(@RequestParam("wish_id") String wish_id,
											HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		wishlistService.removeWishlistGoods(wish_id);
		mav.setViewName("redirect:/wishlist/myWishlist.do");
		return mav;
	}
    
}