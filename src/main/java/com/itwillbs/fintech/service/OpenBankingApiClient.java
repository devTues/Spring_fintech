package com.itwillbs.fintech.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.itwillbs.fintech.domain.AccountRequestDTO;
import com.itwillbs.fintech.domain.AccountResponseDTO;
import com.itwillbs.fintech.domain.RequestTokenDTO;
import com.itwillbs.fintech.domain.ResponseTokenDTO;
import com.itwillbs.fintech.domain.UserInfoRequestDTO;
import com.itwillbs.fintech.domain.UserInfoResponseDTO;

@Service
public class OpenBankingApiClient {

	//토큰발급 메서드
	public ResponseTokenDTO requestToken(RequestTokenDTO requestTokenDTO){
//		ㅇ 요청 메시지 URL
//		HTTP URL https://testapi.openbanking.or.kr/oauth/2.0/token
//		HTTP Method POST
//		Content-Type application/x-www-form-urlencoded; charset=UTF-8
		
		// 주소줄에 값을 넘김 주소 이름=값
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		//POST방식
		//토큰 발급  => code, client_id, client_secret, redirect_uri
		//            grant_type
		MultiValueMap<String, String> parameters
		=new LinkedMultiValueMap<String, String>();
		parameters.add("code", requestTokenDTO.getCode());
//		parameters.add("client_id", "Client ID 회원가입");
//		parameters.add("client_secret", "Client secret 회원가입");
		parameters.add("client_id", "ff7477bf-9ff5-46c1-9f42-e5078d3b1c8c");
		parameters.add("client_secret", "f91010ef-e5c8-45a8-af95-48b4c4ab713d");
		parameters.add("redirect_uri", "http://localhost:8080/fintech/callback");
		parameters.add("grant_type", "authorization_code");
		
		// header, parameters
		HttpEntity<MultiValueMap<String, String>> param=
				new HttpEntity<MultiValueMap<String,String>>(parameters,httpHeaders);
		
		
		// REST 방식 API 요청 리턴받은 값
		RestTemplate restTemplate=new RestTemplate();
		String apiurl="https://testapi.openbanking.or.kr/oauth/2.0/token";
		return restTemplate.exchange(apiurl, HttpMethod.POST, param, ResponseTokenDTO.class).getBody();
	}
	
	
	// 사용자 정보 조회
	public UserInfoResponseDTO findUser(UserInfoRequestDTO userInfoRequestDTO) {
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add("Authorization", "Bearer"+userInfoRequestDTO.getAccess_token());
		
		HttpEntity<String> param = new HttpEntity<String>(httpHeaders);
		
		//get 방식일때 파라미터값 가지고 가는 방식
		String apiurl = "https://testapi.openbanking.or.kr/v2.0/user/me";
		UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(apiurl)
				.queryParam("user_seq_no", userInfoRequestDTO.getUser_seq_no())
				.build();
		
		// REST 방식 API 요청 리턴받은 값
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(uriBuilder.toString(), HttpMethod.GET, param, UserInfoResponseDTO.class).getBody();
	}

	// 등록계좌조회
	public AccountResponseDTO findAccount(AccountRequestDTO accountRequestDTO) {
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add("Authorization", "Bearer"+accountRequestDTO.getAccess_token());
		
		HttpEntity<String> param = new HttpEntity<String>(httpHeaders);
		
		//get 방식일때 파라미터값 가지고 가는 방식
		String apiurl="https://testapi.openbanking.or.kr/v2.0/account/list";
		UriComponents uriBuilder=UriComponentsBuilder.fromHttpUrl(apiurl)
				.queryParam("user_seq_no", accountRequestDTO.getUser_seq_no())
				.queryParam("include_cancel_yn", accountRequestDTO.getInclude_cancel_yn())
				.queryParam("sort_order", accountRequestDTO.getSort_order())
				.build();
		
		// REST 방식 API 요청 리턴받은 값
		RestTemplate restTemplate=new RestTemplate();
		return restTemplate.exchange(uriBuilder.toString(), HttpMethod.GET, param, AccountResponseDTO.class).getBody();
	}
		
		
	
}
