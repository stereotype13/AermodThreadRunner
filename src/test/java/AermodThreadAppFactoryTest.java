import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.*;

import App.*;

public class AermodThreadAppFactoryTest {

    @Test public void testPRODUCTION() {
        ThreadAppFactory factory = new AermodThreadAppFactory();
        ThreadApp app = factory.createThreadApp(ThreadAppFactory.ThreadAppType.PRODUCTION);
        assertThat(app, instanceOf(AermodThreadAppLinux.class));
    }
    
}