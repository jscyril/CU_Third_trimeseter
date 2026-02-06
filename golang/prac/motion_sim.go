package main

import (
	"bufio"
	"fmt"
)

func main() {
	//var x [7]int = [7]int{1, 2, 3, 4, 5, 6, 7}
	var x []int
	fmt.Println("Enter number of values in array")
	var n int
	fmt.Scanf("%d", &n)

	fmt.Println("Enter values for the array")
	for i, element := range x {
		for j, element2 := range x {
			if element == element2 && i != j {
				fmt.Println("Found duplicate:", element, "at indices", i)
			}
		}
	}

}
