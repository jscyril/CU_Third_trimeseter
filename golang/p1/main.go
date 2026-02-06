package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	scanner.Scan()
	name := scanner.Text()
	fmt.Printf("Hello %s!\n", name)
	scanner.Scan()
	n := scanner.Text()
	num, _ := strconv.Atoi(n)
	for i := 0; i < num; i++ {
		fmt.Printf("Enter number %d: ", i)

	}
	fmt.Println()
}
