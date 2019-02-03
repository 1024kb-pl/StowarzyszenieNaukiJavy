package com.mac.bry.simpleTodo.entity.comparators;

import java.util.Comparator;

import com.mac.bry.simpleTodo.entity.Task;

public class TaskComparatorByStatus implements Comparator<Task> {

	@Override
	public int compare(Task task0, Task task1) {
		if(task0.isTaskStatus() && !task1.isTaskStatus()) {
			return -1;
		}
		if(!task0.isTaskStatus() && task1.isTaskStatus()) {
			return 1;
		}
		return 0;
	}

}
