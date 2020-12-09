package com.lagou.func1;

import com.lagou.test.User;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 编程实现学生信息管理系统的测试
 */
public class MainTest {

	public static void main(String[] args) throws AgeException {
		// 创建ObjectOutputStream和ObjectInputStream类型的对象与d
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		// 1.创建一个集合用于存储所有学生信息

		List<Student> studentList = null;

		try {		//读取相应的文件信息
			// 1.创建ObjectInputStream类型的对象与d:/a.txt文件关联
			ois = new ObjectInputStream(new FileInputStream("a.txt"));
			// 2.从输入流中读取一个对象并打印
			 studentList = (List<Student>) ois.readObject();
			System.out.println("读取到的对象是：" + studentList); // qidian 123456 13511258688  null
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 3.关闭流对象并释放有关的资源
			if (null != ois) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}



		// 2.通过集合进行学生信息的增删改查操作
		ManageStudent manageStudent = new ManageStudent(studentList);
		ViewStudent viewStudent = new ViewStudent(manageStudent);
		viewStudent.showMenu();

		// 3.关闭扫描器
		ScannerStudent.closeScanner();
		try {
			// 1.创建ObjectOutputStream类型的对象与d:/a.txt文件关联
			oos = new ObjectOutputStream(new FileOutputStream("a.txt"));

			oos.writeObject(studentList);
			System.out.println("写入对象成功！");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 4.关闭流对象并释放有关的资源
			if (null != oos) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
