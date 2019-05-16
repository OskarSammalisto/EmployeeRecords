package com.example.EmployeeRecords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

@SpringBootApplication
public class EmployeeRecordsApplication {


	public static void main(String[] args) {
		SpringApplication.run(EmployeeRecordsApplication.class, args);

//		try (PrintWriter employeeRecord = new PrintWriter("employeeRecord.txt")) {
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		try (PrintWriter employerRecord = new PrintWriter("employerRecord.txt")) {
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}

	}

}
