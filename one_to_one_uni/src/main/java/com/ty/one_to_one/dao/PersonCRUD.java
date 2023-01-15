package com.ty.one_to_one.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.ty.one_to_one_uni.dto.AdhaarCard;
import com.ty.one_to_one_uni.dto.Person;

public class PersonCRUD {
	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("venu");
		return entityManagerFactory.createEntityManager();
	}

	public void SavePerson(Person person) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		AdhaarCard adhaarCard = person.getAdhaarCard();
		entityTransaction.begin();
		entityManager.persist(person);
		entityManager.persist(adhaarCard);
		entityTransaction.commit();
	}

	public void updatePerson(Person person) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		AdhaarCard adhaarCard = person.getAdhaarCard();
		entityTransaction.begin();
		entityManager.merge(person);
		entityManager.merge(adhaarCard);
		entityTransaction.commit();
	}

	public void deletePerson(int id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Person person = entityManager.find(Person.class, id);
		if (person != null) {
			entityTransaction.begin();
			AdhaarCard adhaarCard = person.getAdhaarCard();
			entityManager.remove(person);
			entityManager.remove(adhaarCard);
			entityTransaction.commit();
			System.out.println("deleted");
		} else {
			System.out.println("No Si=uch Id");
		}
	}

	public Person getdetails(int id) {
		EntityManager entityManager = getEntityManager();
		return entityManager.find(Person.class, id);
	}

	public List<Person> getDetailsList() {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createQuery("select p from Person p");
		List<Person> list = query.getResultList();
		return list;
	}
}
