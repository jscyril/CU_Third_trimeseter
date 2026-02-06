package main

import (
	"fmt"
	"strings"
)

type demo struct {
	data int
	next int
}

func main() {
	var x string = "Jacob Cyril"
	fmt.Println(x)
	var xr []rune = []rune(x)
	xr[0] = 'Y'
	fmt.Println(string(xr))
	fmt.Println(strings.ToUpper(x))
	fmt.Println(strings.ToLower(x))
	fmt.Println(strings.Contains(x, "Cyril"))
	fmt.Println(strings.ReplaceAll(x, "Cyril", "Xavier"))
	fmt.Println(strings.Split(x, " "))
	fmt.Println(len(x))
	var y string = "Stressed"
	var yr []rune = []rune(y)
	for i, j := 0, len(yr)-1; i < j; i, j = i+1, j-1 {
		yr[i], yr[j] = yr[j], yr[i]
	}
	fmt.Println(string(yr))
	var d strings.Builder
	for i := 1; i <= 5; i++ {
		d.WriteString("Line ")
		d.WriteString(fmt.Sprintf("%d", i))
		d.WriteString("\n")
	}
	fmt.Println(d.String())
	var s1 string
	var s2 string
	fmt.Println("Enter first string:")
	fmt.Scanln(&s1)
	fmt.Println("Enter second string:")
	fmt.Scanln(&s2)
	fmt.Println("Concatenated String:", s1+s2)
	fmt.Println("Contains:", strings.Contains(s1, s2))
	fmt.Println("Equalfold:", strings.EqualFold(s1, s2))
	fmt.Println("Index of second string in first string:", strings.Index(s1, s2))
	fmt.Println("Length of first string:", len(s1))
	fmt.Println("Replaced String:", strings.ReplaceAll(s1, s2, "REPLACED"))
	fmt.Println("Split first string by second string:", strings.Split(s1, s2))
	var demo1 demo = demo{data: 10, next: 20}
	fmt.Println("Demo Struct:", demo1)
	demo1.data = 5
	demo1.next = 10
	fmt.Println("Updated Demo Struct:", demo1)
	var num1 int
	fmt.Println("Enter a number:")
	fmt.Scan(&num1)
	fmt.Println(num1)
	fmt.Printf("%v", demo1)

}
