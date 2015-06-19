package AermodProcess;

public interface ThreadBus {
	public void addThread(Runnable process);
	public void runAllThreads();
}