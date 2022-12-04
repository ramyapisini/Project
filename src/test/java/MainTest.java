import com.sun.tools.javac.Main;
import org.Demo.Ledger;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
 
public class MainTest {

    @Mock
    Ledger ledger;
    @Test
    void testLedger(){
        boolean value = ledger.evaluateLedger();
        assertEquals(value,Boolean.TRUE);
    }
}