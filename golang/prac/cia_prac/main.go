package main

import (
	"fmt"
	"slices"
)

func main() {
	var cargoIds []int = []int{50, 42, 100, 12, 18, 55, 60, 24, 5}
	var containerTags []string = []string{"level", "gamma", "area", "trust", "radar", "sense"}
	var expressCargo []int
	var secureContainers map[string]int = make(map[string]int)
	var bays map[string][]int = make(map[string][]int)
	var heavyCargo []int
	var topTwoHeavyCargo []int
	for _, i := range cargoIds {
		if i%2 == 0 && i%10 != 0 {
			expressCargo = append(expressCargo, i)
		}
	}
	for _, str := range containerTags {
		var startIdx int = 0
		var endIdx int = len(str) - 1
		if str[startIdx] == str[endIdx] {
			secureContainers[str] = len(str)
		}
	}
	for _, val := range expressCargo {
		if val > 20 {
			bays["Bay A"] = append(bays["Bay A"], val)
		} else {
			bays["Bay B"] = append(bays["Bay B"], val)
		}
	}
	for _, val := range cargoIds {
		if val > 40 {
			heavyCargo = append(heavyCargo, val)
		}
	}
	slices.Sort(heavyCargo)
	topTwoHeavyCargo = heavyCargo[0:2]
	fmt.Println("Express Cargo: ", expressCargo)
	fmt.Println("Secure Containers: ", secureContainers)
	fmt.Println("Heavy Cargo:", heavyCargo)
	fmt.Println("Bays: ", bays)
	fmt.Println("Top 2 Heavy cargo: ", topTwoHeavyCargo)
}
