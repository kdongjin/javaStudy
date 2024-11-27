package com.kh.subjectMVCProject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.StudentVO;
import com.kh.subjectMVCProject.model.TraineeVO;


public class TraineeRegisterManager {
	public static Scanner sc = new Scanner(System.in); 
	//전체 리스트를 출력요청(조인)
	public static void totalSelectManager()  {
		TraineeDAO tdao = new TraineeDAO(); 
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();
		
		traineeList = tdao.traineeAllSelect(new TraineeVO()); 
		if(traineeList.size() <= 0 ) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printTraineeAllList(traineeList); 
	}
	
	public static void selectManager()  {
		TraineeDAO tdao = new TraineeDAO(); 
		ArrayList<TraineeVO> traineeList = new ArrayList<TraineeVO>();
		traineeList = tdao.traineeSelect(new TraineeVO()); 
		
		if(traineeList.size() <= 0) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printTraineeList(traineeList); 
	}

	public static void insertManager() {
		TraineeDAO tdao = new TraineeDAO(); 
		// 3.statement
		//student 검색기능추가
		//검색된 이름으로 학번, 이름, 이메일 출력 통해서 학번입력처리
		StudentRegisterManager srm = new StudentRegisterManager(); 
		srm.selectNameSearchManager();
		System.out.print("학생번호등록>>");
		String s_num = sc.nextLine();
		
		//Lesson abbreviation 보여준다. 과목명요약, 과목명
		LessonRegisterManager lrm = new LessonRegisterManager();
		lrm.selectSortManager();
		System.out.print("과목요약입력>>");
		String abbre = (sc.nextLine()).trim();
		
		System.out.print("전공,부전공,교양입력>> ");
		String section = (sc.nextLine()).trim();
		
		TraineeVO traineeVO =  new TraineeVO(0, s_num, abbre, section, null);
		boolean successFlag = tdao.traineeInsert(traineeVO);
		
		if(successFlag == true) {
			System.out.println("입력처리 성공");
		}else {
			System.out.println("입력처리 실패");
		}
	}

	public static void updateManager()  {
		TraineeDAO tdao = new TraineeDAO(); 
		
		//Trainee 테이블 전체내용을 보여준다. 
		selectManager();
		// 3.statement
		//student 검색기능추가
		System.out.print("수정할 번호 입력하세요>>");
		int no = Integer.parseInt(sc.nextLine());
		//검색된 이름으로 학번, 이름, 이메일 출력 통해서 학번입력처리
		StudentRegisterManager srm = new StudentRegisterManager(); 
		srm.selectNameSearchManager();
		System.out.print("학생번호등록>>");
		String s_num = sc.nextLine();
		
		//Lesson abbreviation 보여준다. 과목명요약, 과목명
		LessonRegisterManager lrm = new LessonRegisterManager();
		lrm.selectSortManager();
		System.out.print("과목요약입력>>");
		String abbre = (sc.nextLine()).trim();
		
		System.out.print("전공,부전공,교양입력>> ");
		String section = (sc.nextLine()).trim();
		
		TraineeVO traineeVO =  new TraineeVO(no, s_num, abbre, section, null);
		boolean successFlag = tdao.traineeUpdate(traineeVO);
		
		if(successFlag == true) {
			System.out.println("수정처리 성공");
		}else {
			System.out.println("수정처리 실패");
		}
	}

	public static void deleteManager() {
		TraineeVO tvo = new TraineeVO();
		TraineeDAO tdao = new TraineeDAO(); 
		
		System.out.print("삭제할 수강신청 번호를 입력하세요>>");
		int no = Integer.parseInt(sc.nextLine());
		tvo.setNo(no);
		boolean successFlag = tdao.traineeDelete(tvo);  
		
		if(successFlag == true) {
			System.out.println("삭제처리 성공");
		}else {
			System.out.println("삭제처리 실패");
		}
	}

	public static void sortManager() throws SQLException {
		TraineeDAO tdao = new TraineeDAO(); 
		ArrayList<TraineeVO> traineeList = null; 
		traineeList = tdao.traineeSelectSort(new TraineeVO());  
		if(traineeList.size() <= 0) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printTraineeList(traineeList);
	}

	//전체 학생리스트를 출력진행
	public static void printTraineeList(ArrayList<TraineeVO> traineeList) {
		System.out.println("============================================");
		for( TraineeVO tvo :  traineeList) {
			System.out.println(tvo.toString());
		}
		System.out.println("============================================");
	}
	//전체 학생리스트를 조인기능을 포함해서 출력진행
	public static void printTraineeAllList(ArrayList<TraineeVO> traineeList) {
		System.out.println("============================================");
		for( TraineeVO tvo :  traineeList) {
			System.out.println(tvo.toAllString());
		}
		System.out.println("============================================");
	}
}






