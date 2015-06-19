package App;

import AermodProcess.ProgressUpdater;

public interface ThreadApp {
	void setProgressUpdater(ProgressUpdater progressUpdater);
	void execute();
	void onAllThreadsFinished();
}