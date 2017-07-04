package model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_veiculos")
public class Veiculos {
	//
	// @Id
	// @GeneratedValue(generator="inc")
	// @GenericGenerator(name="inc",strategy="increment")
	// @Column(name="cod_veiculo")
	// private Long codigo;

	@Column(name = "modelo_veiculo", length = 50, nullable = false)
	private String modelo;

	@Column(name = "fabricante_veiculo", length = 50, nullable = false)
	private String fabricante;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fabricacao_veiculo")
	private Date anoFabricacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ano_modelo_veiculo")
	private Date anoModelo;

	@Column(name = "valor_veiculo", precision = 7, scale = 2)
	private BigDecimal valor;

	@EmbeddedId				//referncia ao identificador placa e cidade VeiculoId
	private VeiculoId veiculoId;

	@Enumerated(EnumType.STRING)							//ORDINAL == num_id da sequencia
	@Column(name="veiculo_tipo_combustivel",nullable=false)
	private TipoCombustivel tipoCombustivel;
	
	@Lob
	@Column(name="especificacoes_veiculo")
	private String especificacoes;
	
	@Lob
	private byte[] foto;

	//@Embedded		//notacao para unir sem chaves as tabelas
	@OneToOne(optional=false)
	private Proprietario proprietario;
	
	
	public Veiculos() {
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Date getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(Date anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Date getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(Date anoModelo) {
		this.anoModelo = anoModelo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public VeiculoId getVeiculoId() {
		return veiculoId;
	}

	public void setVeiculoId(VeiculoId veiculoId) {
		this.veiculoId = veiculoId;
	}
	
	public TipoCombustivel getTipoCombustivel() {
		return tipoCombustivel;
	}
	
	public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}
	
	public String getEspecificacoes() {
		return especificacoes;
	}
	
	public void setEspecificacoes(String especificacoes) {
		this.especificacoes = especificacoes;
	}
	
	public byte[] getFoto() {
		return foto;
	}
	
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public Proprietario getProprietario() {
		return proprietario;
	}
	
	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}
	
	@Transient			//será iguinorado pelo objeto de persistência
	@Override
	public String toString() {
	return getModelo()+" "+getFabricante()+" "+getAnoFabricacao()+"\n"+getAnoModelo()+""
			+ " "+getTipoCombustivel()+" "+getValor();
			
	}
}
