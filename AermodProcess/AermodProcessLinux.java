package AermodProcess;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class AermodProcessLinux implements AermodProcess, Runnable, Updateable {

	private AermodCallback mAermodCallback;
	private String mProcessDirectory;
	private float mProgress = 0;
	private int mTotalIterations = 1;
	private boolean mProcessFinished = false;

	public AermodProcessLinux(AermodCallback aermodCallback, String processDirectory) {
		this.mAermodCallback = aermodCallback;
		this.mProcessDirectory = processDirectory;
	}

	public AermodProcessLinux(AermodCallback aermodCallback, String processDirectory, int iterations) {
		this.mAermodCallback = aermodCallback;
		this.mProcessDirectory = processDirectory;
		this.mTotalIterations = iterations;
	}

	public void sayProgress() {
		System.out.println(mProcessDirectory.split("/")[1] + " is " + this.mProgress + "% complete.");
	}

	public boolean isFinished() {
		return this.mProcessFinished;
	}

	public void runProcess() {
		System.out.println(mProcessDirectory.split("/")[1] + " is running!");
		Process aermodProcess;
		String output;
		String[] cmdarray = {"./aermod"};
		try {
			aermodProcess = Runtime.getRuntime().exec(cmdarray, null, new File(mProcessDirectory));

			//We need to do this to clear the buffer of input stream from aermod.
			//aermod keeps printing "Day 1 of 365, etc., etc."
			//If we don't clear the input stream, this may lead to subprocess blocking.
			BufferedReader reader = new BufferedReader(new InputStreamReader(aermodProcess.getInputStream()));
			int updateCount = 0;
			while((output = reader.readLine()) != null) { 
				//System.out.println(mProcessDirectory.split("/")[1] + " output: " + output);
				++updateCount;
				//mAermodCallback.onThreadUpdate(mProcessDirectory.split("/")[1] + " " + (updateCount/10000.0f)*100 + "%");
				this.mProgress = ((float)updateCount/mTotalIterations)*100;
			}

			//No we can wait without aermod being blocked.
			aermodProcess.waitFor();
			System.out.println("Exit value for " + mProcessDirectory.split("/")[1] + ": " + aermodProcess.exitValue());
			aermodProcess.destroy();
			mAermodCallback.onThreadComplete(this, mProcessDirectory.split("/")[1]);
			mProcessFinished = true;
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}


	public void run() {
		runProcess();
	}
}