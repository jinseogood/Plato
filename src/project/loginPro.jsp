<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "java.sql.*, java.io.*, java.util.*, java.net.URLEncoder" %>
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
<%
request.setCharacterEncoding("EUC-KR");
session.setAttribute("id", request.getParameter("id"));
session.setAttribute("stnum", request.getParameter("stnum"));
%>
<%
request.setCharacterEncoding("EUC-KR");
String id = changeuni(request.getParameter("id"));
String stnum = changeuni(request.getParameter("stnum"));

String DBdriver = "org.gjt.mm.mysql.Driver";
String DBurl = "jdbc:mysql://203.250.133.104:3306/s1207";
String DBUser = "s1207";
String DBPw = "1234";

Class.forName(DBdriver);

Connection con = null;
PreparedStatement stmt = null;
ResultSet rs = null;
String usrstnum = "";
try{
	con = DriverManager.getConnection(DBurl, DBUser, DBPw);
	String sql = "Select * From login_test Where id=?";
	stmt = con.prepareStatement(sql);
	stmt.setString(1, id);
	rs = stmt.executeQuery();
	
	if(rs.next()){
		usrstnum = rs.getString("stnum");
		if(usrstnum.equals(stnum)){
			session.setAttribute("id", id);
			response.sendRedirect("index.jsp?name="+URLEncoder.encode(id, "UTF-8"));
		}
		else {
		%>
			<script type="text/javascript">
			alert("사용 불일치");
			history.go(-1);
			</script>
			<% 
			}
	}
	else{
		%>
		<script type="text/javascript">
		alter("해당 가맹점 없음");
		history.go(-1);
		</script>
		<%
	}
}catch(Exception e){
	e.printStackTrace();
}
%>