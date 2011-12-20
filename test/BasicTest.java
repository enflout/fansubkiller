import org.junit.*;
import play.test.*;

public class BasicTest extends UnitTest {
  @Before
  public void setUp() {
    Fixtures.deleteAll();
  }

  @Test
  public void fullTest() {
    Fixtures.load("data.yml");
  }
}
