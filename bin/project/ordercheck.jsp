<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*,java.io.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  HttpSession session = request.getSession();
(String)session.setAttribute("stnum", stnum);
(String)session.setAttribute("id", id);-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ORDER CHECK</title>
<%!
public String GetDate(){
	Calendar rightnow=Calendar.getInstance();
	String Date=rightnow.get(Calendar.YEAR)+"-"+(rightnow.get(Calendar.MONTH)+1)+"-"+
			rightnow.get(Calendar.DATE);
	return Date;
}
%>
</head>
<body>
<%
request.setCharacterEncoding("euc-kr");

String stnum = (String)session.getAttribute("stnum");
String id = (String)session.getAttribute("id");

String len=request.getParameter("length");
int length=Integer.parseInt(len);
out.println(length+1);
out.println("1 : "+request.getParameter("1"));
out.println("2 : "+request.getParameter("1"));
int []n=new int[length+1];
n[0]=0;
for(int i=1;i<length+1;i++){
	n[i]=Integer.parseInt(request.getParameter(request.getParameter(i+"")));
	out.print(n[i]);
}




String dbUrl = "jdbc:mysql://203.250.133.104:3306/s1207";
String dbUser = "s1207";
String dbPw = "1234";
Connection connection = null;
PreparedStatement statement = null;
try {
	
	for(int i=1;i<length+1;i++){
		Class.forName("org.gjt.mm.mysql.Driver");
		connection = DriverManager.getConnection(dbUrl, dbUser, dbPw);
    String sql = "update item set `수량`=`수량`-'"+n[i]+"' where `제품번호`='"+request.getParameter(i+"")+"'";
    statement = connection.prepareStatement(sql);
    statement.executeUpdate();
//  response.sendRedirect(request.getContextPath()+"/board/boardList.jsp");
	}
	
	
	for(int i=1;i<length+1;i++){
		if(n[i]!=0){
			Class.forName("org.gjt.mm.mysql.Driver");
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			//String sql="insert into order values('"+request.getParameter(i+"")+"','"+n[i]+"','"+GetDate()+"')";		
			String sql="INSERT INTO orderitem(`제품번호`,`수량`,`주문일자`,`주문자`) values(?,?,?,?)";
			statement = connection.prepareStatement(sql);
		    statement.setInt(1,Integer.parseInt(request.getParameter(i+"")));
		    statement.setInt(2,n[i]);
		    statement.setString(3,GetDate());
		    statement.setString(4,id);
		    statement.executeUpdate();

		}
	}
} catch(Exception e) {
    e.printStackTrace();
    out.print("입력 예외 발생");
} finally {
    try {statement.close();} catch(Exception e){}
    try {connection.close();} catch(Exception e){}
}




%>
<meta http-equiv='Refresh' content='0; URL=order.jsp'>


















</body>
</html>