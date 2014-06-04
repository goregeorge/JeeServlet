package dataAccess.DAOs;

import java.util.ArrayList;
import java.util.List;

import model.roles.MUser;
import dataAccess.Entities.Payment;;

public class DAOPayments extends DAO{
	
	public  Payment getPayment(int idPayment){
		return (Payment) getByID(idPayment, Payment.class);
	}

	public void createPayment(Payment payment){
		create(payment);
	}
	
	public void updatePayment(Payment payment){
		update(payment);
	}
	
	public void deletePayment(Payment payment){
		Payment deleted=getPayment(payment.getIdPayment());
		delete(deleted);
	}
	
	public List<Payment> getAllPayments(){
		return getAll(Payment.class);
	}
	
	public List<Payment> getPaymentsFromUser(MUser user){
		List<Payment> allPayments=getAllPayments();
		List<Payment> paymentsFromUser=new ArrayList<Payment>();
		
		for(int i=0; i<allPayments.size(); i++){
			if( allPayments.get(i).getUser().getId().getIdUser()==user.getId() ){
				paymentsFromUser.add(allPayments.get(i));
			}
		}
			
		return paymentsFromUser;
	}
	
}
