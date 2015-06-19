package AermodProcess;

import java.util.List;
import java.util.ArrayList;
import App.ThreadApp;

public class AermodProgressUpdater implements ProgressUpdater, Runnable {
	private List<Updateable> mUpdateableList = new ArrayList<Updateable>();
	private Thread updateThread;
	private Boolean keepUpdating = true;
	private int mUpdateInterval = 1000;
	private ThreadApp mCallbacks = null;
	
	public AermodProgressUpdater() {
		
	}

	public AermodProgressUpdater(int updateInterval) {
		this.mUpdateInterval = updateInterval;
	}

	public void setThreadApp(ThreadApp threadApp) {
		this.mCallbacks = threadApp;
	}

	public void addUpdateable(Updateable updateable) {
		mUpdateableList.add(updateable);
	}

	public void start() {
		updateThread = new Thread(this);
		updateThread.start();
	}

	public void stop() {
		keepUpdating = false;
		updateThread.interrupt();
	}

	public void showProgress() {
		System.out.println("\n\n");
		for(Updateable updateable : mUpdateableList) {
			updateable.sayProgress();
		}
	}

	public void run() {
		while(keepUpdating) {
			showProgress();

			if(allThreadsFinished()) {
				this.stop();
				keepUpdating = false;
				System.out.println("All threads are complete!");

				mCallbacks.onAllThreadsFinished();
				return;
			}

			try {
				Thread.sleep(this.mUpdateInterval);
			} catch (InterruptedException e) {
				System.out.println(e.toString());
				this.stop();
			}
			
		}
	}

	private boolean allThreadsFinished() {
		for(Updateable updateable : mUpdateableList) {
			if(!updateable.isFinished()) {
				return false;
			}
		}

		return true;
	}
}