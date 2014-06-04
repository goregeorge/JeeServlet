package model.roles;

import java.util.Date;
import java.util.List;

import dataAccess.DAOs.DAODirectionAddress;
import dataAccess.DAOs.DAOPayments;
import dataAccess.DAOs.DAOProducts;
import dataAccess.DAOs.DAOShoppingCarAndHistory;
import dataAccess.DAOs.DAOUsuarios;
import dataAccess.DAOs.DAOWishList;
import dataAccess.Entities.Directionaddres;
import dataAccess.Entities.Payment;
import dataAccess.Entities.Product;
import dataAccess.Entities.Shoppingcarhistory;
import dataAccess.Entities.Wishlist;

public class MClient extends MUser{

	private List<Payment> payments;
	private List<Directionaddres> directionAdress;
	private List<Shoppingcarhistory> shoppingCar;
	private List<Shoppingcarhistory> history;
	private List<Wishlist> wishList;
	
	public MClient(int id, String userName, String passWord,
			String name, String lastName, String email, Date birthDate) {
		
		super(id, false, userName, passWord, name, lastName, email, birthDate);		
	}
	
	public MClient(String userName, String passWord,
			String name, String lastName, String email, Date birthDate) {
		
		super(false, userName, passWord, name, lastName, email, birthDate);		
	}

	//Getters y setters analizar mas abajo
	
	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public List<Directionaddres> getDirectionAdress() {
		return directionAdress;
	}

	public void setDirectionAdress(List<Directionaddres> directionAdress) {
		this.directionAdress = directionAdress;
	}

	public List<Shoppingcarhistory> getShoppingCar() {
		return shoppingCar;
	}

	public void setShoppingCar(List<Shoppingcarhistory> shoppingCar) {
		this.shoppingCar = shoppingCar;
	}

	public List<Shoppingcarhistory> getHistory() {
		return history;
	}

	public void setHistory(List<Shoppingcarhistory> history) {
		this.history = history;
	}

	public List<Wishlist> getWishList() {
		return wishList;
	}

	public void setWishList(List<Wishlist> wishList) {
		this.wishList = wishList;
	}
	
	//Apartir de aca codigo de la logica
	
	
	public boolean buyAProduct(Product product, int quantity){
		try{
			DAOProducts daoproducts=new DAOProducts();
			DAOShoppingCarAndHistory daoHistory=new DAOShoppingCarAndHistory();
			DAOUsuarios daousers= new DAOUsuarios();
			
			if((product.getQuantity()-quantity)<0){ //verifica si la compra puede realizarse
				return false;
			}else{
				//crea un nuevo registro de compra
				Shoppingcarhistory newHistoryRegistry=new Shoppingcarhistory(this, product, (daoHistory.getAllShoppingCarHistory().size() + 1) , new Date(), quantity, (product.getPrize()*quantity), true);
				
				//Actualiza el historial de la base de datos, y actualiza el historial del cliente
				daoHistory.create(newHistoryRegistry);
				setHistory(daoHistory.getHistory(this));
				
				//actualiza la cantidad del producto cuando fue comprado
				product.setQuantity(product.getQuantity() - quantity);
				daoproducts.updateProduct(product);
				
				return true;
			}
			
		}catch(Exception e){
			return false;
		}
	}
	
	
	public boolean setProductInWishList(Product product, int quantity){
		try{
			if((product.getQuantity()-quantity)<0){ //verifica si se puede realizar esta transaccion
				return false;
			}else{
				DAOWishList daowish=new DAOWishList();
				DAOProducts daoproducts=new DAOProducts();
				
				//Crea un registro de wishlist
				Wishlist wish=new Wishlist(product, this, quantity+"");
				
				//Guarda el registro en la bd y en el cliente
				daowish.updateWishListRegistry(wish);
				setWishList(daowish.getWishListFromUser(this));
				
				//actualiza la informacion del producto
				product.setQuantity(product.getQuantity() - quantity);
				daoproducts.updateProduct(product);
				
				return true;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setAProductInShoppingCar(Product product, int quantity){
		try{
			DAOProducts daoproducts=new DAOProducts();
			DAOShoppingCarAndHistory daoHistory=new DAOShoppingCarAndHistory();
			DAOUsuarios daousers= new DAOUsuarios();
			
			if((product.getQuantity()-quantity)<0){ //verifica si la accion puede realizarse
				return false;
			}else{
				//crea un nuevo registro de carrito
				int idCart = daoHistory.getAllShoppingCarHistory().size() + 1;
				Shoppingcarhistory newHistoryRegistry=new Shoppingcarhistory(this, product, idCart , new Date(), quantity, (product.getPrize()*quantity), false);
				
				//Actualiza el historial de la base de datos, y actualiza el historial del cliente
				daoHistory.create(newHistoryRegistry);
				setHistory(daoHistory.getHistory(this));
				
				//actualiza la cantidad del producto cuando fue comprado
				product.setQuantity(product.getQuantity() - quantity);
				daoproducts.updateProduct(product);
				
				return true;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean buyAProductInShoppingCar(Shoppingcarhistory registry){
		try{
			DAOShoppingCarAndHistory daoshop=new DAOShoppingCarAndHistory();
			
			//Actualizando el registro a comprado
			registry.setIsBuyed(true);
			
			//Actualizando la base de datos y el historial y carrito del cliente
			daoshop.update(registry);
			setHistory(daoshop.getHistory(this));
			setShoppingCar(daoshop.getShoppingCar(this));
			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean WishListToShoppingCar(Wishlist registry){
		try{
			DAOShoppingCarAndHistory daoshop=new DAOShoppingCarAndHistory();
			DAOWishList daowish=new DAOWishList();
                        
                        DAOProducts daoprod=new DAOProducts();
                        
			//creando el nuevo registro de carrito de compras
                        Product product= daoprod.getProduct(registry.getId().getProductIdProduct());
			int idCart = daoshop.getAllShoppingCarHistory().size() + 1;
                        
			Shoppingcarhistory registro= new Shoppingcarhistory(this, product, idCart, new Date(), Integer.valueOf(registry.getQuantity()), ( product.getPrize() * Integer.valueOf(registry.getQuantity()) ), false);
			
			//Guardando en bd el registro de carrito de compra
			daoshop.create(registro);
			
			//eliminando el registro de la wishlist de la bd
			daowish.deleteWishListRegistry(registry);
			
			//Actualizando los datos del cliente
			setWishList(daowish.getWishListFromUser(this));
			setShoppingCar(daoshop.getShoppingCar(this));
			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean deleteRegistrytInWishList(Wishlist registry){
		try{
			DAOWishList daowish=new DAOWishList();
			DAOProducts daoproduct=new DAOProducts();
			
			//Eliminando el registro de la base de datos
			daowish.deleteWishListRegistry(registry);
			
			//Actualizando datos del producto
			Product product= daoproduct.getProduct(registry.getId().getProductIdProduct());
			product.setQuantity( product.getQuantity() + Integer.valueOf(registry.getQuantity()) );
			daoproduct.updateProduct(product);
			
			//Actulizando wishlist del cliente
			setWishList(daowish.getWishListFromUser(this));
			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean deleteRegistryFromShoppingCar(Shoppingcarhistory registry){
		try{
			DAOShoppingCarAndHistory daoshop=new DAOShoppingCarAndHistory();
			DAOProducts daoproduct=new DAOProducts();
			
			//Eliminando el registro de la base de datos
			daoshop.delete(registry);			
			
			//Actualizando datos del producto
			Product product=daoproduct.getProduct(registry.getId().getProductIdProduct());
			product.setQuantity(product.getQuantity() + registry.getQuantity());
			daoproduct.updateProduct(product);
			
			//Actualizando carrito del cliente
			setShoppingCar(daoshop.getShoppingCar(this));		
			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setNewPayment(Payment payment){
		try{
			DAOPayments daopayment=new DAOPayments();
			
			//Crendo registro en bd
			daopayment.createPayment(payment);
			
			//Actualizando datos del cliente
			setPayments(daopayment.getPaymentsFromUser(this));
			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	public boolean setNewDirection(Directionaddres direction){
		try{
			DAODirectionAddress daodir=new DAODirectionAddress();
			
			//creando registro en bd
			daodir.createDirectionaddres(direction);			
			
			//Actualizando datos del cliente
			setDirectionAdress(daodir.getDirectionaddresFromUser(this));
			
			return true;
		}catch(Exception e){
			
			return false;
		}
	}
	
	public boolean deletePayment(Payment payment){
		try{
			DAOPayments daopayment=new DAOPayments();
			
			//Crendo registro en bd
			daopayment.deletePayment(payment);
			
			//Actualizando datos del cliente
			setPayments(daopayment.getPaymentsFromUser(this));
			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	public boolean deletDirection(Directionaddres direction){
		try{
			DAODirectionAddress daodir=new DAODirectionAddress();
			
			//creando registro en bd
			daodir.deleteDirectionaddres(direction);			
			
			//Actualizando datos del cliente
			setDirectionAdress(daodir.getDirectionaddresFromUser(this));
			
			return true;
		}catch(Exception e){
			
			return false;
		}
	}
	
	public boolean rateGame(Product product, int stars){
		
		try{
			//Verificando cantidad de estrellas
			if(stars<=5 || stars>=0){
				
				//Sacando promedio de calificacion
				int oldStars=product.getStars();
				int newStars;
				if(oldStars==0){//Verifica si es la primera vez clificada
					newStars=stars;
				}else{
					newStars=(int) Math.ceil((oldStars + stars)/2);
				}
				
				//Cambiando los valores del producto
				product.setStars(newStars);
				
				//Guardando en bd las actualizaciones
				DAOProducts daoproducts=new DAOProducts();
				daoproducts.updateProduct(product);
			
				return true;
			}else return false;
		}catch(Exception e){
			
			
			return false;
		}
		
	}
}
