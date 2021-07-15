﻿package com.diary.mydiary.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diary.mydiary.domain.DUserVO;
import com.diary.mydiary.service.DUserService;

// user로 시작하는 요청 처리 컨트롤러

@Controller
@RequestMapping("user/*")
public class DUserController {

	@Autowired
	private DUserService service;

	// 1. 로그인 페이지로 이동하는 요청 처리
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void goLogin() {
		// 로그인 페이지로 단순 이동
	}

	// 2. 로그인 요청 처리
	// 사용자가 입력한 id와 pw를 가져와서 일치한 데이터가 있으면 !null, 없으면 null
	// 세션 처리 -> 수정 인터셉터로
	@RequestMapping(value = "loginPost", method = RequestMethod.POST)
	// public String login(DUserVO vo, HttpSession session, RedirectAttributes
	// attr)
	public void login(DUserVO vo, Model model, RedirectAttributes attr) {
		// 1) 서비스 로그인 함수 실행
		DUserVO login = service.login(vo);

		// 2) model에 login 값 저장 (로그인 성공시 데이터, 실패시 null)
		model.addAttribute("login", login);
		// attr.addFlashAttribute("msg", "false");

		// 3) 사용하지 않는 페이지로 이동

		// return "redirect:login";
		// 인터셉터 적용을 위해 삭제
		/*
		 * // 2) 로그인 성공 여부 확인 if (login == null) { // 로그인 실패 // (1) session에 null 저장
		 * session.setAttribute("login", null); // (2) 로그인 실패 메세지 전달
		 * attr.addFlashAttribute("msg", "false"); // (3) 로그인 페이지로 재 이동 return
		 * "redirect:login";
		 * 
		 * } else { // 로그인 성공 // (1) 세션에 로그인 정보 저장 session.setAttribute("login", login);
		 * // (2) 이동할 페이지로 리다이렉트 -> 일단 메인으로.. return "redirect:/";
		 * 
		 * }
		 */
	}

	// 3. 로그아웃 처리 -> 세션 초기화 해야함
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// 1) 세션 초기화
		session.invalidate();
		// 2) 메인 페이지로 리다이렉트
		return "redirect:/";

	}

	// 4. 회원 가입 페이지로 이동 요청 처리
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public void goJoin() {

		// 단순 이동

	}

	// 5. 회원 가입 처리
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String join(DUserVO vo, RedirectAttributes attr) {

		int result = service.join(vo);
		System.out.println("Result for Join : " + result);
		attr.addFlashAttribute("msg", "true");
		return "redirect:login";

	}

	// 6. 프로필 수정
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String updateProfile(MultipartHttpServletRequest request) {

		// 1) 서비스 메소드 호출
		service.updateProfile(request);

		// 2) list 페이지로 리다이렉트
		return "redirect:/diary/list";
	}

	// 7. 회원 탈퇴
	@RequestMapping(value = "deleteUser", method = RequestMethod.GET)
	public String deleteUser(@RequestParam("id") String id, HttpSession session, RedirectAttributes attr) {
		// 1) 회원 탈퇴
		service.deleteUser(id);

		// 2) 세션 초기화
		session.invalidate();

		// 3) 리다이렉트 메세지
		attr.addFlashAttribute("msg", "정상적으로 회원 탈퇴 되셨습니다.");

		// 4) 메인화면으로 리다이렉트
		return "redirect:/";

	}

}