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
	
	public MUser getUser(String userName, String passWord){
		List<MUser> all=getAllUsers();
		
		MUser result=null;
		MUser temp;
		for(int i=0; i<all.size(); i++){
			temp=all.get(i);
			if(temp.getUserName().equals(userName)){
				result=temp;
				break;
			}
		}
		
		if(result!=null && result.getPassWord().equals(passWord)){
			return result;
		}else return null;
		
	}
	
	public List<MUser> getAllUsers(){
		List<User> users = getAll(User.class);
		
		List<MUser> resultUsers=new ArrayList<MUser>();
		
		for (int i = 0; i < users.size(); i++) {
			resultUsers.add( getMUserFromEntitie(users.get(i)) );
		}
		
		return resultUsers;
	}
	
	public MUser createUser(MUser user){
		User newUser=getEntitieFromMUser(user);
		create(newUser);
		return getUserById(newUser.getId().getIdUser(), newUser.getId().isType());
	}
	
	public void updateUser(MUser user){
		update( getEntitieFromMUser(user) );
	}
	
	public void deleteUser(MUser user){
		MUser userdelete=getUserById(user.getId(), user.isType());
		
		delete( getEntitieFromMUser(userdelete) );
	}
	
	public List<MClient> getAllCliets(){
		List<MUser> all=getAllUsers();
		List<MClient> result=new ArrayList<MClient>();
		
		for(int i=0; i<all.size(); i++){
			if(!all.get(i).isType()){
				result.add((MClient)all.get(i));
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
		return new User(((user.getId()==-1)?(getAllUsers().size()+1):user.getId()), user.isType(), user.getUserName(), user.getPassWord(), user.getName(),
			user.getLastName(), user.getEmail(), user.getBirthDate());
	}
	
	
}
