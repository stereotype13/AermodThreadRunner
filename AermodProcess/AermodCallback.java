package AermodProcess;

public interface AermodCallback {
	void onThreadComplete(Runnable thread, String message);
	void onThreadUpdate(Runnable thread, String message);
}