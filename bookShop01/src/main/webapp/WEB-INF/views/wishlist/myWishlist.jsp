<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="goods" value="${goodsMap.goodsVO}" />
<c:set var="imageList" value="${goodsMap.imageList}" />

<html>
<head>
    <title>위시리스트</title>
    <style>
        /* 여기에 스타일 추가 */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>위시리스트</h1>
    <div id="goods_details">
        <h2>${goods.goods_title}</h2>
        <img src="${contextPath}/thumbnails.do?goods_id=${goods.goods_id}&fileName=${goods.goods_fileName}" alt="${goods.goods_title}">
        <p>작가: ${goods.goods_writer}</p>
        <p>가격: ${fmt:formatNumber(value=goods.goods_price, type='number')} 원</p>
        <button onclick="add_wishlist('${goods.goods_id}')">위시리스트에 추가</button>
    </div>
    
    <!-- 위시리스트에 추가된 아이템들을 보여줄 수 있는 코드 -->
    <h2>내 위시리스트</h2>
    <c:choose>
        <c:when test="${not empty wishlist}">
            <table>
                <thead>
                    <tr>
                        <th>상품명</th>
                        <th>상품 ID</th>
                        <th>추가 날짜</th>
                        <th>삭제</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${wishlist}">
                        <tr>
                            <td>${item.goods_name}</td> <!-- 상품명 -->
                            <td>${item.goods_id}</td>   <!-- 상품 ID -->
                            <td>${item.wishlist_date}</td> <!-- 추가 날짜 -->
                            <td>
                                <form action="${contextPath}/wishlist/removeGoodsFromWishlist.do" method="post">
                                    <input type="hidden" name="wish_id" value="${item.wish_id}"/>
                                    <input type="submit" value="삭제"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>위시리스트에 아이템이 없습니다.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
