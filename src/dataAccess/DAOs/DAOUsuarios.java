package dataAccess.DAOs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import dataAccess.Entities.User;
import dataAccess.Entities.UserId;
import model.roles.*;

public class DAOUsuarios extends DAO{
	
	public MUser getUserById(int id, boolean type){
		UserId uid=new UserId(id, type);
		
		Session s=getSession();
		User result=(User) s.get(User.class, uid);
		commitTransaction(s);
		
		return getMUserFromEntitie(result);
	}
	
	public List<MUser> getAllUsers(){
		List<User> users = getAll(User.class);
		
		List<MUser> resultUsers=new ArrayList<MUser>();
		
		for (int i = 0; i < users.size(); i++) {
			resultUsers.add( getMUserFromEntitie(users.get(i)) );
		}
		
		return resultUsers;
	}
	
	public void createUser(MUser user){
		create( getEntitieFromMUser(user) );
	}
	
	public void updateUser(MUser user){
		update( getEntitieFromMUser(user) );
	}
	
	public void deleteUser(MUser user){
		MUser userdelete=getUserById(user.getId(), user.isAdmin());
		
		delete( getEntitieFromMUser(userdelete) );
	}
	
	public List<MUser> getAllCliets(){
		List<MUser> all=getAllUsers();
		List<MUser> result=new ArrayList<MUser>();
		
		for(int i=0; i<all.size(); i++){
			if(!all.get(i).isAdmin()){
				result.add(all.get(i));
			}
		}
		
		return result;
	}
	
	
	public MUser getMUserFromEntitie(User user){
		if(user.getId().isType()){
			return new MAdmin(user.getId().getIdUser(), user.getUserName(), user.getPassWord(),
					user.getName(), user.getLastName(), user.getEmail(), user.getBirthDate());
		}else{
			DAOPayments daop=new DAOPayments();
			DAODirectionAddress daodi= new DAODirectionAddress();
			DAOShoppingCarAndHistory daosh= new DAOShoppingCarAndHistory();
			DAOWishList daowish=new DAOWishList();
			
			
			MClient client=new MClient(user.getId().getIdUser(), user.getUserName(), user.getPassWord(),
					user.getName(), user.getLastName(), user.getEmail(), user.getBirthDate());
			
			client.setPayments(daop.getPaymentsFromUser(client));
			client.setDirectionAdress(daodi.getDirectionaddresFromUser(client));
			client.setShoppingCar(daosh.getShoppingCar(client));
			client.setHistory(daosh.getHistory(client));
			client.setWishList(daowish.getWishListFromUser(client));
			
			return client;
		}
	}
	
	public User getEntitieFromMUser(MUser user){
		return new User(((user.getId()==-1)?(getAllUsers().size()+1):user.getId()), user.isAdmin(), user.getUserName(), user.getPassWord(), user.getName(),
			user.getLastName(), user.getEmail(), user.getBirthDate());
	}
	
	
}
