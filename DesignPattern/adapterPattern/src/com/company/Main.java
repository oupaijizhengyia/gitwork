package com.company;

public class Main {

    public static void main(String[] args) {
	    Employee employee = new Employee("yy","我一");
	    Target target = (Target)FileUtil.getBean();
	    System.out.println(target.encrypt(employee));
    }
}
