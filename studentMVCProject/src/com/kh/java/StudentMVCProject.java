package com.kh.java;

import java.sql.SQLException;
import java.util.Scanner;
import com.kh.java.controller.StudentRegisterManager;
import com.kh.java.view.StudentCURDMenu;
import com.kh.java.view.StudentMenu;

public class StudentMVCProject {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		boolean exitFlag = false;
		while (!exitFlag) {
			StudentMenu.printMenu();
			int num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case StudentCURDMenu.PRINT: {
				StudentRegisterManager.totalSelectManager();
				break;
			}
			case StudentCURDMenu.INSERT: {
				StudentRegisterManager.insertManager();
				break;
			}
			case StudentCURDMenu.UPDATE: {
				StudentRegisterManager.updateManager();
				break;
			}
			case StudentCURDMenu.DELETE: {
				StudentRegisterManager.deleteManager();
				break;
			}
			case StudentCURDMenu.SORT: {
				StudentRegisterManager.sortManager();
				break;
			}
			case StudentCURDMenu.EXIT: {
				exitFlag = true;
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + num);
			}
			System.out.println("The end");
		}

	}
}