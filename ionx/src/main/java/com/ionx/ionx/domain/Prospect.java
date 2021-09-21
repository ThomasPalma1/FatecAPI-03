package com.ionx.ionx.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "prospect")
public class Prospect {
	//Gerando o ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//Não deve ser null e nem vazio, caractere > 2 e < 160
	@NotBlank
	@Size(min=2, max= 160)
	@Column(nullable = false, length = 160)
	private String nome;
	
	//Não deve estar vazio, não deve ser null
	@NotBlank
    @Column(nullable = false)
    private String empresa;
	
	@NotBlank
    @Column(nullable = false)
    private String cargo;

	@NotBlank
	@Size(min=7, max= 20)
	@Column(nullable = false, length = 20)
    private String telefone;
	
	@NotBlank
	@Size(min=2, max= 60)
	@Column(nullable = false, length = 60)
    private String email;
	
	@NotBlank
	@Size(min=2, max= 60)
	@Column(nullable = false, length = 160)
    private String estado;
	
	@NotBlank
	@Size(min=2, max= 160)
	@Column(nullable = false, length = 160)
    private String cidade;
	
	@NotBlank
    @Column(nullable = false)
    private String endereco;
	

	
    @Column(nullable = false)
    private String numero;
    
	@NotBlank
	@Size(min=11, max= 14)
    @Column(nullable = false)
    private String cnpj;
	
	
	@NotBlank
	@Size(min=2, max= 160)
	@Column(nullable = false, length = 160)
	private String produtEscolhido;
	
	@NotBlank
	@Size(min=2, max=20)
	@Column(nullable = false, length = 160)
	private String level;
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getProdutEscolhido() {
		return produtEscolhido;
	}

	public void setProdutEscolhido(String produtEscolhido) {
		this.produtEscolhido = produtEscolhido;
	}
	
	@OneToMany(mappedBy = "prospect", cascade = CascadeType.ALL)

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
	
