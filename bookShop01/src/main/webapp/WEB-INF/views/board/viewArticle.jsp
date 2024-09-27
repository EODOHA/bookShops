<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("utf-8");
%>

<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<c:set var="article" value="${articleMap.article}"/>
<c:set var="imageFileList" value="${articleMap.imageFileList}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 보기</title>
<style>
	#tr_file_upload{
       display:none;
     }
 	#tr_btn_modify{
	display:none;
	}
</style>
<script src = "http://code.jquery.com/jquery-latest.min.js"></script>
<script type = "text/javascript">
	function backToList(obj) {
		obj.action = "${contextPath}/board/listArticles.do";
		obj.submit();
	}
	
	function fn_enable(obj){
		document.getElementById("i_title").disabled=false;
		document.getElementById("i_content").disabled=false;
		document.getElementById("i_imageFileName").disabled=false;
		document.getElementById("tr_btn_modify").style.display="table-row";
		document.getElementById("tr_file_upload").style.display="block";
		document.getElementById("tr_btn").style.display="none";
		$(".tr_modEnable").css('visibility', 'visible');
	}
	
	function fn_modify_article(obj){
		obj.action="${contextPath}/board/modArticle.do";
		obj.submit();
	}
	
	function readURL(input){
		if(input.files&&input.files[0]){   //files는 선택한 모든 파일을 나열하는 FileList객체
			var reader = new FileReader();	//업로드한 이미지 미리보기하게하는 FileReader API
			reader.onload = function(e){	//파일 읽어 들이기 성공헀을 때 호출되는 onload 이벤트 핸들러
				$('#preview').attr('src', e.target.result);	//<img> 태그의 src 속성에 읽어 들인 File 내용을 지정	
			}
			reader.readAsDataURL(input.files[0]);	//File내용을 읽어 DataURL 형식의 문자열로 저장
		}
	}
	function fn_remove_article(url, articleNO){ 
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		var articleNOInput = document.createElement("input");
		articleNOInput.setAttribute("type", "hidden");
		articleNOInput.setAttribute("name", "articleNO");
		articleNOInput.setAttribute("value", articleNO);
			
		form.appendChild(articleNOInput);
		document.body.appendChild(form) //완성한 태그를 html에 붙이기
		form.submit(); //전송
	}
</script>
</head>
<body>
	<form name="frmArticle" method="post" action="${contextPath }" encType="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">
				글번호
				</td>
				<td>
				<input type="text" value="${article.articleNO }" disabled />
				<input type="hidden" name="articleNO" value="${article.articleNO }" />
				</td>
			</tr>
			<tr>
				<td width="200" align="center" bgcolor="#FF9933">
				작성자 아이디
				</td>
				<td>
				<input type="text" value="${article.member_id }" name="writer" disabled />
				</td>
			</tr>
			<tr>
				<td width="200" align="center" bgcolor="#FF9933">
				제목
				</td>
				<td>
				<input type="text" value="${article.title }" name="title" id="i_title" disabled />
				</td>
			</tr>
			 <tr>
   				<td width="200" align="center" bgcolor="#FF9933">
     			 내용
  			    </td>
   				<td>
    			<textarea rows="20" cols="60"  name="content"  id="i_content"  disabled >${article.content }</textarea>
  				</td>  
  			</tr>
  			<c:if test="${not empty imageFileList && imageFileList!='null'}">
				  <c:forEach var="item" items="${imageFileList}" varStatus="status" >
					    <tr>
						    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
						      이미지${status.count}
						   </td>
						   <td>
						     <input type= "hidden" name="originalFileName" value="${item.imageFileName }" />
						    <img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${item.imageFileName}" id="preview"  /><br>
						   </td>   
						  </tr>  
						  <tr>
						    <td>
						       <input type="file" name="imageFileName" id="i_imageFileName" disabled onchange="readURL(this);"   />
						    </td>
						 </tr>
					</c:forEach>
			 </c:if>  			
			 <c:choose>
			<c:when test="${not empty article.imageFileName && article.imageFileName!='null'}">
				<tr>
					<td width="150" align="center" bgcolor="#FF9933" rowspan="2">
					이미지
					</td>
					<td>
					<input type="hidden" name="originalFileName" value="${article.imageFileName }" /> 
					<img src="${contextPath }/download.do?articleNO=${article.articleNO }&imageFileName=${article.imageFileName}" id="preview" /><br>
					</td>
				</tr>
				<tr>
					<td>
					<input type="file" name="imageFileName"	id="i_imageFileName" disabled onchange="readURL(this);" />
					</td>
				</tr>
			</c:when>
			<c:otherwise>			
		    <tr  id="tr_file_upload" >
				    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
				      이미지
				    </td>
				    <td>
				      <input type= "hidden" name="originalFileName" value="${article.imageFileName }" />
				    </td>
			    </tr>
			    <tr>
				    <td ></td>
				    <td>
				       <img id="preview"/><br>
				       <input type="file" name="imageFileName" id="i_imageFileName" disabled onchange="readURL(this);"   />
				    </td>
			 	 </tr>
		 </c:otherwise>
	 </c:choose>
			<tr id="tr_file_upload">
				<td width = "150" align = "center" bgcolor = "#FF9933" rowspan="2">
				등록일자
				</td>
				<td>
				<input type = "text" value = "<fmt:formatDate value = "${article.writeDate}"/>"disabled/>
				</td>
			</tr>
			<tr id = "tr_btn_modify">
				<td colspan="2" align = "center">
					<input type = "button" value = "수정반영하기" onClick="fn_modify_article(frmArticle)"> 
					<input type = "button" value = "취소" onClick = "backToList(frmArticle)">
				</td>
			</tr>
			<tr id = "tr_btn">
				<td colspan="2" align = "center">
				 	<c:if test = "${member.member_id == article.member_id }">
						<input type = "button" value = "수정하기" onClick = "fn_enable(this.form)">
						<input type = "button" value = "삭제하기" onClick = "fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
					</c:if> 
			<input type = "button" value = "리스트로 돌아가기" onClick = "backToList(this.form)">
			</td>
			</tr>			
		</table>
	</form>
</body>
</html>