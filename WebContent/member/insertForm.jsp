<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>insertForm</title>
</head>
<body>
	<h1>WebContent/member/insertForm.jsp</h1>
	
	<h2>회원가입 페이지(model2)</h2>
	<!-- 
		아이디, 비밀번호(password), 이름, 나이, 성별(radio), 이메일 입력받아서
		insertPro.jsp 페이지에 전달 -> DB 저장
	 -->
	
	<fieldset>
		<form action="./MemberJoinAction.me" method="post">
			<pre>
			아이디 : <input type="text" name="id"><br>
			비밀번호 : <input type="password" name="pass"><br>
			이름 : <input type="text" name="name"><br>
			나이 : <input type="text" name="age"><br>
			성별 : 남<input type="radio" name="gender" value="남">&nbsp;
			       여<input type="radio" name="gender" value="여"><br>
			이메일 : <input type="text" name="email"><br>
				
			<hr>
			
			
			<input type="submit" value="회원 가입">
			</pre>
		</form>
	</fieldset>
</body>
</html>