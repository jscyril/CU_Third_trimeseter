package main

import (
	"fmt"
	"slices"
)

func main() {
	var village_years []int = []int{1900, 1947, 2000, 2012, 2023, 2024, 2100, 1985, 1975}
	var item_names []string = []string{"naman", "12321", "malayalam", "mela", "101", "isro"}

	//Task 1:
	var shubh_years []int
	for _, year := range village_years {
		if year%4 == 0 {
			if year%100 != 0 || year%400 == 0 {
				shubh_years = append(shubh_years, year)
			}
		}
	}
	//Task 2:
	var anokhi_registry map[string]int = make(map[string]int)

	for _, gift := range item_names {
		var bool_val_item = 1

		for i, j := 0, len(gift)-1; i < j; i, j = i+1, j-1 {
			if gift[i] != gift[j] {
				bool_val_item = 0
			}
		}
		if bool_val_item == 1 {
			anokhi_registry[gift] = len(gift)
		}
	}

	//Task 3:
	var ghat map[string][]int = make(map[string][]int)

	for _, year := range shubh_years {
		if year%3 == 0 {
			ghat["Gate-0"] = append(ghat["Gate-0"], year)
		} else if year%3 == 1 {
			ghat["Gate-1"] = append(ghat["Gate-1"], year)
		} else if year%3 == 2 {
			ghat["Gate-2"] = append(ghat["Gate-2"], year)
		}
	}

	//Task 4:
	var unbalanced []int
	for _, year := range village_years {
		if year%2 != 0 {
			unbalanced = append(unbalanced, year)
		}
	}
	slices.Sort(unbalanced)
	var inv_slice []int = unbalanced[:2]

	//Task 5:
	fmt.Println("Shubh years : ", shubh_years)
	fmt.Println("Anokhi Map: ", anokhi_registry)
	fmt.Println("Ghat Allocation Map: ", ghat)
	fmt.Println("Invitation Slice: ", inv_slice)
}
