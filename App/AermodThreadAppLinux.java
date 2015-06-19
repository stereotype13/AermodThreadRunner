package App;

import java.util.List;
import java.util.ArrayList;
import AermodProcess.*;
import AWSProcess.AWSShutdownProcess;

public class AermodThreadAppLinux implements ThreadApp {
	private int mUpdateInterval;

	//5 years * 365 days
	private int mThreadIterations;

	//A list of directories we don't want to parse to find 'aermod' executable
	private List<String> mDirectoriesToIgnore = new ArrayList<String>();
	private AWSShutdownProcess mAWSShutdownProcess;
	private ThreadBus mBus;
	private ProgressUpdater mProgressUpdater;
	private DirectoryFinder mDirectoryFinder;

	public AermodThreadAppLinux(int threadIterations, DirectoryFinder directoryFinder, ThreadBus threadBus, AWSShutdownProcess awsShutdownProcess) {
		this.mThreadIterations = threadIterations;
		this.mDirectoryFinder = directoryFinder;
		this.mBus = threadBus;
		this.mAWSShutdownProcess = awsShutdownProcess;
	}

	public void setProgressUpdater(ProgressUpdater progressUpdater) {
		this.mProgressUpdater = progressUpdater;
	}

	public void execute() {
		
		//List of directories to parse
		List<String> directoryList = mDirectoryFinder.getDirectories();
		
		for(String directory : directoryList) {
			System.out.println(directory.split("/")[1]);
			AermodProcess aermodThread = new AermodProcessLinux((AermodCallback)mBus, directory, mThreadIterations);
			mBus.addThread((Runnable)aermodThread);	
			mProgressUpdater.addUpdateable((Updateable)aermodThread);
		}

		mBus.runAllThreads();
		mProgressUpdater.start();
	}

	public void onAllThreadsFinished() {
		mAWSShutdownProcess.shutdown();
	}
}