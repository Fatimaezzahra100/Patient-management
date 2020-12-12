package org.side.patients.entites;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@NotNull
	@Size(min=1, max=20)
	private String name;
	
	/*JPA POUR QUE 9A SOIT DATE DANS LA DB*/
	/*SPRING a besoin de savoir quel est le format libaystoki biha 
	 * dak string lijay men requette http*/
	@Temporal(TemporalType.DATE)
	/*dateformat ça concerne l'interface graphique et pas la db ou plutot spring il 
	 * utilise date format pour savoir dans quelle format va stocker la date venu du coté client*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@javax.validation.constraints.NotNull
	private Date dateNaissance;
	private boolean malade;
	
	public Patient(String name, Date dateNaissance, boolean malade) {
		super();
		this.name = name;
		this.dateNaissance = dateNaissance;
		this.malade = malade;
	}
	
	
	
	

	
}
