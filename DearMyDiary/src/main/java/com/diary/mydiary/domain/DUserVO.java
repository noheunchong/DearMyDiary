﻿package com.diary.mydiary.domain;

// DUser 테이블 데이터 저

public class DUserVO {

	// 1. 변수 선언
	private String id;
	private String pw;
	private String profile;
	private String nickname;
	private String intro;

	public DUserVO() {

	}

	public DUserVO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	// 2. get set
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {

		this.profile = profile;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	// 3. toString()
	@Override
	public String toString() {
		return "DUserVO [id=" + id + ", pw=" + pw + ", profile=" + profile + ", nickname=" + nickname + ", intro="
				+ intro + "]";
	}

}
