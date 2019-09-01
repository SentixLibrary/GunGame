package com.javabeatiful.project.print;

public class PrintAPI {
	
	// Colors named ANSI_***Color***
	
	public final String ANSI_RESET = "\u001B[0m"; 
	public final String ANSI_BLACK = "\u001B[30m"; 
	public final String ANSI_RED = "\u001B[31m"; 
	public final String ANSI_GREEN = "\u001B[32m";
	 public final String ANSI_YELLOW = "\u001B[33m"; 
	 public final String ANSI_BLUE = "\u001B[34m"; 
	 public final String ANSI_PURPLE = "\u001B[35m"; 
	 public final String ANSI_CYAN = "\u001B[36m"; 
	 public final String ANSI_WHITE = "\u001B[37m";
	
	public static final String BLUE_BACKROUND = "\u001B[44m";
	
	public void debug(String inputString){
		System.out.println(ANSI_YELLOW + "DEBUG | " + inputString + ANSI_RESET);
	}
	
}