package com.itwillbs.fintech.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.fintech.domain.AccountRequestDTO;
import com.itwillbs.fintech.domain.AccountResponseDTO;
import com.itwillbs.fintech.domain.RequestTokenDTO;
import com.itwillbs.fintech.domain.ResponseTokenDTO;
import com.itwillbs.fintech.domain.UserInfoRequestDTO;
import com.itwillbs.fintech.domain.UserInfoResponseDTO;
import com.itwillbs.fintech.service.OpenBankingService;

@Controller
public class OpenBankingController {
	//객체생성
	@Inject
	private OpenBankingService openBankingService;

	@RequestMapping(value = "/callback",method = RequestMethod.GET)
	public String getToken(RequestTokenDTO requestTokenDTO, Model model) throws Exception {
		//인증 결과
//		응답 메시지 URL HTTP URL <callback_uri> [Callback URL 로 redirect]
//		HTTP Method GET
		// 인증 응답 메시지 명세
		System.out.println("code :" +requestTokenDTO.getCode());
		System.out.println("scope :" +requestTokenDTO.getScope());
		System.out.println("client_info :" +requestTokenDTO.getClient_info());
		System.out.println("state :" +requestTokenDTO.getState());

		//토큰 발급  => code, client_id, client_secret, redirect_uri
		//            grant_type
		// 토큰 발급 처리 
		ResponseTokenDTO responseTokenDTO=
				openBankingService.requestToken(requestTokenDTO);
		
		//리턴 정보를 저장
		model.addAttribute("responseTokenDTO", responseTokenDTO);
		// bank_main.jsp 이동
		return "bank_main";
	}
	
	@RequestMapping(value = "/userInfo",method = RequestMethod.GET)
	public String userInfo(UserInfoRequestDTO userInfoRequestDTO, Model model) {
		UserInfoResponseDTO userInfoResponseDTO=
				openBankingService.findUser(userInfoRequestDTO);
		
		//리턴 정보를 저장
		model.addAttribute("userInfoResponseDTO", userInfoResponseDTO);
		model.addAttribute("access_token", userInfoRequestDTO.getAccess_token());
		
		// user_info.jsp 이동
		return "user_info";
	}
	
	@RequestMapping(value = "/accountList",method = RequestMethod.GET)
	public String accountList(AccountRequestDTO accountRequestDTO, Model model) {
		AccountResponseDTO accountResponseDTO=
				openBankingService.findAccount(accountRequestDTO);
		
		//리턴 정보를 저장
		model.addAttribute("accountResponseDTO", accountResponseDTO);
		model.addAttribute("access_token", accountRequestDTO.getAccess_token());
		
		// user_info.jsp 이동
		return "list";
	}
	
	
}
