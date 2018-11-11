<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<head>

	<meta charset="EUC-KR">

	<title>Login</title>

	<link href="style2.css" rel="stylesheet" type="text/css" />

	<!--[if lt IE 9]>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

</head>

<body>

	<div id="login">

		<h2><span class="fontawesome-lock"></span>Sign In</h2>

		<form action="loginPro.jsp" method="POST">

			<fieldset>

				<p><label for="id">사업자 등록 번호</label></p>
				<p><input type="text" name="id" class="tex"></p> <!-- JS because of IE support; better: placeholder="mail@address.com" -->

				<p><label for="password">가맹점 등록 번호</label></p>
				<p><input type="password" name="stnum"></p> <!-- JS because of IE support; better: placeholder="password" -->

				<p><input type="submit" value="로그인"></p>

			</fieldset>

		</form>

	</div> <!-- end login -->

</body>	
</html>