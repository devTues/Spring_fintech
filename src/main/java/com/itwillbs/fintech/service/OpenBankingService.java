package com.itwillbs.fintech.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.fintech.domain.AccountRequestDTO;
import com.itwillbs.fintech.domain.AccountResponseDTO;
import com.itwillbs.fintech.domain.RequestTokenDTO;
import com.itwillbs.fintech.domain.ResponseTokenDTO;
import com.itwillbs.fintech.domain.UserInfoRequestDTO;
import com.itwillbs.fintech.domain.UserInfoResponseDTO;

@Service
public class OpenBankingService {
	//객체생성
	@Inject
	private OpenBankingApiClient openBankingApiClient;
	
	//토큰 발급 요청
	public ResponseTokenDTO requestToken(RequestTokenDTO requestTokenDTO) {
		return openBankingApiClient.requestToken(requestTokenDTO);
	}
	
	// 사용자 정보 조회
	public UserInfoResponseDTO findUser(UserInfoRequestDTO userInfoRequestDTO) {
		return openBankingApiClient.findUser(userInfoRequestDTO);
	}

	public AccountResponseDTO findAccount(AccountRequestDTO accountRequestDTO) {
		return openBankingApiClient.findAccount(accountRequestDTO);
	}

}
