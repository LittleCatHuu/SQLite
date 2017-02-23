package com.example.demo;

public class Student {
	int stuno;
	String name; 
	int age;
	public Student(int stuno, String name, int age) {
		this.stuno =stuno;
		this.name = name;
		this.age = age;
	}
	public String getInfo() {
		return stuno +"   "+  name + "   "+age;
	}

}
