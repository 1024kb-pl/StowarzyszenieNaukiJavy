package com.mac.bry.simpleTodo.Enums;

import com.mac.bry.simpleTodo.Exception.TaskOptionNoExistException;

public enum TaskOption {
	ADD_TASK(0, "Add task"),
	SHOW_TASKS(1, "Show all tasks"),
	EDIT_TASK_NAME(2, "Edit task name"),
	EDIT_TASK_STATUS(3, "Edit task status"),
	DELET_TASK_BY_ID(4, "Delete task by ID"),
	SHOW_TASKS_BY_STATUS(5, "Show tasks by Status"),
	SHOW_TASKS_BY_DATA(6, "Show tasks by Data"),
	SORT_TASKS_BY_NAME(7, "Sort tasks by Name"),
	SORT_TASKS_BY_STATUS(8, "Sort tasks by Status"),
	SORT_TASKS_BY_DATA(9, "Sort Task by Data"),
	BACK(10, "Back");

	
	private int value;
	private String description;
	
	private TaskOption(int value, String description) {
		this.value = value;
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return value + " - " + description;
	}
	
	public static TaskOption getOptionByOrderNumber(int option) throws TaskOptionNoExistException {
		return TaskOption.values()[option];
	}
}
