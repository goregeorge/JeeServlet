package model.roles;

import java.util.Date;
import java.util.List;

import dataAccess.DAOs.DAOConsoles;
import dataAccess.DAOs.DAOProducts;
import dataAccess.DAOs.DAOUsuarios;
import dataAccess.Entities.Product;
import dataAccess.Entities.Wishlist;

public class Prueba {

	public static void main(String[] args) {
		
		
		DAOProducts products=new DAOProducts();
		
		Product nuevo=new Product(2, "GTA V", "El nuevo juego por parte de rockstar games vuelve a los santos", 1500, 0, 500, new Date());
		
		DAOConsoles con=new DAOConsoles();
		
		nuevo.getConsoles().add(con.getConsole(1));
		
		products.createProduct(nuevo);
		
		
		System.out.println("lol");
		
	}

}
