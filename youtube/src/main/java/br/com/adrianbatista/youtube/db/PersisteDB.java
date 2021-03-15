package br.com.adrianbatista.youtube.db;

public class PersisteDB implements Runnable{
	
	@Override
	public void run() {
		UsersInFile userInFile = new UsersInFile();
		try {
			while (true) {
				userInFile.check();
				Thread.sleep(4200);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
