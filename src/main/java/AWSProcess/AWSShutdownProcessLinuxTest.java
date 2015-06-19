package AWSProcess;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class AWSShutdownProcessLinuxTest implements AWSShutdownProcess, Runnable {
	private Thread mAWSShutdownThread;
	public AWSShutdownProcessLinuxTest() {}

	public void shutdown() {
		mAWSShutdownThread = new Thread((Runnable)this);
		mAWSShutdownThread.start();
	}

	public void run() {
		
		Process awsProcess;
		String output;
		//String[] cmdarray = {"aws ec2 stop-instances --instance-ids $(ec2metadata --instance-id)"};
		//String[] cmdarray = {"./aws"};
		try {

			awsProcess = Runtime.getRuntime().exec("./aws");

			//We need to do this to clear the buffer of input stream 
			BufferedReader reader = new BufferedReader(new InputStreamReader(awsProcess.getInputStream()));
		
			while((output = reader.readLine()) != null) { 
				System.out.println(output);
				
			}

			//No we can wait without aermod being blocked.
			awsProcess.waitFor();
			System.out.println("Exit value for "  + awsProcess.exitValue());
			awsProcess.destroy();
						
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}