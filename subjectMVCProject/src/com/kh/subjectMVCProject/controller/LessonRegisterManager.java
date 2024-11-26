package com.kh.subjectMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.LessonVO;

public class LessonRegisterManager {
	public Scanner sc = new Scanner(System.in); 
	//과목등록(insert)
	public void insertManager() {
		LessonDAO ldao = new LessonDAO(); 
		//화면으로부터 입력받는다. 
		System.out.print("과목요약>>");
		System.out.print("K-국어  / M-수학  /  E-영어  / H-역사  / P-\r\n"
				+ "프로그래밍 / D-데이터베이스 / ED-교육학이론\r\n"
				+ "");
		int no = Integer.parseInt(sc.nextLine());
		
		LessonVO lvo = new LessonVO();
		lvo.setNo(no);
		boolean successFlag = ldao.lessonDelete(lvo); 
		
		//화면출력
		if(successFlag == true) {
			System.out.println(no +"번호를 삭제 하였습니다.");
		}else {
			System.out.println(no +"번호 삭제 실패하였습니다.");
		}
	} 	
	//과목목록(select)
	public void selectManager() {
		LessonDAO ldao = new LessonDAO(); 
		//화면으로부터 입력받는다.
		//데이타베이스 요청
		LessonVO lvo = new LessonVO();
		ArrayList<LessonVO> lessonList = ldao.lessonSelect(lvo); 
		//화면출력
		if(lessonList.size() != 0) {
			printLessonList(lessonList); 
		}else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	} 
	
	
	//과목삭제(delete)
	public void deleteManager() {
		LessonDAO ldao = new LessonDAO(); 
		//화면으로부터 입력받는다. 
		System.out.print("삭제할번호>>");
		int no = Integer.parseInt(sc.nextLine());
		
		LessonVO lvo = new LessonVO();
		lvo.setNo(no);
		boolean successFlag = ldao.lessonDelete(lvo); 
		
		//화면출력
		if(successFlag == true) {
			System.out.println(no +"번호를 삭제 하였습니다.");
		}else {
			System.out.println(no +"번호 삭제 실패하였습니다.");
		}
	} 
	//과목수정(update)
	
	//과목정렬(select)
	public void selectSortManager() {
		LessonDAO ldao = new LessonDAO(); 
		//화면으로부터 입력받는다.
		//데이타베이스 요청
		LessonVO lvo = new LessonVO();
		ArrayList<LessonVO> lessonList = ldao.lessonSelectSort(lvo); 
		//화면출력
		if(lessonList.size() != 0) {
			printLessonList(lessonList); 
		}else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	} 
	
	//화면출력
	public void printLessonList(ArrayList<LessonVO> lessonList) {
		for( LessonVO data : lessonList ) {
			System.out.println(data);
		}
	}

}





