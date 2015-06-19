//package AermodThreadRunner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import App.*;

public class AermodThreadRunner {
	
	public static void main(String[] args) {
		ThreadAppFactory threadAppFactory = new AermodThreadAppFactory();
		ThreadApp theApp = threadAppFactory.createThreadApp(ThreadAppFactory.ThreadAppType.PRODUCTION);
		theApp.execute();
	}
}