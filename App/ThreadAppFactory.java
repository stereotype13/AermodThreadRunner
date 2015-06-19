package App;

public interface ThreadAppFactory {
	static enum ThreadAppType {TEST_LINUX, TEST_WINDOWS, PRODUCTION};
	ThreadApp createThreadApp(ThreadAppType type);
}