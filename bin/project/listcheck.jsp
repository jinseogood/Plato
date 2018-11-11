<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*,java.io.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>business solutions_sliced</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="style1.css" rel="stylesheet" type="text/css" />
</head>
<body bgcolor="#550403" style="leftmargin=0px; topmargin=0px; marginwidth=0px; marginheight=0px;">
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
      <div><br/></div>
      <div class="content">
<%
String num=request.getParameter("abc");
String dbUrl = "jdbc:mysql://203.250.133.104:3306/s1207?useUnicode=true&characterEncoding=euckr";
String dbUser = "s1207";
String dbPw = "1234";
Connection connection = null;
PreparedStatement totalStatement = null;
PreparedStatement listStatement = null;
ResultSet totalResultSet = null;
ResultSet listResultSet = null;
try {
    Class.forName("org.gjt.mm.mysql.Driver");
    connection = DriverManager.getConnection(dbUrl, dbUser, dbPw);
    
  /*  String totalSql = "SELECT COUNT(*) FROM notice";
    totalStatement = connection.prepareStatement(totalSql);
    totalResultSet = totalStatement.executeQuery();
    if(totalResultSet.next()) {
        totalRowCount = totalResultSet.getInt(1);
    }*/
%>
<form action="<%=request.getContextPath()%>/project/list.jsp" method="post">
</div>
<%    
    String listSql = "SELECT * FROM request where `¹øÈ£`='"+num+"'";
    listStatement = connection.prepareStatement(listSql);
    listResultSet = listStatement.executeQuery();
  	listResultSet.next();
%>
    <table border="0" width="90%">
        <thead>
            <tr align="center">
                <th width="20%">°¡¸ÍÁ¡ ¹øÈ£</th><td colspan="3" align="left">&nbsp<%=listResultSet.getString(2)%></td>
                <tr><th colspan="4"><hr width="100%"></hr></th></tr>
                </tr>
                <tr align="center">
                <th width="10%">ÀÛ¼ºÀÚ</th><td align="left">&nbsp<%=listResultSet.getString(3)%></td>
                <th width="20%" align="right">&nbsp&nbsp&nbsp&nbspÀÛ¼ºÀÏ</th><td align="left">&nbsp<%=listResultSet.getString(5)%></td>
                </tr>
                <tr><th colspan="4"><hr width="100%"></hr></th></tr>
                <tr align="center">
                <th width="10%">³»¿ë</th>
                <td colspan="3" align="left">&nbsp<%=listResultSet.getString(4) %></td>
                </tr>
                <tr>
                <td align="right" colspan="4">
                <input type="submit" value="¸ñ·Ï"></input>
     			</form>
     			</td>
     			</tr>
            
               
        </thead>
        <tbody>      
        </tbody>
    </table>

<%
}
     catch(Exception e) {
    e.printStackTrace();
    out.print("µ¥ÀÌÅÍ °¡Á®¿À±â ¿¡·¯!");
} finally {
    try {totalResultSet.close();} catch(Exception e){}
    try {listResultSet.close();} catch(Exception e){}
    try {totalStatement.close();} catch(Exception e){}
    try {listStatement.close();} catch(Exception e){}
    try {connection.close();} catch(Exception e){}
}
%>
      </div>
      </div></td>
	    <td rowspan="3">
	        <img src="images/index_06.jpg" width="27" height="773" alt="" /></td>
	    <td>
	        <img src="images/spacer.gif" width="1" height="76" alt="" /></td>
      </tr>
    <tr>
      <td rowspan="2"><img src="images/index_07.jpg" width="70" height="697" alt=""/></td>
       <td><div class="left_menu"><font size=5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=session.getAttribute("id")%>´Ô</font>

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
      <td colspan="3"><div id="footer">COPYRIGHT (C) 2010 &quot;YOUR SITE&quot;. DESIGN BY <a href="http://www.e-

globalspot.com">wholesale china</a></div></td>
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