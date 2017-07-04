package tests;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Ignore;
import org.junit.Test;

import model.Carros;
import util.JpaUtil;

public class TestsCarros {
	
	@Test
	@Ignore
	public void criarCarro(){
		Persistence.createEntityManagerFactory("CarroPu");
	}
	
	@Test
	@Ignore
	public void addCarro(){
		Carros c = new Carros("Chevrolet","Celta",2013,2014,new BigDecimal(22000.00));
		EntityManager manager = JpaUtil.getManagerFactory();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(c);
		transaction.commit();
		manager.close();
		JpaUtil.closeEntityManager();
	}
	
	@Ignore
	@Test
	public void buscarCarros(){
		EntityManager manager = JpaUtil.getManagerFactory();
		Carros c = manager.find(Carros.class, 14L);
		if(manager!=null)
			System.out.println("\t\n\nCod "+c.getCodigo()+" Carro "+c.getModelo()+" valor "+c.getValor());
		
		
		manager.close();
		JpaUtil.closeEntityManager();
	}
	
	@Test
	@Ignore
	public void bucandoPorReference(){
		EntityManager manager = JpaUtil.getManagerFactory();
		Carros c = manager.getReference(Carros.class, 1L);
		if(manager!=null){
			System.out.println("Carro "+c.getModelo());
		}
		manager.close();
		JpaUtil.closeEntityManager();
	}
	
	@Test
	@Ignore
	public void listarCarros(){
		EntityManager manager = JpaUtil.getManagerFactory();
		Query query = manager.createQuery("from Carros");
		@SuppressWarnings("unchecked")
		List<Carros> carros = query.getResultList();
		for(Carros c: carros){
			System.out.println("Carro "+c.getModelo()+" "+c.getValor());
		}
		manager.close();
		JpaUtil.closeEntityManager();	
	}
	
	@Test
	@Ignore
	public void editarCarro(){
		EntityManager manager = JpaUtil.getManagerFactory();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Carros c = manager.find(Carros.class, 1L);
		System.out.println("veiculo "+c.getModelo()+" Valor atual "+c.getValor());
		c.setValor(new BigDecimal(73000.00));
		transaction.commit();
		System.out.println("Novo valor "+c.getValor());
		manager.close();
		JpaUtil.closeEntityManager();
	}
	
	@Test
	@Ignore
	public void alterarValor(){
		EntityManager manager = JpaUtil.getManagerFactory();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Carros c = manager.find(Carros.class, 2L);
		System.out.println("Aumentanto o  valor do carro "+c.getModelo()+" \nem 10.000 reias ");
		c.setValor(c.getValor().add(new BigDecimal(10000.00)));
		transaction.commit();
		System.out.println(c.getModelo()+" novo valor "+c.getValor());
		manager.close();
		JpaUtil.closeEntityManager();
	}
	
	@Test
	@Ignore
	public void excluirCarro(){
		EntityManager manager = JpaUtil.getManagerFactory();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Carros c = manager.find(Carros.class, 2L);
		if(manager!=null){
			manager.remove(c);
			transaction.commit();
		}
		manager.close();
		JpaUtil.closeEntityManager();
		
	}
	
}
