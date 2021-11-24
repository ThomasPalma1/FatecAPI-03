package com.ionx.ionx.domain;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "prospect")
public class Prospect {
	//Gerando o ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//Não deve ser null e nem vazio, caractere > 2 e < 160
	@Size(min=2, max= 160)
	@Column(nullable = false, length = 160)
	private String nome;
	
	private String empresa;

	private String cargo;
	
    private String bairro;
    
    private String numeroCasa;
	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

    private String cep;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@Size(min=7, max= 20)
	@Column(nullable = false, length = 20)
    private String telefone;

	@Size(min=2, max= 60)
	@Column(nullable = false, length = 60)
    private String email;
	
	@Size(min=2, max= 60)
	@Column(nullable = false, length = 160)
    private String estado;

	@Size(min=2, max= 160)
	@Column(nullable = false, length = 160)
    private String cidade;
	
	private LocalTime hremail;
	
	private LocalTime hrtel;
	
    private String endereco;
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Size(min=11, max= 20)
    private String cnpj;
	
	
	@Size(min=2, max= 160)
	@Column(nullable = false, length = 160)
	private String produtEscolhido;
	
	@Size(min=2, max=20)
	@Column(nullable = false, length = 160)
	private String level;
	
	@Transient
	private MultipartFile file;
	
	@Column(name="file_type")
	private String fileType;
	 @OneToOne
		@JoinColumn(name = "status", referencedColumnName = "id")
		private Status status;
	
	public Prospect() {}
	 
	 public Prospect(String nome, String empresa, String cargo,String bairro, String cep, String telefone, String email, String estado, String cidade, 
			String endereco,String cnpj,String numeroCasa, String produtEscolhido, String level, String fileType) {
		 super();
		 this.nome = nome;
		 this.empresa = empresa;
		 this.cargo = cargo;
		 this.bairro = bairro;
		 this.cep = cep;
		 this.telefone = telefone;
		 this.email = email;
		 this.estado = estado;
		 this.cidade = cidade;
		 this.endereco = endereco;
		 this.cnpj = cnpj;
		 this.numeroCasa = numeroCasa;
		 this.produtEscolhido = produtEscolhido;
		 this.level = level;
		 this.fileType = fileType;
	 }
	
	
	
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
	 private List<History> historys;

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
    
    
    public LocalTime getHremail() {
        return hremail;
    }

    public void setHremail(LocalTime hremail) {
        this.hremail = hremail;
    }
    
    public LocalTime getHrtel() {
        return hrtel;
    }

    public void setHrtel(LocalTime hrtel) {
        this.hrtel = hrtel;
    }
    

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<History> getHistorys() {
        return historys;
    }

    public void setHistorys(List<History> historys) {
        this.historys = historys;
    }

	public String getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}
}
	
