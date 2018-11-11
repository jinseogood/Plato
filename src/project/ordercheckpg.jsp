<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("euc-kr");

/*String stnum = (String)session.getAttribute("stnum");
String id = (String)session.getAttribute("id");*/

String len=request.getParameter("length");
int length=Integer.parseInt(len);
int []n=new int[length+1];
for(int i=1;i<length+1;i++){
	n[i]=Integer.parseInt(request.getParameter(request.getParameter(i+"")));
	out.print(n[i]);
}
%>
</body>
</html>