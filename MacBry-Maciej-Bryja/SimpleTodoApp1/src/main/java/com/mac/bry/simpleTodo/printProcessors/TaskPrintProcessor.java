package com.mac.bry.simpleTodo.printProcessors;

import java.util.List;

import com.mac.bry.simpleTodo.entity.Task;

public class TaskPrintProcessor {

	private static void printHorizontalBorder() {
		for (int i = 0; i < 170; i++) {
			System.out.print("=");
		}
		System.out.println();
	}

	private static void printColumnName() {
		System.out.printf("|| %-10s || %-10s || %-10s || %-20s || %-50s || %-20s || %-20s ||", "LP", "TASK ID",
				"USER ID", "TASK NAME", "TASK DESCRIPTION", "DATE OD COMPLETION", "TASK STATUS");
		System.out.println();
	}

	public static void printTasks(List<Task> tasks) {
		printHorizontalBorder();
		printColumnName();
		printHorizontalBorder();

		int lp = 1;

		for (Task task : tasks) {
			System.out.printf("|| %-10d || %-10d || %-10d || %-20s || %-50s || %-20tD || %-20s ||", lp,
					task.getTaskID(), task.getUser().getId(), task.getTaskName(), task.getTaskDescription(),
					task.getDateOfCompletion(), task.isTaskStatus());
			System.out.println();
		}
		printHorizontalBorder();
	}
}
