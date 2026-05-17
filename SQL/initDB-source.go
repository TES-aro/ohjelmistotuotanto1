package main

import (
	"bufio"
	"database/sql"
	"fmt"
	"os"
	"path/filepath"
	"strings"

	_ "github.com/go-sql-driver/mysql"
)

var db *sql.DB

func main(){
	connStr := "root:salasana12@tcp(localhost:3306)/"
	newdb, err := sql.Open("mysql", connStr)
	db = newdb
	if err != nil {
		fmt.Println("couldn't access mySQL db")
		return
	}
	defer db.Close()
	err = db.Ping()
	if err != nil {
		fmt.Println("failed to ping")
		return
	}

	ex, err := os.Executable()
	if err != nil {
		fmt.Println(err) 
	}
	exPath := filepath.Dir(ex)
	initFile := exPath + "/init.sql"
	readFile(initFile,true)

	fmt.Println("all good")
}

func exec(query string) (sql.Result){
	result, err := db.Exec(query)
	if err != nil {
		fmt.Println(err)
		db.Close()
		os.Exit(1)
	}
	return result
}

func readFile(path string, verbose bool) {
	file, err := os.Open(path)
	if err != nil {
		fmt.Println("couldn't open file")
		return
	}
	defer file.Close()
	scanner := bufio.NewScanner(file)
	var query string
	for scanner.Scan(){
		line := scanner.Text()
		if len(line) > 4 {
			first := line[:4]
			if strings.Contains(first, "#END"){
				fmt.Println(line)
				return
			}
			if strings.Contains(first, "#"){
				fmt.Print("comment: ")
				fmt.Println(line)
				continue
			}
		}
		if line == ""{
			continue
		}
		query = query+"\n"+line
		if strings.Contains(line, ";") {
			exec(query)
			if verbose {
				fmt.Println(query)
				rows , err := db.Query(query)
				if err != nil {
					fmt.Println(err)
					return
				}
				defer rows.Close()
				for rows.Next(){
					var str string;
					rows.Scan(&str)
					fmt.Println(str)
				}
			}
			query = ""
		}
	}
}
