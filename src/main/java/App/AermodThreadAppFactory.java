package App;

import java.util.List;
import java.util.ArrayList;
import AermodProcess.*;
import AWSProcess.*;

public class AermodThreadAppFactory implements ThreadAppFactory {
	private ThreadApp mThreadApp;
	private ThreadAppType mAppType;

	private AWSShutdownProcess mAWSShutdownProcess;
	private ThreadBus mBus;
	private ProgressUpdater mProgressUpdater;
	private DirectoryFinder mDirectoryFinder;

	public AermodThreadAppFactory() {
		
	}

	public ThreadApp createThreadApp(ThreadAppType type) {
		mAppType = type;
		List<String> directoriesToIgnore = new ArrayList<String>();
		directoriesToIgnore.add("AermodProcess");
		directoriesToIgnore.add("App");
		directoriesToIgnore.add("AWSProcess");

		mBus = new AermodThreadBus();
		

		switch(mAppType) {
	
			case PRODUCTION:
				mAWSShutdownProcess = new AWSShutdownProcessLinux();
				mDirectoryFinder = new AermodDirectoryFinderLinux();
				mThreadApp = new AermodThreadAppLinux(1825, mDirectoryFinder, mBus, mAWSShutdownProcess);
				mProgressUpdater = new AermodProgressUpdater(60000);
				mProgressUpdater.setThreadApp(mThreadApp);
				mThreadApp.setProgressUpdater(mProgressUpdater);
			break;

			case TEST_WINDOWS:
				mAWSShutdownProcess = new AWSShutdownProcessWindows();
				mDirectoryFinder = new AermodDirectoryFinderWindows();
				mThreadApp = new AermodThreadAppWindows(10000, mDirectoryFinder, mBus, mAWSShutdownProcess);
				mProgressUpdater = new AermodProgressUpdater(1000);
				mProgressUpdater.setThreadApp(mThreadApp);
				mThreadApp.setProgressUpdater(mProgressUpdater);
			break;

			default:
			case TEST_LINUX:
				mAWSShutdownProcess = new AWSShutdownProcessLinuxTest();
				mDirectoryFinder = new AermodDirectoryFinderLinux();
				mThreadApp = new AermodThreadAppLinux(10000, mDirectoryFinder, mBus, mAWSShutdownProcess);
				mProgressUpdater = new AermodProgressUpdater(1000);
				mProgressUpdater.setThreadApp(mThreadApp);
				mThreadApp.setProgressUpdater(mProgressUpdater);
		}

		return mThreadApp;
	}
}