package com.bookshop01.wishlist.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("wishlistVO")
public class WishlistVO {
    private String wish_id;
    private String member_id;
    private int goods_id;
    private Date wishlist_date;

    public WishlistVO() { }

    // getter/setter 메서드 이름 수정
    public String getWish_id() {
        return wish_id;
    }

    public void setWish_id(String wish_id) {
        this.wish_id = wish_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public Date getWishlist_date() {
        return wishlist_date;
    }

    public void setWishlist_date(Date wishlist_date) {
        this.wishlist_date = wishlist_date;
    }
}
