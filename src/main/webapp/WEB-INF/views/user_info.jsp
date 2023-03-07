<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- user_info.jsp -->
<h1> 사용자 정보 조회</h1>
<h3>거래고유번호(API) : ${userInfoResponseDTO.api_tran_id }</h3>
<h3>거래일시 : ${userInfoResponseDTO.api_tran_dtm }</h3>
<h3>응답코드 : ${userInfoResponseDTO.rsp_code }</h3>
<h3>응답메시지 : ${userInfoResponseDTO.rsp_message }</h3>
<br>
<h3>고객번호 : ${userInfoResponseDTO.user_seq_no }</h3>
<h3>고객CI값 : ${userInfoResponseDTO.user_ci }</h3>
<h3>고객이름 : ${userInfoResponseDTO.user_name }</h3>
<br>
<h3>계좌수 : ${userInfoResponseDTO.res_cnt }</h3>

</body>
</html>