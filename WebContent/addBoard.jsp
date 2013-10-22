<%@page contentType="text/html; charset=EUC-KR"%>
<%@taglib prefix="form" 
          uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>새글등록</title>
</head>
<body>
<center>
	<h3>새글 등록하기............<a href='./logout.do'>LOG-OUT</a></h3>
	<hr>
		<form action="addBoard.do" method="post" enctype="multipart/form-data">
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td>제목</td><td align="left">
				<input type="text" name="title"/>
				<font color="red">
				<form:errors path="board.title"/>
				</font>
				</td>
			</tr>
			<tr>
				<td>작성자</td><td align="left"><input type="text" name="writer" size="10"/><font color="red"><form:errors path="board.writer"/></font></td>
			</tr>
			<tr>
				<td>내용</td><td align="left"><textarea name="content" cols="40" rows="10"></textarea><font color="red"><form:errors path="board.content"/></font></td>
			</tr>
			<tr>
				<td>이미지</td><td align="left"><input type="file" name="uploadFile"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value=" 새글 등록 "/></td>
			</tr>
		</table>
		</form>
	<hr>
</center>
</body>
</html>