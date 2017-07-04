package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable //Será usada de forma embutida em outra classe
public class VeiculoId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="placa_veiculo",length=8,nullable=false)
	private String placa;
	
	@Column(name="cidade_placa_veiculo",length=50,nullable=false)
	private String cidade;

	public VeiculoId(){}

	public VeiculoId(String placa, String cidade) {
		super();
		this.placa = placa;
		this.cidade = cidade;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	
}
