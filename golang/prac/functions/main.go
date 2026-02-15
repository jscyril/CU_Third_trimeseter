package main

import "fmt"

func factorial(n int) int {
	if n == 0 {
		return 1
	}
	fmt.Println("Hello Hehehehehe")
	defer fmt.Println("Hi hehehehehehehe")

	return n * factorial(n-1)
}

func sum(x ...int) {
	var sum_val int
	for i := range x {
		sum_val += x[i]
	}
	fmt.Println(x, sum_val)
}

func main() {

	// x := func(x int) int {
	// 	defer fmt.Println("Is this the end of a function or the main function")
	// 	return x * x
	// }
	// fmt.Println("Hello")
	// b := x(5)
	// fmt.Println(b)
	// fact := factorial(5)
	// fmt.Println(fact)
	(sum(1, 2, 3, 4, 5))

}
