package model.roles;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;

import dataAccess.DAOs.DAOConsoles;
import dataAccess.DAOs.DAOProducts;
import dataAccess.DAOs.DAOShoppingCarAndHistory;
import dataAccess.DAOs.DAOUsuarios;
import dataAccess.Entities.Console;
import dataAccess.Entities.Product;
import dataAccess.Entities.Shoppingcarhistory;

public class MAdmin extends MUser{

	public MAdmin(int id, String userName, String passWord,
			String name, String lastName, String email, Date birthDate) {
		
		super(id, true, userName, passWord, name, lastName, email, birthDate);
	}

	public MAdmin(String userName, String passWord,
			String name, String lastName, String email, Date birthDate) {
		
		super(true, userName, passWord, name, lastName, email, birthDate);
	}
	
	
	public Product createProduct(String name, String description, float prize, int quantity, String imageRute, List<Console> consoles){
		DAOProducts daop=new DAOProducts();
		int id=daop.getAllProducts().size()+1;
		
		//Creando nuevo producto
		Product product=new Product(id, name, description, prize, 0, quantity, new Date(), imageRute);
		
		for(int i=0; i<consoles.size(); i++){
			product.getConsoles().add(consoles.get(i));
		}		
		
		//Almacenandolo en la base de datos
		daop.createProduct(product);
		
		return product;
	}
	
	public boolean updateProduct(Product product){
		DAOProducts daop=new DAOProducts();
				
		 //Actualizando la base de datos
		 daop.updateProduct(product);		
		 return true;
	}
	
	public boolean deleteProduct(Product product){
		DAOProducts daop=new DAOProducts();
		
		daop.deleteProduct(product);
		
		return true;
	}
	
	public Product getProduct(int idProduct){
		DAOProducts daop=new DAOProducts();
		return daop.getProduct(idProduct);
	}
	
	public List<Product> getCatalog(){
		DAOProducts daop=new DAOProducts();
		return daop.getAllProducts();
	}
	
	public List<Product> getCatalogByConsole(Console console){
		DAOConsoles daocns=new DAOConsoles();
		ArrayList<Product> products=new ArrayList<Product>();
		
		Console cons= daocns.getConsole(console.getIdConsole());
		
		Iterator<Product> prd=cons.getProducts().iterator();
		
		while(prd.hasNext()){
			products.add(prd.next());
		}
		
		return products;
	}
	
	public MUser createUser(int id, boolean isAdmin, String userName, String passWord,	String name, String lastName, String email, Date birthDate){
		DAOUsuarios daou=new DAOUsuarios();
		if(isAdmin){
			MAdmin admin=new MAdmin(userName, passWord, name, lastName, email, birthDate);
			return daou.createUser(admin);
		}else{
			MClient client=new MClient(userName, passWord, name, lastName, email, birthDate);
			return daou.createUser(client);
		}
	}
	
	public boolean updateUser(MUser user){
		DAOUsuarios daou=new DAOUsuarios();
		
		//Actualizando la bd
		daou.updateUser(user);
		
		return true;
	}
	
	public MUser getUser(int idUser, boolean isAdmin){
		DAOUsuarios daou=new DAOUsuarios();
		return daou.getUserById(1, isAdmin);
	}
	
	public boolean deleteUser(MUser user){
		DAOUsuarios daou=new DAOUsuarios();
		
		//Eliminando de la bd
		daou.deleteUser(user);
		
		return false;
	}
	
	public List<MUser> getAllUsers(){
		DAOUsuarios daou=new DAOUsuarios();
		return daou.getAllUsers();
	}
	
	public List<MClient> getAllClients(){
		DAOUsuarios daou=new DAOUsuarios();
		return daou.getAllCliets();
	}
	
	public MAdmin upGradeUser(MClient client){
		MAdmin admin=new MAdmin(client.getId(), client.getUserName(), client.getPassWord(), client.getName(), client.getLastName(), client.getEmail(), client.getBirthDate());
		
		//Actualizando la base de datos
		updateUser(admin);
		
		return admin;
	}

	public MClient deGradeUser(MAdmin admin){
		MClient client=new MClient(admin.getId(), admin.getUserName(), admin.getPassWord(), admin.getName(), admin.getLastName(), admin.getEmail(), admin.getBirthDate());
		
		updateUser(client);
		
		return client;
	}
	
	public Console createConsole(String name){
		DAOConsoles daoc=new DAOConsoles();
		
		int id=daoc.getAllConsoles().size()+1;
		
		//Creando nueva consola
		Console console=new Console(id, name);
		
		//Actualizando base de datos
		daoc.createConsole(console);		
		
		return console;
	}
	
	public Console getConsole(int id){
		DAOConsoles daoc=new DAOConsoles();
		
		return daoc.getConsole(id);
	}
	
	public boolean updateConsole(Console console){
		DAOConsoles daoc=new DAOConsoles();
		
		//Actualizando base de datos
		daoc.updateConsole(console);
		
		return true;
	}
	
	public boolean deleteConsole(Console console){
		DAOConsoles daoc=new DAOConsoles();
		
		//Eliminando de la base de datos
		
		daoc.deleteConsole(console);
		
		return true;
	}
	
	public List<Console> getAllConsoles(){
		DAOConsoles daoc=new DAOConsoles();
		return daoc.getAllConsoles();
	}
	
	
	@SuppressWarnings("deprecation")
	public String ageRangeSolds(Product product){
		DAOShoppingCarAndHistory daohist= new DAOShoppingCarAndHistory();
		DAOProducts daoProduct=new DAOProducts();
		
		List<Shoppingcarhistory> history=daohist.getAllHistorys();
		
		List<Shoppingcarhistory> allBuyedProduct=new ArrayList<Shoppingcarhistory>();;
		
		for(int i=0; i<history.size(); i++){
			Product temp=daoProduct.getProduct( history.get(i).getId().getProductIdProduct() );
			if(temp.getIdProduct()==product.getIdProduct()){
				allBuyedProduct.add(history.get(i));
			}
		}
		
		int minAge=0;
		int temp;
		for(int i=0; i<allBuyedProduct.size(); i++){
			long ageInMillis = new Date().getTime() - getBirthDate().getTime();
			Date age = new Date(ageInMillis);
			if(age.getYear()<minAge || minAge==0){
				minAge=age.getYear();
			}
		}
		
		int maxAge=0;
		for(int i=0; i<allBuyedProduct.size(); i++){
			long ageInMillis = new Date().getTime() - getBirthDate().getTime();
			Date age = new Date(ageInMillis);
			if(age.getYear()>maxAge || maxAge==0){
				minAge=age.getYear();
			}
		}
		
		return minAge+"-"+maxAge;
	}
	
	public double getSoldPercentage(Product product){
		DAOShoppingCarAndHistory daohist= new DAOShoppingCarAndHistory();
		DAOProducts daoProduct=new DAOProducts();
		
		List<Shoppingcarhistory> history=daohist.getAllHistorys();
		List<Shoppingcarhistory> historyToCreation=new ArrayList<Shoppingcarhistory>();
		
		for(int i=0; i<history.size(); i++){
			Product temp=daoProduct.getProduct( history.get(i).getId().getProductIdProduct() );
			if(temp.getCreationDate().compareTo(product.getCreationDate())>=0){
				historyToCreation.add(history.get(i));
			}
		}
		
		List<Shoppingcarhistory> allBuyedProduct=new ArrayList<Shoppingcarhistory>();;
		
		for(int i=0; i<history.size(); i++){
			Product temp=daoProduct.getProduct( history.get(i).getId().getProductIdProduct() );
			if(temp.getIdProduct()==product.getIdProduct()){
				allBuyedProduct.add(history.get(i));
			}
		}
		
		double soldPercentage=(allBuyedProduct.size()*100)/history.size();
		
		return soldPercentage;
	}
	
	public double getNumberOfSolds(Product product){

		DAOShoppingCarAndHistory daohist= new DAOShoppingCarAndHistory();
		DAOProducts daoProduct=new DAOProducts();
		
		List<Shoppingcarhistory> history=daohist.getAllHistorys();
		
		List<Shoppingcarhistory> allBuyedProduct=new ArrayList<Shoppingcarhistory>();;
		
		for(int i=0; i<history.size(); i++){
			Product temp=daoProduct.getProduct( history.get(i).getId().getProductIdProduct() );
			if(temp.getIdProduct()==product.getIdProduct()){
				allBuyedProduct.add(history.get(i));
			}
		}
		
		double timesSold=allBuyedProduct.size();
		return timesSold;
	}
	
	
}
