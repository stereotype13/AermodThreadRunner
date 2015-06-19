import AermodProcess.AermodDirectoryFinder;
import java.util.List;
import java.util.ArrayList;

public class AermodDirectoryFinderTest {
	
	public static void main(String[] args) {
		List<String> ignore = new ArrayList<String>();
		ignore.add("AWSProcess");
		ignore.add("App");
		ignore.add("AermodProcess");
		AermodDirectoryFinder df = new AermodDirectoryFinder(ignore);
	}
}