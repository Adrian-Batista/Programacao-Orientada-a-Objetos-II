package br.com.adrianbatista.youtube.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.adrianbatista.youtube.entities.User;

public class UsersInFile {

	public void check() {
		String fileLocation = "./local-users.json";
		List<String> fileLines = new ArrayList<>();
		try {
			File file = new File(fileLocation);
			if (file.exists()) {
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine())
					fileLines.add(scanner.nextLine());
				scanner.close();
			} else {
				System.err.println("File \"" + fileLocation + "\" is missing.");
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error while opening file \"" + fileLocation + "\".");
		}

		// processamento
		List<User> userList = UtilDB.consumeAPI(fileLines);
		for (User t : userList) {
			User existingTeacher = new UserDAO().get(t.getUsername());
			if (existingTeacher != null) {
				if (!t.getUsername().contentEquals(existingTeacher.getUsername())) {
					new UserDAO().persist(t);
				}
			} else {
				new UserDAO().persist(t);
			}
		}
	}
}
