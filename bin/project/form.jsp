<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*,java.io.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- HttpSession session = request.getSession();
(String)session.setAttribute("id", id); -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>business solutions_sliced</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="style1.css" rel="stylesheet" type="text/css" />
</head>
<body bgcolor="#550403" style="leftmargin=0px; topmargin=0px; marginwidth=0px; marginheight=0px;">
<%
request.setCharacterEncoding("euc-kr");
String totalrow=request.getParameter("totalrow");
System.out.println(totalrow);

%>
<span class="style6"></span>
<!-- Save for Web Slices (business solutions_sliced.psd) -->
<div id="body">
<br />
<br />
<br />
 <table style="width=1001px; height=1001px;" border="0" align="center" cellpadding="0" cellspacing="0" id="Table_01">
    <tr>
      <td colspan="2" rowspan="3"><div id="logo">
      <div class="logo"><a href="index.jsp"><span class="style5">Plato<br />
	  Academy</span></a></div>
      </div></td>
<td colspan="3"><div class="style1" id="header">
			<br />
			Plato Academy<span class="style3">&nbspCo.,Ltd</span>
             <span class="style4"><br />
             The Future Is Gained From Present Pains!            </span></div></td>
	      <td>
		      <img src="images/spacer.gif" width="1" height="185" alt="" /></td>
      </tr>
    <tr>
      <td><div class="top_menu">
      <ul>
      <li><a href="index.jsp">HOME</a></li>
      <li><a href="notice.jsp">NOTICE</a></li>
      <li><a href="list.jsp">FAQ</a></li>
      <li><a href="order.jsp">ORDER</a></li>
      </ul>
      </div></td>
	    <td colspan="2">
	        <img src="images/index_04.jpg" width="128" height="42" alt="" /></td>
	    <td>
	        <img src="images/spacer.gif" width="1" height="42" alt="" /></td>
      </tr>
    <tr>
      <td colspan="2" rowspan="2"><div id="content">
      <div class="title">FAQ</div>
      <div class="content">
        <form action="<%=request.getContextPath()%>/project/insert.jsp" method="post">
    <!--<div>°¡¸ÍÁ¡¹øÈ£ : </div>
    <div><input name="stnum" id="stnum" type="text"/></div>
    <div>ÀÛ¼ºÀÚ : </div>
    <div><input name="name" id="name" type="text"/></div>-->
    <div><br/></div>
    <table border="0" width="90%">
    <tr align="left"><th>³»¿ë : </th></tr>
    <tr><td align="center"><textarea name="memo" id="memo" rows="17" cols="87"></textarea></td></tr>
    <tr><td align="right">
    <input type="hidden" name="totalrow" value="<%=totalrow %>"></input>
        <input type="submit" value="±ÛÀÔ·Â"/>
        <input type="reset" value="ÃÊ±âÈ­"/>
      </td></tr></table>
      </div></td>
	    <td rowspan="3">
	        <img src="images/index_06.jpg" width="27" height="773" alt="" /></td>
	    <td>
	        <img src="images/spacer.gif" width="1" height="76" alt="" /></td>
      </tr>
    <tr>
      <td rowspan="2"><img src="images/index_07.jpg" width="70" height="697" alt=""/></td>
      <td><div class="left_menu"><font size=4><b><%=session.getAttribute("id")%>´Ô</font>
        <ul>
        <li><a href="index.jsp">HOME</a></li>
        <li><a href="notice.jsp">NOTICE</a></li>
        <li><a href="list.jsp">FAQ</a></li>
        <li><a href="order.jsp">ORDER</a></li>
        <li><a href="logout.jsp">LOGOUT</a></li>
        </ul>
        </div></td>
	    <td>
	        <img src="images/spacer.gif" width="1" height="651" alt="" /></td>
      </tr>
    <tr>
      <td colspan="3"><div id="footer">COPYRIGHT (C) 2016 &quot;Plato Academy Co.,Ltd SITE&quot;. DESIGN BY <a href="#">Plato Academy Co.,Ltd</a></div></td>
  <td>
	        <img src="images/spacer.gif" width="1" height="46" alt="" /></td>
      </tr>
    <tr>
      <td>
        <img src="images/spacer.gif" width="70" height="1" alt="" /></td>
	    <td>
	        <img src="images/spacer.gif" width="179" height="1" alt="" /></td>
	    <td>
	        <img src="images/spacer.gif" width="623" height="1" alt="" /></td>
	    <td>
	        <img src="images/spacer.gif" width="101" height="1" alt="" /></td>
	    <td>
	        <img src="images/spacer.gif" width="27" height="1" alt="" /></td>
	    <td></td>
      </tr>
  </table>
  <div id="lft"><a href="#">Plato Academy Co.,Ltd</a></div><br />
  <br />
</div>
<!-- End Save for Web Slices -->
</body>
</html>