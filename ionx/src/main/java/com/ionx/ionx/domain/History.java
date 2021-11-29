package com.ionx.ionx.domain;


import javax.persistence.*;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



@Entity
@Table(name = "historys")
public class History {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	 
	 	 
	    @NotBlank
	    @Size(min = 2, max = 255)
	    @Column(nullable = false, length = 255)
	    public String descricaoHistory;
	    
	    @CreationTimestamp
	    
	    private LocalDateTime createDateTime;
	 
	    @UpdateTimestamp
	    
	    private LocalDateTime updateDateTime;
	    
	    @ManyToOne
	    @JoinColumn(name = "id_prospect_fk")
	    private Prospect prospect;
	    
	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }
	    
	    public String getDescricaoHistory() {
	        return descricaoHistory;
	    }

	    public void setDescricaoHistory(String descricaoHistory) {
	        this.descricaoHistory = descricaoHistory;
	    }


	    public Prospect getProspect() {
	        return prospect;
	    }

	    public void setProspect(Prospect prospect) {
	        this.prospect = prospect;
	    }


}
