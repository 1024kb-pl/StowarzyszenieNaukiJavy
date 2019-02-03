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

@Entity
@Table(name="TASKS")
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

	public Task() {
		super();
	}

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

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public boolean isTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(boolean taskStatus) {
		this.taskStatus = taskStatus;
	}

	public LocalDate getDateOfCompletion() {
		return dateOfCompletion;
	}

	public void setDateOfCompletion(LocalDate dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Task [taskID=" + taskID + ", user=" + user + ", taskName=" + taskName + ", taskDescription="
				+ taskDescription + ", taskStatus=" + taskStatus + ", dateOfCompletion=" + dateOfCompletion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfCompletion == null) ? 0 : dateOfCompletion.hashCode());
		result = prime * result + ((taskDescription == null) ? 0 : taskDescription.hashCode());
		result = prime * result + taskID;
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
		result = prime * result + (taskStatus ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (dateOfCompletion == null) {
			if (other.dateOfCompletion != null)
				return false;
		} else if (!dateOfCompletion.equals(other.dateOfCompletion))
			return false;
		if (taskDescription == null) {
			if (other.taskDescription != null)
				return false;
		} else if (!taskDescription.equals(other.taskDescription))
			return false;
		if (taskID != other.taskID)
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		if (taskStatus != other.taskStatus)
			return false;
		return true;
	
	}
	
}
