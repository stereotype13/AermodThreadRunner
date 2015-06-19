package AermodProcess;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AermodDirectoryFinderWindows implements DirectoryFinder {

	private List<String> mIgnoredDirectoriesList = new ArrayList<String>();
	private List<String> mDirectoryList = new ArrayList<String>();

	public AermodDirectoryFinderWindows() {
		setup();
	}

	public AermodDirectoryFinderWindows(List<String> ignoredDirectoriesList) {
		this.mIgnoredDirectoriesList = ignoredDirectoriesList;
		System.out.println("Directories to ignore: ");
		for(String directory : mIgnoredDirectoriesList) {
			System.out.println(directory);
		}
		System.out.println("");
		setup();
	}

	private void setup() {
		String s;
		Process p;
		try {
			p = Runtime.getRuntime().exec("cmd /c dir /a:d /b");
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

			int directoryCount = 0;
			
			while((s = br.readLine()) != null) {
				boolean ignore = false;

				for(String ignoredDirectory : mIgnoredDirectoriesList) {
					
					if(ignoredDirectory.equals(s)) {
						ignore = true;
						break;
					}	
					
				}

				if(!ignore) {
					mDirectoryList.add(s);
					++directoryCount;				
				}
				
			}
			p.waitFor();
			System.out.println("There are " + directoryCount + " subdirectories to parse.");
			//System.out.println("exit: " + p.exitValue());
			p.destroy();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public List<String> getDirectories() {
		return this.mDirectoryList;
	}
} 