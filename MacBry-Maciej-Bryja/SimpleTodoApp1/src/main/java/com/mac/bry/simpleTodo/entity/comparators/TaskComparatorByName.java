package com.mac.bry.simpleTodo.entity.comparators;

import java.util.Comparator;

import com.mac.bry.simpleTodo.entity.Task;

public class TaskComparatorByName implements Comparator<Task> {

	@Override
	public int compare(Task task0, Task task1) {
		return task0.getTaskName().compareTo(task1.getTaskName());
	}

}
