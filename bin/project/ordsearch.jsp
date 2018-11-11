<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*,java.io.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%!
public String changeuni(String src)
{
	  char[] temp=src.toCharArray();  
	  StringBuffer result=new StringBuffer(temp.length);  
	  for(int i=0;i<temp.length;i++){
	   if(temp[i]=='&' && temp.length>i+7 && temp[i+1]=='#' && temp[i+7]==';'){
	    try{
	     result.append((char)Integer.parseInt(src.substring(i+2, i+7)));
	     i=i+7;
	    }catch(NumberFormatException e){
	     result.append(temp[i]);
	    }    
	   }
	   else
	    result.append(temp[i]);
	  }
	  result.trimToSize();
	  return result.toString();
	 }


%>
<head>
      

<title>business solutions_sliced</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="style1.css" rel="stylesheet" type="text/css" />


</head>
<body bgcolor="#550403" style="leftmargin=0px; topmargin=0px; marginwidth=0px; marginheight=0px;">

<%
request.setCharacterEncoding("euc-kr");
String option=changeuni(request.getParameter("option"));
String search=changeuni(request.getParameter("search"));
//int n=changeuni(request.getParameter(arg0))


System.out.println("option : "+option);
System.out.println("search : "+search);

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
      <div class="logo"><a href="index.jsp"><span class="style5">Plato <br />
	  Academy	</span></a></div>
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
      <div class="title">ORDER</div>
      <div class="content">
<%
String notice=request.getParameter("page");
if(notice==null){
	notice="notice";
}
int currentPage = 1;
if(request.getParameter("currentPage") != null){
    currentPage = Integer.parseInt(request.getParameter("currentPage"));
}
 
int totalRowCount = 0;
 
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
    
    String totalSql = "SELECT COUNT(*) FROM item";
    totalStatement = connection.prepareStatement(totalSql);
    totalResultSet = totalStatement.executeQuery();
    if(totalResultSet.next()) {
        totalRowCount = totalResultSet.getInt(1);
    }
%>
   <!--  <div>전체행의 수 : <%=totalRowCount%> </div>--> 
   <div align="right"> 
   <form action="<%=request.getContextPath()%>/project/ordsearch.jsp" method="post">
    <select name="option">
   <option value="`제품명`">제품명</option>
   <option value="`제품번호`">제품번호</option>
   <option value="`제품종류`">제품종류</option>
   <option value="`납품업체`">납품업체</option>
   </select>
   <input type="text" name="search" id="search"></input>
   <input type="submit" value="검색"></input>
   </form>
   </div>
   
<%    
    int pagePerRow = 15;
    //String listSql = "SELECT * FROM notice ORDER BY `번호` LIMIT ?, ?";
    //listStatement = connection.prepareStatement(listSql);
    /*listStatement.setInt(1, (currentPage-1)*pagePerRow); 
    listStatement.setInt(2, pagePerRow);*/ 
    
    String listSql="SELECT * FROM item WHERE "+option+"='"+search+"' ORDER BY `제품번호` LIMIT ?, ?";
    listStatement = connection.prepareStatement(listSql);
    listStatement.setInt(1, (currentPage-1)*pagePerRow);
    listStatement.setInt(2, pagePerRow); 
    listResultSet = listStatement.executeQuery();
    
%>
<div>
<br/>
</div>
    <table border="0" width="90%">
        <thead>
            <tr align="center">
               <th width="15%">제품번호</th>
                <th width="15%">제품종류</th>
                <th width="25%">제품명</th>
                <th width="15%">납품업체</th>
                <th width="15%">제품가격</th>
                <th width="15%">수량</th>
            </tr>
            <tr><th colspan="6"><hr width="100%"/></th></tr>
        </thead>
        <tbody>

<%
			int n=0;
            while(listResultSet.next()){
%>
					<form  action="ordercheck.jsp" method="post">
                	<tr align="center">
                    <td padding="1"><%=listResultSet.getInt(2)%></td>
                    <td padding="1"><%=listResultSet.getString(1)%></td>
                    <td padding="1"><%=listResultSet.getString(3)%></td>
                    <td padding="1"><%=listResultSet.getString(4)%></td>
                    <td padding="1"><%=listResultSet.getString(5)%></td>
                    
                     <%
                		n++;
                		System.out.println(n);
                		%>
                		
                    <td><input type="hidden" name="<%=n%>" value="<%=listResultSet.getInt(2)%>"></input>
                    <input type="number" id="<%=listResultSet.getString(3)%>" name="<%=listResultSet.getInt(2)%>" size="4" min="0" max="<%=listResultSet.getInt(6)%>" value="0"></input></td>
               
                </tr>
<%        
System.out.println(n);
            }
%>
<input type="hidden" name="length" value="<%=n%>"></input>
     
       
      <tr><td colspan="6"> <br/>
</td>
</tr>       

<tr><td colspan="6" align="right">
             <input type="submit"  value="주문" onclick="JavaScript:alert('주문이 완료 되었습니다.')">
             </input>
             <input type="reset" value="취소">
             </input>
           </td></tr>
                </tbody>
    </table>
             </form>
             
<%
    int lastPage = totalRowCount/pagePerRow;
    if(totalRowCount%pagePerRow != 0) {
        lastPage++;
    }
%>
    <div>
<%
 if(currentPage>1) {
 		for(int i=1;i<=lastPage;i++)	{
%>
            <a href="list.jsp?currentPage=<%=i%>"><%=i %></a>
<%
		}
       }
       
%>
	
   		 </div>
<%
}
     catch(Exception e) {
    e.printStackTrace();
    out.print("데이터 가져오기 에러!");
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
       <td><div class="left_menu"><font size=4><b><%=session.getAttribute("id")%>님</font>
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