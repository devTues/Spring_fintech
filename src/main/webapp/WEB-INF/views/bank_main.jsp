<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- views/bank_main.jsp -->
<h1>views/bank_main.jsp</h1>
<h1>인증 토큰발급</h1>
<!-- 	private String access_token; -->
<!-- 	private String token_type; -->
<!-- 	private int expires_in; -->
<!-- 	private String refresh_token; -->
<!-- 	private String scope; -->
<!-- 	private String user_seq_no; -->
<h2>access_token : ${responseTokenDTO.access_token }</h2>
<h2>token_type : ${responseTokenDTO.token_type }</h2>
<h2>expires_in : ${responseTokenDTO.expires_in }</h2>
<h2>refresh_token : ${responseTokenDTO.refresh_token }</h2>
<h2>영역 scope : ${responseTokenDTO.scope }</h2>
<h2>사용자 번호user_seq_no : ${responseTokenDTO.user_seq_no }</h2>


<h1>사용자 정보조회</h1>
<!-- 요청 메시지 명세 -->
<!-- HTTP 항목 필수 TYPE -->
<!-- (길이) 설명 -->
<!-- Header Authorization Y -->
<!-- 오픈뱅킹으로부터 전송받은 Access Token 을 HTTP  -->
<!-- Header 에 추가 [ scope = login, sa ]  -->
<!-- - 입력값 : Bearer <access_token> -->
<!-- Parameter user_seq_no Y AN(10) 사용자일련번호 -->
<form action="userInfo" method="get">
<input type="hidden" name="access_token" value="${responseTokenDTO.access_token }">
<input type="hidden" name="user_seq_no" value="${responseTokenDTO.user_seq_no }">
<input type="submit" value="사용자정보조회">
</form>

<h1>등록계좌조회</h1>
<form action="accountList" method="get">
<input type="hidden" name="access_token" value="${responseTokenDTO.access_token }">
<input type="hidden" name="user_seq_no" value="${responseTokenDTO.user_seq_no }">
<input type="hidden" name="include_cancel_yn" value="Y">
<input type="hidden" name="sort_order" value="D">
<input type="submit" value="등록계좌조회">
</form>

</body>
</html>