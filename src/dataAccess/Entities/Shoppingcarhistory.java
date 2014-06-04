package dataAccess.Entities;

// Generated 20/05/2014 01:17:10 AM by Hibernate Tools 4.0.0

import java.util.Date;

import model.roles.MUser;

/**
 * Shoppingcarhistory generated by hbm2java
 */
public class Shoppingcarhistory implements java.io.Serializable {

	private ShoppingcarhistoryId id;
	private Product product;
	private User user;
	private Date date;
	private int quantity;
	private float total;
	private boolean isBuyed;

	public Shoppingcarhistory(){
		
	}
	
	public Shoppingcarhistory(MUser user, Product product, int idCart, Date date, int quantity, float total, boolean isBuyed) {
		
		ShoppingcarhistoryId id=new ShoppingcarhistoryId(user.getId(), user.isAdmin(), product.getIdProduct(), idCart );
		User entitieU=new User(user.getId(), user.isAdmin(), user.getUserName(), user.getPassWord(), user.getName(), user.getLastName(), user.getEmail(), user.getBirthDate());
		
		this.id = id;
		this.product = product;
		this.user = entitieU;
		this.date = date;
		this.quantity = quantity;
		this.total = total;
		this.isBuyed = isBuyed;
		
	}

	public Shoppingcarhistory(ShoppingcarhistoryId id, Product product,
			User user, Date date, int quantity, float total, boolean isBuyed) {
		this.id = id;
		this.product = product;
		this.user = user;
		this.date = date;
		this.quantity = quantity;
		this.total = total;
		this.isBuyed = isBuyed;
	}

	public ShoppingcarhistoryId getId() {
		return this.id;
	}

	public void setId(ShoppingcarhistoryId id) {
		this.id = id;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotal() {
		return this.total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public boolean isIsBuyed() {
		return this.isBuyed;
	}

	public void setIsBuyed(boolean isBuyed) {
		this.isBuyed = isBuyed;
	}

}
