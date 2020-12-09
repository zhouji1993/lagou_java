package com.lagou.func1;

import java.util.Iterator;
import java.util.List;

/**
 * 编程实现学生信息管理系统中逻辑处理层的功能
 */
public class ManageStudent {
	/**
	 * 定义一个集合来存储学生信息
	 */
	private List<Student> studentList;

	/**
	 * 通过构造方法实现成员变量的初始化
	 * @param studentList
	 */
	public ManageStudent(List<Student> studentList) {
		this.studentList = studentList;
	}

	/**
	 * 增加学生信息到集合中
	 */
	public void addStudent() throws AgeException {
		// 1.提示用户输入要增加的学生信息并使用变量记录
		System.out.println("请输入要增加的学生信息(学号,姓名,年龄)：");
		String strStudent = ScannerStudent.getScanner().next();

		// 2.按照逗号进行字符串拆分，使用字符串数组进行记录
		String[] strings = strStudent.split(",");

		// 3.根据学号判断输入的学生信息是否存在，如果存在则放弃加入
		int id = Integer.parseInt(strings[0]);
		for(Student ts : studentList) {
			if(id == ts.getId()) {
				System.out.println("该学生已经存在，增加失败");
				return;
			}
		}

		// 4.构造Student对象并放入集合中
		Student student = new Student(id, strings[1], Integer.parseInt(strings[2]));
		studentList.add(student);
		System.out.println("增加学生信息成功");
	}

	/**
	 * 从集合中删除学生信息
	 */
	public void deleteStudent() {
		// 1.提示用户输入要删除的学生学号并使用变量记录
		System.out.println("请输入要删除的学生学号：");
		int id = ScannerStudent.getScanner().nextInt();

		// 2.查找该学生信息是否存在，如果存在则删除成功
		Iterator<Student> iterator = studentList.iterator();
		while (iterator.hasNext()) {
			Student next = iterator.next();
			if (id == next.getId()) {
				iterator.remove();
				System.out.println("删除成功，被删除的学生信息是：" + next);
				return;
			}
		}
		// 否则删除失败
		System.out.println("该学生不存在，删除失败！");
	}

	/**
	 * 修改学生信息从集合中
	 */
	public void modifyStudent() throws AgeException {
		// 1.提示用户输入要修改的学生学号并使用变量记录
		System.out.println("请输入要修改的学生学号：");
		int id = ScannerStudent.getScanner().nextInt();

		// 2.使用for循环去查找学生信息并给出下一步提示信息
		for (int i = 0; i < studentList.size(); i++) {
			if(id == studentList.get(i).getId()){
				// 修改成功的处理方式
				System.out.println("请输入修改后的信息（姓名 年龄）：");
				String name = ScannerStudent.getScanner().next();
				int age = ScannerStudent.getScanner().nextInt();
				Student student = new Student(id, name, age);
				studentList.set(i, student);
				System.out.println("修改成功，修改后的学生信息是：" + student);
				return;
			}
		}
		// 修改失败的处理方式
		System.out.println("修改失败，该学生不存在！");
	}

	/**
	 * 查找学生信息从集合中
	 */
	public void queryStudent() {
		// 1.提示用户输入要查找的学生信息并使用变量记录
		System.out.println("请输入要查找的学生学号：");
		int id = ScannerStudent.getScanner().nextInt();

		// 2.使用增强版for循环去查找学生信息并给出提示信息
		for(Student s : studentList) {
			if(id == s.getId()){
				// 查找成功的处理方式
				System.out.println("查找成功，查找到的学生信息是：" + s);
				return;
			}
		}
		// 查找失败的处理方式
		System.out.println("查找失败，该学生不存在！");
	}

	/**
	 * 显示所有学生信息
	 */
	public void printStudent() {
		System.out.println("---------------------------------------------");
		System.out.println("目前所有的学生信息是：");
		for(Student s : studentList){
			System.out.println(s);
		}
		System.out.println("---------------------------------------------");
	}
}
