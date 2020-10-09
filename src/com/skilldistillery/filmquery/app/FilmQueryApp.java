package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    app.test();
    app.launch();
  }

  private void test() {
    Film film = db.findFilmById(1);
    System.out.println(film);
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	boolean run = true;
	while (run) {
    System.out.println("What would you like to do?");
    System.out.println("1: Look up film by ID");
    System.out.println("2: Look up film by keywords");
    System.out.println("3: Exit");
    int selection;
	try {
		selection = input.nextInt();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		input.next();
		selection = 0;
	}
    switch(selection) {
    case 1:
    	System.out.println("What is the id?");
    	int id = input.nextInt();
    	Film film = db.findFilmById(id);
    	System.out.println(film);
    	break;
    case 2:
    	break;
    case 3:
    	run = false;
    	break;
    default:
    	System.out.println("Make a proper selection. Thanks.");
    }
	}
  }

}
