package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	type Student struct {
		RollNo   string
		Name     string
		Semester int
		Courses  map[string]int
	}

	students := make(map[string]Student)
	reader := bufio.NewReader(os.Stdin)

	var n int
	fmt.Print("Enter number of students: ")
	fmt.Scan(&n)
	reader.ReadString('\n')

	for i := 0; i < n; i++ {
		var s Student
		s.Courses = make(map[string]int)

		fmt.Println("\n Student", i+1)

		fmt.Print("Enter Roll Number: ")
		s.RollNo, _ = reader.ReadString('\n')
		s.RollNo = strings.TrimSpace(s.RollNo)

		fmt.Print("Enter Name: ")
		s.Name, _ = reader.ReadString('\n')
		s.Name = strings.TrimSpace(s.Name)

		fmt.Print("Enter Semester: ")
		fmt.Scan(&s.Semester)
		reader.ReadString('\n')

		var k int
		fmt.Print("Enter number of courses: ")
		fmt.Scan(&k)
		reader.ReadString('\n')

		for j := 0; j < k; j++ {
			var code string
			var marks int

			fmt.Print("  Course code: ")
			code, _ = reader.ReadString('\n')
			code = strings.TrimSpace(code)

			fmt.Print("  Marks: ")
			fmt.Scan(&marks)
			reader.ReadString('\n')

			s.Courses[code] = marks
		}

		students[s.RollNo] = s
	}

	var highestAvg float64 = -1
	var topRollNo string
	var failedCount int = 0

	fmt.Println("\n         STUDENT DETAILS")

	for rollNo, student := range students {
		fmt.Println("\nRoll No:", student.RollNo)
		fmt.Println("Name:", student.Name)
		fmt.Println("Semester:", student.Semester)
		fmt.Println("Courses:")

		var total int = 0
		var passed bool = true

		for code, marks := range student.Courses {
			fmt.Println("  ", code, ":", marks)
			total = total + marks
			if marks < 40 {
				passed = false
			}
		}

		var avg float64 = 0
		var numCourses int = len(student.Courses)
		if numCourses > 0 {
			avg = float64(total) / float64(numCourses)
		}

		fmt.Println("Total Marks:", total)
		fmt.Printf("Average Marks: %.2f\n", avg)

		if passed {
			fmt.Println("Status: PASS")
		} else {
			fmt.Println("Status: FAIL")
			failedCount = failedCount + 1
		}

		if avg > highestAvg {
			highestAvg = avg
			topRollNo = rollNo
		}
	}

	fmt.Println("\n            SUMMARY")
	fmt.Println("Highest Average Roll No:", topRollNo)
	fmt.Printf("Highest Average: %.2f\n", highestAvg)
	fmt.Println("Total Failed Students:", failedCount)
}
