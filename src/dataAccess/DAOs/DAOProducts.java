package dataAccess.DAOs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import model.roles.MUser;
import dataAccess.Entities.Console;
import dataAccess.Entities.Product;

public class DAOProducts extends DAO{

	public  Product getProduct(int idProduct){
		return (Product) getByID(idProduct, Product.class);
	}

	public void createProduct(Product product){
		create(product);
	}
	
	public void updateProduct(Product product){
		update(product);
	}
	
	public void deleteProduct(Product product){
		Product deleted=getProduct(product.getIdProduct());
		delete(deleted);
	}
	
	public List<Product> getAllProducts(){
		return getAll(Product.class);
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
	
}
