package com.mac.bry.simpleTodo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userID")
	private int userID;
	
	@Column(name="login")
	private String login;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String emailAdress;
	
	@OneToMany(mappedBy="user",cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	private List <Task> tasksList;

	public User() {
		super();
	}

	public User( String login, String password,List <Task> tasksList, String emailAdress) {
		super();
		this.login = login;
		this.password = password;
		this.tasksList = tasksList;
		this.emailAdress = emailAdress;
	}

	public User(String login, String password, String emailAdress) {
		super();
		this.login = login;
		this.password = password;
		this.emailAdress = emailAdress;
	}
	

	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public int getId() {
		return userID;
	}

	public void setId(int id) {
		this.userID = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Task> getTasksList() {
		return tasksList;
	}

	public void setTasks(List<Task> tempTasks) {
		this.tasksList = tempTasks;
	}
	
	public void add (Task tempTask) {
		if (tasksList == null) {
			tasksList = new ArrayList<>();
		}
		
		tempTask.setUser(this);
		tasksList.add(tempTask);
		
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", login=" + login + ", password=" + password + ", emailAdress=" + emailAdress
				+ ", tasksList=" + tasksList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAdress == null) ? 0 : emailAdress.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((tasksList == null) ? 0 : tasksList.hashCode());
		result = prime * result + userID;
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
		User other = (User) obj;
		if (emailAdress == null) {
			if (other.emailAdress != null)
				return false;
		} else if (!emailAdress.equals(other.emailAdress))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (tasksList == null) {
			if (other.tasksList != null)
				return false;
		} else if (!tasksList.equals(other.tasksList))
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}

}