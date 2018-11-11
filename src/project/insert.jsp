<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*,java.io.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

HttpSession session = request.getSession();
(String)session.setAttribute("stnum", stnum);
(String)session.setAttribute("id", id);
<html xmlns="http://www.w3.org/1999/xhtml">
<%!
public void Rownum(String table,int n){
				try{
					
					
					int i=0;
					Connection con = null;
					Statement st=null;
					Class.forName("org.gjt.mm.mysql.Driver");
				    con = DriverManager.getConnection("jdbc:mysql://203.250.133.104:3306/s1207", "s1207", "1234");
					
				    String sql="Select `번호` from "+table;
					st=con.createStatement();
					ResultSet rs=st.executeQuery(sql);
					
					String row[]=new String[n];
					
					while(rs.next()){
							row[i]=rs.getString(1);	
							i++;
					}
					
					sql="set @rownum:=0;";
	
					st=con.createStatement();
					int k=st.executeUpdate(sql);
					
					sql="select @rownum:=@rownum+1 from "+table;
					st=con.createStatement();
					rs=st.executeQuery(sql);
					String num[]=new String[n];
					i=0;
					while(rs.next()){
							num[i]=rs.getString(1);	
							i++;
						}
				
					for(i=0;i<n;i++){
					sql="update "+table+" as a set a.`번호`="+Integer.parseInt(num[i])+" where a.`번호`="+Integer.parseInt(row[i])+"";
			
					st=con.createStatement();
					int m=st.executeUpdate(sql);
					}
					
					rs.close();
					st.close();
					con.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title></title>
</head>
<body>
<%
request.setCharacterEncoding("euc-kr");
String stnum = (String)session.getAttribute("stnum");
String id = (String)session.getAttribute("id");
String memo = request.getParameter("memo");
String totalrow=request.getParameter("totalrow");
out.print(totalrow);
System.out.println(totalrow);
out.println("id : "+id);

//System.out.println(id);

String dbUrl = "jdbc:mysql://203.250.133.104:3306/s1207";
String dbUser = "s1207";
String dbPw = "1234";
String name="";
Connection connection = null;
PreparedStatement statement = null;
ResultSet listResultSet=null;

try {
    Class.forName("org.gjt.mm.mysql.Driver");
    connection = DriverManager.getConnection(dbUrl, dbUser, dbPw);
    String sql="select `대표자` from store where `사업자번호`='"+id+"'";
    statement = connection.prepareStatement(sql);
    listResultSet = statement.executeQuery();
    listResultSet.next();
    name=listResultSet.getString(1);
    out.println(name);
    		
    sql = "INSERT INTO request(`사업자번호`, `작성자`, `내용`, `작성일`) values(?,?,?,now())";
    statement = connection.prepareStatement(sql);
    statement.setString(1,id);
    statement.setString(2,name);
    statement.setString(3,memo);
    statement.executeUpdate();
//  response.sendRedirect(request.getContextPath()+"/board/boardList.jsp");
	this.Rownum("request",Integer.parseInt(totalrow)+1);
} catch(Exception e) {
    e.printStackTrace();
    out.print("입력 예외 발생");
} finally {
    try {statement.close();} catch(Exception e){}
    try {connection.close();} catch(Exception e){}
}
%>
</body>
<meta http-equiv='Refresh' content='0; URL=list.jsp'>
</html>

