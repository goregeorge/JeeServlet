package dataAccess.DAOs;

import java.util.ArrayList;
import java.util.List;

import model.roles.MUser;
import dataAccess.Entities.Directionaddres;
import dataAccess.Entities.Shoppingcarhistory;

public class DAODirectionAddress extends DAO{

	public  Directionaddres getDirectionaddres(int idDirectionaddres){
		return (Directionaddres) getByID(idDirectionaddres, Directionaddres.class);
	}

	public void createDirectionaddres(Directionaddres directionaddres){
		create(directionaddres);
	}
	
	public void updateDirectionaddres(Directionaddres directionaddres){
		update(directionaddres);
	}
	
	public void deleteDirectionaddres(Directionaddres directionaddres){
		Directionaddres deleted=getDirectionaddres(directionaddres.getIdDirectionAddres());
		delete(deleted);
	}
	
	public List<Directionaddres> getAllDirectionaddres(){
		return getAll(Directionaddres.class);
	}
	
	public List<Directionaddres> getDirectionaddresFromUser(MUser user){
		List<Directionaddres> all=getAllDirectionaddres();
		List<Directionaddres> result=new ArrayList();
		
		for(int i=0; i<all.size(); i++){
			int userId=all.get(i).getUser().getId().getIdUser();
			
			if(userId==user.getId()){
				result.add(all.get(i));
			}
		}
		
		return result;
	}
	
}
