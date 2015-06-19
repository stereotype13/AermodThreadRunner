package AermodProcess;

import java.util.List;
import java.util.ArrayList;



public class AermodThreadBus implements ThreadBus, AermodCallback {
	public static int ThreadCount = 0;
	public static int ThreadsRunning = 0;
	public static int ThreadsCompleted = 0;
	
	private List<Runnable> mThreadList = new ArrayList<Runnable>();

	public AermodThreadBus() {

	}

	public void addThread(Runnable process) {
		mThreadList.add(process);
		++ThreadCount;
	}

	public void runAllThreads() {
		for (Runnable runnable : mThreadList ) {
			Thread t = new Thread(runnable);
			t.start();
			++ThreadsRunning;
		}
		System.out.println("Total thread count: " + ThreadCount);
	}

	public void onThreadComplete(Runnable thread, String message) {
		--ThreadsRunning;
		++ThreadsCompleted;
		System.out.println("(" + message + ") " + ThreadsCompleted + " of " + ThreadCount + " aermod thread(s) completed.");
	}

	public void onThreadUpdate(Runnable thread, String message) {
		//Don't need to do anything
	}
}