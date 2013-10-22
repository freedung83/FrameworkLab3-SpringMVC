
<%--
 TITLE :		TOP 프레임
 CREATOR :		김민혁
 CREATE DATE :	2012/11/23
 DESCRIPTION :	TOP 프레임

 Update History
--%>
<%@page import="java.util.List"%>
<%@page import="com.multicampus.biz.common.EntityObject"%>
<%@page import="com.multicampus.biz.common.ListObject"%>
<%@page import="com.multicampus.biz.common.Utilities"%>
<%@page import="com.multicampus.biz.main.vo.MainVO"%>
<%@ page contentType="text/html; charset=utf-8" %>

<%
EntityObject eo = null;
ListObject firstMenuLo = null;
ListObject lo = null;
EntityObject secondMenuEo = null;

//세션에 저장된 대메뉴코드 구하기
firstMenuLo = (ListObject)request.getAttribute("mainList1");

for(int i=0; i<firstMenuLo.size(); i++) {
    eo = (EntityObject)firstMenuLo.get(i);
}

secondMenuEo = (EntityObject)request.getAttribute("mainList2");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>전사통합 리스크관리 시스템</title>
<link rel="stylesheet" href="../../css/sys1.css" type="text/css" />
<script language="javascript" src="../js/sys1.js"></script>
<script language="javascript">

// 메뉴 클릭시 메뉴 프레임 변경
function goToMainFrame(urlName, menu, depth1, depth2){
	document.topForm.menuId.value=menu;
	
	//initGnbMenuFirst(depth1, depth2, false);
	
    if (urlName == 'cmd='){
        window.open ("/posco/include/underconstruction.jsp","mainFrame");
    }
    else{
        document.topForm.action=urlName;
        document.topForm.target='mainFrame';
        //MM_menuid(menu);
        document.topForm.submit();
    }
    //gnb2menuHideAll();
    initGnbMenu(depth1, depth2, false);
    onLocationDisplay(menu);
}

function onLocationDisplay(menu){
	
	var gnb1navi;
	var gnb2navi;
	
	for (var i=1;i<=6;i++)//메뉴삭제시 1차메뉴수가 안맞아 gnb1menuEl.length 대신 숫자로 넣음
	{
		if(i==6){
			gnb1navi = document.getElementById("990000");
		}else{
			gnb1navi = document.getElementById("0"+i+"0000");
		}
		
		if(gnb1navi!=null){
			gnb1navi.style.display = "none";
		}
		
		for(var j=1;j<=7;j++){
			if(i==6){
				gnb2navi = document.getElementById("990"+j+"00");
			}else{
				gnb2navi = document.getElementById("0"+i+"0"+j+"00");
			}
			
			if(gnb2navi!=null){
				gnb2navi.style.display = "none";
			}
		}
	}
	var navi = document.getElementById(menu);
	navi.style.display = "inline";
}

</script>
</head>
<body style="margin: 0; padding: 0;" onload="javascript:initGnbMenu(1,0,true);">
<form name="topForm" method="post">
<input type="hidden" name="menuId" value="" />
<!-- #header -->
<div id="header">
	<div class="inner">
		<h1 id="logo">
			<a href="javascript:initGnbMenuFirst(1, 0, true);
			goToMainFrame('/F51/F51010/servlets/DashboardS?cmd=DASHBOARD_MAIN','010000',1,0);" title="홈으로 가기"><img src="../../images/header/logo.gif" alt="POSPIA 3.0 - 전사통합 리스크관리 시스템" />
			</a>
		</h1>
        
        <!-- #gnbmenu -->
        <div id="gnbmenu">
        	<h4>주 메뉴</h4>
            <ul id="gnb1menu">
            
<%

	ListObject secondMenuLo = new ListObject();
	EntityObject secondEo = new EntityObject();

	//반복문을 사용하여 권한에 따른 보여줘야할 메뉴를 화면에 출력한다.
	for(int i=0; i<firstMenuLo.size(); i++) {
		eo = (EntityObject)firstMenuLo.get(i);
		
		//메뉴가 리스크 이슈, Dashboard는 대메뉴에서 바로 메뉴를 디스플레이 해준다.
		if(((String)eo.get("cd_v")).equals("010000")){
			
%>
				<li>
					<a href="javascript:initGnbMenuFirst(1, 0, true);goToMainFrame('<%=(String)eo.get("url")%>','<%=eo.get("cd_v")%>',1,0);" id="gnb1m1">
						<img src="../../images/header/gnb1d1.gif" width="148" height="39" alt="Dashboard" />
					</a>
				</li>
<%			
		}else if(((String)eo.get("cd_v")).equals("020000")){
%>
				<li><a href="#n" id="gnb1m2"><img src="../../images/header/gnb1d2.gif" width="148" height="39" alt="지표관리" /></a>
					<ul id="gnb2m2">				
<%
			secondMenuLo = (ListObject)secondMenuEo.get("020000");
			for(int j=1;j<=secondMenuLo.size();j++){
				secondEo = (EntityObject)secondMenuLo.get(j-1);
%>
						<li id="gnb2m2m<%=j%>"><a href="javascript:initGnbMenuFirst(2, <%=j%>, true);goToMainFrame('<%=(String)secondEo.get("URL")%>','020<%=j%>00',2,<%=j%>);"><%=(String)secondEo.get("MENU_NAME")%></a></li>					
<%				
			}
%>
                    </ul>
                </li>				
<%
		}else if(((String)eo.get("cd_v")).equals("030000")){
%>
				<li>
					<a href="javascript:initGnbMenuFirst(3, 0, true);goToMainFrame('<%=(String)eo.get("url")%>','<%=eo.get("cd_v")%>',3,0);" id="gnb1m3">
						<img src="../../images/header/gnb1d3.gif" width="148" height="39" alt="리스크이슈" />
					</a> 
				</li>
<%
		}else if(((String)eo.get("cd_v")).equals("040000")){
%>
	            <li><a href="#n" id="gnb1m4"><img src="../../images/header/gnb1d4.gif" width="148" height="39" alt="리스크맵" /></a>
		            <ul id="gnb2m4">
<%
			secondMenuLo = (ListObject)secondMenuEo.get("040000");
			for(int j=1;j<=secondMenuLo.size();j++){
				secondEo = (EntityObject)secondMenuLo.get(j-1);
%>
						<li id="gnb2m4m<%=j%>"><a href="javascript:initGnbMenuFirst(4, <%=j%>, true);goToMainFrame('<%=(String)secondEo.get("URL")%>','040<%=j%>00',4,<%=j%>);"><%=(String)secondEo.get("MENU_NAME")%></a></li>					
<%				
			}
%>		            
		            </ul>
	        	</li>
<%        
		}else if(((String)eo.get("cd_v")).equals("050000")){
%>
                <li><a href="#n" id="gnb1m5"><img src="../../images/header/gnb1d5.gif" width="148" height="39" alt="리스크관리활동" /></a>
                    <ul id="gnb2m5">
<%
			secondMenuLo = (ListObject)secondMenuEo.get("050000");
			for(int j=1;j<=secondMenuLo.size();j++){
				secondEo = (EntityObject)secondMenuLo.get(j-1);
%>
						<li id="gnb2m5m<%=j%>"><a href="javascript:initGnbMenuFirst(5, <%=j%>, true);goToMainFrame('<%=(String)secondEo.get("URL")%>','050<%=j%>00',5,<%=j%>);"><%=(String)secondEo.get("MENU_NAME")%></a></li>					
<%				
			}
%>	                    
                    </ul>
                </li>
<%
		}else if(((String)eo.get("cd_v")).equals("990000")){
%>			
	            <li><a href="#n" id="gnb1m6"><img src="../../images/header/gnb1d6.gif" width="148" height="39" alt="관리자기능" /></a>
		            <ul id="gnb2m6">
<%
			secondMenuLo = (ListObject)secondMenuEo.get("990000");
			for(int j=1;j<=secondMenuLo.size();j++){
				secondEo = (EntityObject)secondMenuLo.get(j-1);
%>
						<li id="gnb2m6m<%=j%>"><a href="javascript:initGnbMenuFirst(6, <%=j%>, true);goToMainFrame('<%=(String)secondEo.get("URL")%>','990<%=j%>00',6,<%=j%>);"><%=(String)secondEo.get("MENU_NAME")%></a></li>					
<%				
			}
%>		            
		            </ul>
				</li>
<%        		
		}
	}
%>            
            </ul>
        </div>
        
        <!-- //#gnbmenu -->
        <div id="cdate"><%=Utilities.getTodayDateTimeJsp()%></div> 
        
        <div id="location">
        	<span id = "010000" style="display:inline">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">Dashboard</a>
            </span>
           	<span id = "020100" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">지표관리</a>
	            <a href="#n">외부환경지표</a>
            </span>
            <span id = "020200" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">지표관리</a>
	            <a href="#n">전사선행지표</a>
            </span>
            <span id = "020300" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">지표관리</a>
	            <a href="#n">전사결과지표</a>
            </span>
            <span id = "030000" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">리스크이슈</a>
            </span>
            <span id = "040100" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">리스크맵</a>
	            <a href="#n">외부환경리스크맵</a>
            </span>
            <span id = "040200" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">리스크맵</a>
	            <a href="#n">국가리스크맵</a>
            </span>
            <span id = "050100" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">리스크관리활동</a>
	            <a href="#n">전사차원활동</a>
            </span>
            <span id = "050200" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">리스크관리활동</a>
	            <a href="#n">이상항목대응활동</a>
            </span>
            <span id = "990100" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">관리자기능</a>
	            <a href="#n">지표등록</a>
            </span>
            <span id = "990200" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">관리자기능</a>
	            <a href="#n">이슈등록</a>
            </span>
            <span id = "990300" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">관리자기능</a>
	            <a href="#n">리스크맵등록</a>
            </span>
            <span id = "990400" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">관리자기능</a>
	            <a href="#n">관리활동등록</a>
            </span>
            <span id = "990500" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">관리자기능</a>
	            <a href="#n">권한그룹별메뉴관리</a>
            </span>
            <span id = "990600" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">관리자기능</a>
	            <a href="#n">권한그룹별사용자관리</a>
            </span>
            <span id = "990700" style="display:none">
	            <h4>현재 위치</h4>
	            <a href="#n" class="home">전사통합 리스크관리 시스템</a> 
	            <a href="#n">관리자기능</a>
	            <a href="#n">사용자관리</a>
            </span>
        </div>
        <!-- <script type="text/javascript">onLocation("location");</script> 순번 마지막 활성화 처리 -->
     </div>
</div>
<!-- //#header -->
</form>
</body>
</html>