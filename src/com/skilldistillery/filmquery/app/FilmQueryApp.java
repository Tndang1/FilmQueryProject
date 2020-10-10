package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
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
		Film film = null;
		int id = 0;
		String search = null;
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
			switch (selection) {
			case 1:
				System.out.println("What is the id?");
				try {
					id = input.nextInt();
				} catch (Exception e) {
					input.next();
					id = 0;
					System.out.println("No.");
				}
				film = db.findFilmById(id);
				break;
			case 2:
				System.out.println("What would you like to search for?");
				search = input.next();
				film = db.findFilmBySearch(search);
				break;
			case 3:
				System.out.println("Get outta here!");
				run = false;
				break;
			default:
				System.out.println("Make a proper selection. Thanks.");
			}
			if (film != null) {
				System.out.println(film);
				System.out.println("Featuring");
				System.out.println("---------");
				List<Actor> actors = db.findActorsByFilmId(film.getId());
				for (Actor actor : actors) {
					System.out.println(actor.getFullName());
				}
				System.out.println();
			} else {
				if (search == null) {
				System.out.println("Sorry, film " + id + " was not found.");
				} else {
					System.out.println("Sorry, no results found for " + search);
				}
			}
		}
	}

}
