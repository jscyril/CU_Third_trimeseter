package main

import "fmt"

func main() {
	type Point struct {
		X, Y int
	}

	locations := map[Point]string{
		{0, 0}:  "origin",
		{1, 2}:  "point A",
		{-1, 3}: "point B",
	}
	fmt.Println("Struct keys:", locations)
}
