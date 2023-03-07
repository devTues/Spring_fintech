<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<!--  인증 요청 -->
<!-- 요청 메시지 URL 설명서 p8-->
<!-- HTTP URL https://openapi.openbanking.or.kr/oauth/2.0/authorize -->
<!-- HTTP Method GET -->
<!-- ㅇ 요청 메시지 명세 response_type , client_id, redirect_uri 
scope, state, auth_type -->
<form action="https://testapi.openbanking.or.kr/oauth/2.0/authorize" method="get">
<input type="hidden" name="response_type" value="code">
<input type="hidden" name="client_id" value="Client ID 회원가입">
<input type="hidden" name="redirect_uri" value="http://localhost:8080/fintech/callback">
<input type="hidden" name="scope" value="login inquiry transfer">
<input type="hidden" name="state" value="12345678123456781234567812345678">
<input type="hidden" name="auth_type" value="0">

<input type="submit" value="사용자인증 토큰발급">
</form>
</body>
</html>




