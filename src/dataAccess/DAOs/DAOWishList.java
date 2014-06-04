package dataAccess.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import dataAccess.Entities.Product;
import dataAccess.Entities.Wishlist;
import dataAccess.Entities.WishlistId;
import model.roles.MUser;

public class DAOWishList extends DAO{

	public Wishlist getWishListById(MUser user, Product product){
		
		WishlistId id=new WishlistId(user.getId(), user.isType(), product.getIdProduct());
		
		Session s=getSession();
		Wishlist result=(Wishlist) s.get(Wishlist.class, id);
		commitTransaction(s);
		return result;
	}
	
	public List<Wishlist> getAllWishList(){
		return getAll(Wishlist.class);
	}
	
	public void createWishlistRegistry(Wishlist wish){
		create(wish);
	}
	
	public void updateWishListRegistry(Wishlist wish){
		update(wish);
	}
	
	public void deleteWishListRegistry(Wishlist wish){
		DAOUsuarios user=new DAOUsuarios();
                DAOProducts prod=new DAOProducts();
		Wishlist wishtodelete=getWishListById(user.getUserById(wish.getId().getUserIdUser(), wish.getId().isUserType()), prod.getProduct(wish.getId().getProductIdProduct()));
		
		delete(wishtodelete);
	}
	
	public List<Wishlist> getWishListFromUser(MUser user){
		List<Wishlist> all=getAllWishList();
		List<Wishlist> wishuser=new ArrayList<Wishlist>();
		
		for(int i=0; i<all.size(); i++){
			if( all.get(i).getUser().getId().getIdUser()==user.getId() ){
				wishuser.add(all.get(i));
			}
		}
		
		return wishuser;
	}
	
	public List<Wishlist> getWishListsFromProduct(Product product){
		
		List<Wishlist> all=getAllWishList();
		List<Wishlist> wishuser=new ArrayList<Wishlist>();
		
		for(int i=0; i<all.size(); i++){
			if( all.get(i).getProduct().getIdProduct()==product.getIdProduct() ){
				wishuser.add(all.get(i));
			}
		}
		
		return wishuser;
	}
	
}
