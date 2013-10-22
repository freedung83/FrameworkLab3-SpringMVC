<%@page import="java.util.List"%>
<%@page import="com.multicampus.biz.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�� ���</title>
</head>

<%
	// ���ǿ��� �� ����� ������ ȭ�� �����Ѵ�. 
	List<BoardVO> boardList = (List)request.getAttribute("boardList");
%>

<body>
<center>
<h1>�� ���</h1>
<h3><%= session.getAttribute("userName") %>�� �α��� ȯ���մϴ�......
<a href="logout.do">Log-out</a></h3>

<!-- �˻� ���� -->
<form action="getBoardList.do" method="post">
	<table border="1" cellpadding="0" cellspacing="0" width="700">
	<tr>
		<td align="right">
			<select name="condition">
			<option value="TITLE">����
			<option value="CONTENT">����
			</select>
			<input name="keyword" type="text"/>
			<input type="submit" value="�˻�"/>
		</td>
	</tr>
	</table>
</form>
<!-- �˻� ���� -->

<table border="1" cellpadding="0" cellspacing="0" width="700">
<tr>
	<th bgcolor="orange" width="100">��ȣ</th>
	<th bgcolor="orange" width="200">����</th>
	<th bgcolor="orange" width="150">�ۼ���</th>
	<th bgcolor="orange" width="150">�����</th>
	<th bgcolor="orange" width="100">��ȸ��</th>
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
<a href="addBoardView.do">���� ���</a>
</center>
</body>
</html>











