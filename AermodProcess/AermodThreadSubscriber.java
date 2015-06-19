package AermodProcess;

public class AermodThreadSubscriber implements AermodCallback {

	public void onThreadComplete(Runnable thread, String message) {
		System.out.println(message);
	}

	public void onThreadUpdate(Runnable thread, String message) {

	}
}