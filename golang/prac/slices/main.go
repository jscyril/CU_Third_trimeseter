package main

import (
	"fmt"
)

func main() {
	type Student struct {
		RollNo   string
		Name     string
		Semester int
		Courses  map[string]int
	}

	students := make(map[string]Student)
	var n int

	fmt.Print("Enter the number of students: ")
	fmt.Scanln(&n)

	for i := 0; i < n; i++ {
		var rollNo, name string
		var semester int

		fmt.Printf("\nEnter details for student %d:\n", i+1)
		fmt.Print("Roll Number: ")
		fmt.Scanln(&rollNo)
		fmt.Print("Name: ")
		fmt.Scanln(&name)
		fmt.Print("Semester: ")
		fmt.Scanln(&semester)

		courses := make(map[string]int)
		var k int
		fmt.Print("Enter the number of courses: ")
		fmt.Scanln(&k)

		for j := 0; j < k; j++ {
			var courseCode string
			var marks int
			fmt.Printf("Enter course code and marks for course %d: ", j+1)
			fmt.Scanln(&courseCode, &marks)
			courses[courseCode] = marks
		}

		students[rollNo] = Student{
			RollNo:   rollNo,
			Name:     name,
			Semester: semester,
			Courses:  courses,
		}
	}

	var highestAvg float64
	var topStudentRollNo string
	failedStudents := 0

	fmt.Println("\n--- Student Details ---")
	for rollNo, student := range students {
		totalMarks := 0
		pass := true
		for _, marks := range student.Courses {
			totalMarks += marks
			if marks < 40 {
				pass = false
			}
		}

		var average float64
		if len(student.Courses) > 0 {
			average = float64(totalMarks) / float64(len(student.Courses))
		}

		fmt.Printf("\nRoll Number: %s\n", student.RollNo)
		fmt.Printf("Name: %s\n", student.Name)
		fmt.Printf("Semester: %d\n", student.Semester)
		fmt.Println("Courses and Marks:")
		for code, mark := range student.Courses {
			fmt.Printf("  %s: %d\n", code, mark)
		}
		fmt.Printf("Total Marks: %d\n", totalMarks)
		fmt.Printf("Average Marks: %.2f\n", average)
		if pass {
			fmt.Println("Status: Pass")
		} else {
			fmt.Println("Status: Fail")
			failedStudents++
		}

		if average > highestAvg {
			highestAvg = average
			topStudentRollNo = rollNo
		}
	}

	fmt.Println("\n--- Summary ---")
	if topStudentRollNo != "" {
		fmt.Printf("Student with the highest average: %s (%.2f)\n", topStudentRollNo, highestAvg)
	} else {
		fmt.Println("No students to determine the highest average.")
	}
	fmt.Printf("Total number of failed students: %d\n", failedStudents)
}