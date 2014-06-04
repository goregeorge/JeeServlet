package dataAccess.DAOs;

import java.util.List;

import model.roles.MUser;
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
	
}
