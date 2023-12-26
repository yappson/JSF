package com.emp.manage.enitity;



import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "Qualifacation")
public class Qualification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="institute")
    private String institute;
	
	@Column(name="degree")
    private String degree;
	
	@Column(name="specialization")
    private String specialization;
	
	
	@Column(name="fromPeriod")
    private LocalDate fromPeriod; 
	
	
	@Column(name="toPeriod")
    private LocalDate toPeriod;
	
	@Column(name="percentage")
    private Double percentage; 

	 
	    @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
	    @JoinColumn(name = "employeeId")
	    private Employee employee;
	

}
