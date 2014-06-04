package dataAccess.DAOs;

import java.util.List;

import dataAccess.Entities.Console;

public class DAOConsoles extends DAO{

	public  Console getConsole(int idConsole){
		return (Console) getByID(idConsole, Console.class);
	}

	public void createConsole(Console console){
		create(console);
	}
	
	public void updateConsole(Console console){
		update(console);
	}
	
	public void deleteConsole(Console console){
		Console deleted=getConsole(console.getIdConsole());
		delete(deleted);
	}
	
	public List<Console> getAllConsoles(){
		return getAll(Console.class);
	}
	
}
