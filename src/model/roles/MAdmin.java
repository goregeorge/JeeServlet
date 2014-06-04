package model.roles;

import java.util.Date;
import java.util.List;

import dataAccess.DAOs.DAOProducts;
import dataAccess.Entities.Console;
import dataAccess.Entities.Product;

public class MAdmin extends MUser{

	public MAdmin(int id, String userName, String passWord,
			String name, String lastName, String email, Date birthDate) {
		
		super(id, true, userName, passWord, name, lastName, email, birthDate);
	}

	public MAdmin(String userName, String passWord,
			String name, String lastName, String email, Date birthDate) {
		
		super(true, userName, passWord, name, lastName, email, birthDate);
	}
	
	
	public Product createProduct(String name, String description, float prize, int quantity, List<Console> consoles){
		DAOProducts daop=new DAOProducts();
		int id=daop.getAllProducts().size()+1;
		
		//Creando nuevo producto
		Product product=new Product(id, name, description, prize, 0, quantity, new Date());
		
		for(int i=0; i<consoles.size(); i++){
			product.getConsoles().add(consoles.get(i));
		}		
		
		//Almacenandolo en la base de datos
		daop.createProduct(product);
		
		return product;
	}
	
	public boolean updateProduct(Product product){
		return false;
	}
	
	public boolean deleteProduct(Product product){
		return false;
	}
	
	public Product getProduct(int idProduct){
		return null;
	}
	
	public List<Product> getCatalog(){
		return null;
	}
	
	public List<Product> getCatalogByConsole(Console console){
		return null;
	}
	
	public MUser createUser(){
		return null;
	}
	
	public boolean updateUser(MUser user){
		return false;
	}
	
	public MUser getUser(int idUser){
		return null;
	}
	
	public boolean deleteUser(MUser user){
		return false;
	}
	
	public List<MUser> getAllUsers(){
		return null;
	}
	
	public List<MClient> getAllClients(){
		return null;
	}
	
	public MAdmin upGradeUser(MClient client){
		return null;
	}
	
	public Console createConsole(String name){
		return null;
	}
	
	public Console getConsole(int id){
		return null;
	}
	
	public boolean updateConsole(Console console){
		return false;
	}
	
	public boolean deleteConsole(Console console){
		return false;
	}
	
	public List<Console> getAllConsoles(){
		return null;
	}
	
	public List getProductEstadistics(Product product){
		return null;
	}
	
}
