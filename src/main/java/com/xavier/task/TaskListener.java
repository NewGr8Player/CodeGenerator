package com.xavier.task;

public interface TaskListener {
	/**
	 * Called when the observed task has finished.
	 */
	void taskFinished();

	/**
	 * Called when the observed task reports its status.
	 */
	void taskStatus(Object obj);

	/**
	 * Called when the observed task reports its result.
	 */
	void taskResult(Object obj);

	/**
	 * Called when the observed task throws an exception.
	 */
	void taskError(Exception e);
}
