<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action ="/guestbook3/add" method ="post">
			<table border ="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value=""></td>
					<td>비밀번호</td>
					<td><input type="text" name="password" value=""></td>
				</tr>
				<tr>
					<td colspan="4"><textarea cols="50" rows="10" name="content"></textarea></td>
				</tr>
				<tr>
					<td colspan="4"><button type="submit">확인</button></td>
				</tr>		
			</table>
			<input type="hidden" name="action" value="add">
		</form>
		
		
		<c:forEach items = "${requestScope.guestbookList }" var = "gbVo" varStatus = "status">
				<table border="1">
					<tr>
			<!-- 번호 -->	<td>${gbVo.no}</td>
			<!-- 이름 --><td>${gbVo.name}</td>
			<!-- 날짜 --><td>${gbVo.regDate}</td>
			<!-- 삭제 --><td><a href="/guestbook2/gbc?action=dform&no=${gbVo.no}">삭제</a></td>
					</tr>
					<tr>
			<!-- 내용 --><td colspan="4">${gbVo.content}</td>
					<tr>
				</table>
				<br>
		</c:forEach>	
		

</body>
</html>