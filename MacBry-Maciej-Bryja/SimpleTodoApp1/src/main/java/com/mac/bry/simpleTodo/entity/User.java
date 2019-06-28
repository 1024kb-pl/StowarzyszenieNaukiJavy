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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="USERS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	
	public static final User DEFAULT_USER = new User("annonymous","password");
	
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
	
	@Column(name="permision")
	private boolean permision;
	
	@OneToMany(mappedBy="user",cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	private List <Task> tasksList;


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
		this.permision = false;
	}
	

	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	
	public void add (Task tempTask) {
		if (tasksList == null) {
			tasksList = new ArrayList<Task>();
		}

		tempTask.setUser(this);
		tasksList.add(tempTask);

	}
}