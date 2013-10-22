<%--
 TITLE :		MAIN 프레임
 CREATOR :		김민혁
 CREATE DATE :	2012/12/23
 DESCRIPTION :	MAIN 프레임 
 Update History
--%>

<%@ page contentType="text/html; charset=utf-8" %>

<jsp:useBean id="topFrame" 	scope="request" class="java.lang.String" />
<jsp:useBean id="bodyFrame" scope="request" class="java.lang.String" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>전사통합 리스크관리 시스템</title>

<script language="JavaScript">

function SetPopCookie(cookieName,cookieVal){
	date=new Date();
	validity=1;
	date.setDate(date.getDate()+validity);
	document.cookie=cookieName+'='+escape(cookieVal)+'; expires='+date.toGMTString();
}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<frameset name="frameset1" rows="146,*" frameborder="NO" border="0" framespacing="0">
	<frame name="topFrame" 	scrolling="NO" noresize src="/main/top.jsp" >
	<frameset name="frameset2" rows="*" frameborder="NO" border="0" framespacing="0"> 
		<frame name="mainFrame" src="<%=bodyFrame%>" scrolling="YES">
	</frameset>
</frameset>

<noframes>

<body bgcolor="#FFFFFF" text="#000000" >
</body>

</noframes>
</html>
</body>
</html>
