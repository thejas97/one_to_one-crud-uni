package com.one_to_one.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.one_to_one.dto.Invoice;
import com.ty.one_to_one.dto.Item;

public class InvoiceItem {

	private EntityManagerFactory entityManagerFactory = null;
	private EntityManager entityManager = null;
	private EntityTransaction entityTransaction = null;

	private EntityManager getEntityManager() {

		entityManagerFactory = Persistence.createEntityManagerFactory("developer");
		entityManager = entityManagerFactory.createEntityManager();

		return entityManager;

	}

	public void save(Item item, Invoice invoice) {

		entityManager = getEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(invoice);
		entityManager.persist(item);

		entityTransaction.commit();
	}

	public Invoice getInvoice(int id) {

		entityManager = getEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		Invoice invoice = entityManager.find(Invoice.class, id);
          entityTransaction.commit();
		
		return invoice;

	}
	
	public Item getItem(int id) {

		entityManager = getEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		Item item = entityManager.find(Item.class, id);
		
		entityTransaction.commit();

		return item;

	}
	
	public Invoice updateInvoice(Invoice invoice) {
		
		entityManager = getEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.merge(invoice);
		
		entityTransaction.commit();
		
		return invoice;
			
	}
	
	public Item updateItem(Item item) {
		
		entityManager = getEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.merge(item);
		
		entityTransaction.commit();
		
		return item;
			
	}
	
	public void deleteInvoice(int id) {
		
		entityManager = getEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Invoice invoice = entityManager.find(Invoice.class, id);
		
		if(invoice!=null) {
			
			Item item = invoice.getItem();
			entityManager.remove(invoice);
		
			if(item!=null) {
				
				entityManager.remove(item);
			}
		}
		entityTransaction.commit();
	}
	
	public void deleteItem(int id) {
		
		entityManager = getEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Item item = entityManager.find(Item.class, id);
		
		if(item !=null) {
			
			entityManager.remove(item);
		}
		
		entityTransaction.commit();
		
	}
	
	

}
