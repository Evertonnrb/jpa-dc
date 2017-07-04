package tests;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.junit.Ignore;
import org.junit.Test;

import model.Proprietario;
import model.TipoCombustivel;
import model.VeiculoId;
import model.Veiculos;
import util.JpaUtil;

public class TestVeiculos {

	@Test
	@Ignore
	public void gerarTabelas() {
		Persistence.createEntityManagerFactory("CarroPu");
	}

	Calendar c = Calendar.getInstance();

	@Test
	 //@Ignore
	public void salvarCarro() {
		EntityManager manager = JpaUtil.getManagerFactory();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		Path path = FileSystems.getDefault().getPath("src/imagens/camaro.jpg");
		byte[] foto = null;
		try {
			foto = Files.readAllBytes(path);
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		StringBuilder sb = new StringBuilder();
		sb.append("Completo com banco de couro, o estep");
		sb.append("nunca foi ao chão, carro de mulher super");
		sb.append("conservado e cheiroso. Oportunidade unica");
		sb.append("não perca essa oportunidade, não tem nem 20.000km rodados");

		Proprietario p = new Proprietario();
		p.setNome("Maria");
		p.setTelefone("3233-2020");
		p.setEmail("maria@yahoo.com");
		manager.persist(p);
		
		Veiculos veiculos = new Veiculos();
		veiculos.setVeiculoId(new VeiculoId("Jks-1010", "JAra"));
		veiculos.setModelo("Camaro");
		veiculos.setFabricante("Chevrolet");
		veiculos.setAnoModelo(new Date(2019));
		veiculos.setAnoFabricacao(new Date(2017));
		veiculos.setValor(new BigDecimal(12500.00));
		veiculos.setTipoCombustivel(TipoCombustivel.GASOLINA);
		veiculos.setEspecificacoes(sb.toString());
		veiculos.setFoto(foto);
		veiculos.setProprietario(p);

		manager.persist(veiculos);
		transaction.commit();
		manager.close();
		JpaUtil.closeEntityManager();

	}

	@Test
	@Ignore
	public void buscarVeiculos() {
		EntityManager manager = JpaUtil.getManagerFactory();
		VeiculoId id = new VeiculoId("SEX-6969", "Aquidauna");
		Veiculos veiculos = manager.find(Veiculos.class, id);
		if (id != null) {
			if (veiculos.getEspecificacoes() != null) {
				System.out.println(veiculos.getModelo());
				System.out.println(veiculos.getFabricante());
				System.out.println(veiculos.getAnoFabricacao());
				System.out.println(veiculos.getAnoModelo());

				if (veiculos.getFoto() != null) {
					try {
						BufferedImage img = ImageIO.read(new ByteArrayInputStream(veiculos.getFoto()));
						JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(img)));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Veiculo não possui foto");
				}
			}
			if (veiculos.getProprietario() != null) {
				System.out.println(veiculos.getProprietario().getNome() + "\n"
						+ veiculos.getProprietario().getTelefone() + "\n" + veiculos.getProprietario().getEmail());
			}

			System.out.println(veiculos.getValor());
			System.out.println(veiculos.getEspecificacoes());
		}
		manager.close();
		JpaUtil.closeEntityManager();
	}
}
