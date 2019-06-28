package com.mac.bry.simpleTodo.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TASKS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task   {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="taskID")
	private int taskID;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="userID", nullable=false)
	private User user;
	
	@Column(name="taskName")
	private String taskName;
	
	@Column(name="taskDescription")
	private String taskDescription;
	
	@Column(name="taskStatus")
	private boolean taskStatus;
	
	@Column(name="dateOfCompletion")
	private LocalDate dateOfCompletion;


	public Task(int taskID, String taskName, String taskDescription, boolean taskStatus,
			LocalDate dateOfCompletion) {
		super();
		this.taskID = taskID;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskStatus = taskStatus;
		this.dateOfCompletion = dateOfCompletion;
	}
	
	public Task(String taskName, String taskDescription, boolean taskStatus, LocalDate dateOfCompletion) {
		super();
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskStatus = taskStatus;
		this.dateOfCompletion = dateOfCompletion;
	}
	
	

	
}
