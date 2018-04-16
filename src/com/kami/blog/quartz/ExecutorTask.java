package com.kami.blog.quartz;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class ExecutorTask {
	private static final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(16);
	private static Executor EXECUTOR = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, queue);
	
	public static void addTask(Runnable runnable) {
		queue.add(runnable);
	}
	
	public void startTask() {
		while(queue.peek() != null) {
			EXECUTOR.execute(queue.poll());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
