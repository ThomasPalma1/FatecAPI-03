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
@Table(name = "produto")
public class Produto {
	//Gerando o ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//Não deve ser null e nem vazio, caractere > 2 e < 160
	@NotBlank
	@Size(min=2, max= 160)
	@Column(nullable = false, length = 160)
	private String nome;
	
	private String photo;

	//Não deve estar vazio, não deve ser null
	@NotBlank
	@Size(min=2, max= 255)
	@Column(nullable = false, length = 255)
    private String descricao;

	@OneToMany(mappedBy = "prospect", cascade = CascadeType.ALL)

	
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
    