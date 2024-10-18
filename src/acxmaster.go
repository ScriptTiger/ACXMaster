package main

import (
	"os"
	"os/exec"
	"path/filepath"
)

func main() {

	// Locate base directory of launcher and establish path to java
	dir, err := os.Executable()
	if err != nil {os.Exit(1)}
	dir, err = filepath.EvalSymlinks(dir)
	if err != nil {os.Exit(2)}
	dir = filepath.Join(filepath.Dir(dir), "bin")

	// Launch app
	command := exec.Command(filepath.Join(dir, javaName), "-m", "acxmaster/acxmaster.Main")
	command.Dir = dir
	command.Start()

	// Exit launcher
	os.Exit(0)
}