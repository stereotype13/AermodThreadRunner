package AermodProcess;

import App.ThreadApp;

public interface ProgressUpdater {
	void setThreadApp(ThreadApp threadApp);
	void addUpdateable(Updateable updateable);
	void start();
	void stop();
	void showProgress();
}