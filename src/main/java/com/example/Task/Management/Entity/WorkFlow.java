package com.example.Task.Management.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="workFlows")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkFlow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@OneToMany(mappedBy="workflow",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<WorkFlowTransaction>transactions=new ArrayList<>();

	public List<WorkFlowTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<WorkFlowTransaction> transactions) {
		this.transactions = transactions;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
