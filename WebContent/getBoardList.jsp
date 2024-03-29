<%@page import="java.util.List"%>
<%@page import="com.multicampus.biz.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>글 목록</title>
</head>

<%
	// 세션에서 글 목록을 꺼내서 화면 구성한다. 
	List<BoardVO> boardList = (List)request.getAttribute("boardList");
%>

<body>
<center>
<h1>글 목록</h1>
<h3><%= session.getAttribute("userName") %>님 로그인 환영합니다......
<a href="logout.do">Log-out</a></h3>

<!-- 검색 시작 -->
<form action="getBoardList.do" method="post">
	<table border="1" cellpadding="0" cellspacing="0" width="700">
	<tr>
		<td align="right">
			<select name="condition">
			<option value="TITLE">제목
			<option value="CONTENT">내용
			</select>
			<input name="keyword" type="text"/>
			<input type="submit" value="검색"/>
		</td>
	</tr>
	</table>
</form>
<!-- 검색 종료 -->

<table border="1" cellpadding="0" cellspacing="0" width="700">
<tr>
	<th bgcolor="orange" width="100">번호</th>
	<th bgcolor="orange" width="200">제목</th>
	<th bgcolor="orange" width="150">작성자</th>
	<th bgcolor="orange" width="150">등록일</th>
	<th bgcolor="orange" width="100">조회수</th>
</tr>
<%for(BoardVO board : boardList){ %>
<tr>
	<td><%= board.getSeq() %></td>
	<td align="left"><a href="getBoard.do?seq=<%=board.getSeq()%>"><%= board.getTitle() %></a></td>
	<td><%= board.getWriter() %></td>
	<td><%= board.getRegDate() %></td>
	<td><%= board.getCnt() %></td>
</tr>
<%} %>
</table>
<br>
<a href="addBoardView.do">새글 등록</a>
</center>
</body>
</html>












