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
//		app.test();
		app.launch();
	}

//	private void test() {
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//	}

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
			System.out.println("2: Look up film by keyword");
			System.out.println("3: Exit");
			String selection = input.next().toLowerCase();
			switch (selection) {
			case "1":
			case "id":
				searchById(input);
				break;
			case "2":
			case "keyword":
				searchByKeyword(input);
				break;
			case "3":
			case "exit":
				System.out.println("Get outta here!");
				run = false;
				break;
			default:
				System.out.println("Make a proper selection. Thanks.");
			}
		}
	}

	private void searchById(Scanner input) {
		Film film = null;
		int id = 0;
		System.out.println("What is the id?");
		try {
			id = input.nextInt();
			film = db.findFilmById(id);
			if (film != null) {
				System.out.print(film.shortToString());
				printActors(film);
				System.out.println();
				learnMore(input, film);
			} else {
				System.out.println("Sorry, film " + id + " was not found.");
			}
		} catch (Exception e) {
			input.next();
			id = 0;
			System.out.println("Please enter a number.");
			System.out.println();
		}
	}

	private void searchByKeyword(Scanner input) {
		String search = null;
		System.out.println("What would you like to search for?");
		search = input.next();
		List<Film> films = db.findFilmBySearch(search);
		if (films.size() == 0) {
			System.out.println("Sorry, no matches were found.");
			System.out.println();
		} else {
			for (Film searchedFilm : films) {
				System.out.print(searchedFilm.shortToString());
				printActors(searchedFilm);
			}
		}
	}

	private void printActors(Film film) {
		String listActors = "";
		System.out.print("Featuring: ");
		try {
			List<Actor> actors = db.findActorsByFilmId(film.getId());
			for (Actor actor : actors) {
				listActors = listActors.concat(actor.getFullName() + ", ");
			}
			listActors = listActors.substring(0, listActors.length() - 2) + ".";
			System.out.println(listActors);
		} catch (Exception e) {
			System.out.println("Unknown.");
		}
		System.out.println();
	}

	private void learnMore(Scanner input, Film film) {
		System.out.println("Want to learn more? Y/N");
		String selection = input.next().toLowerCase();
		switch (selection) {
		case "yes":
		case "y":
		case "1":
			System.out.println(film);
			System.out.println();
			break;
		case "no":
		case "n":
		case "2":
			break;
		default:
			System.out.println("That was not a proper response.");
			System.out.println();
		}
	}

}
