package model.roles;

import java.util.Date;

import dataAccess.DAOs.DAOProducts;
import dataAccess.DAOs.DAOShoppingCarAndHistory;
import dataAccess.DAOs.DAOUsuarios;
import dataAccess.Entities.Product;
import dataAccess.Entities.Shoppingcarhistory;

public abstract class MUser {

	private int id;
	private boolean type;
	private String userName;
	private String passWord;
	private String name;
	private String lastName;
	private String email;
	private Date birthDate;
	
	public MUser(int id, boolean type, String userName, String passWord, 
			String name, String lastName, String email, Date birthDate) {
		this.id=id;
		this.type = type;
		this.userName = userName;
		this.passWord = passWord;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
	}
	
	public MUser(boolean type, String userName, String passWord, 
			String name, String lastName, String email, Date birthDate) {
		this.id=-1;
		this.type = type;
		this.userName = userName;
		this.passWord = passWord;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
	}
	
	public boolean isType(){
		return this.type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}
	
	
	
	
	
}
