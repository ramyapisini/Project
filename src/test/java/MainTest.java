import com.sun.tools.javac.Main;
import org.Demo.Ledger;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
 
public class MainTest {

    @Test
    void testLedger(){
        Ledger ledger = new Ledger();
        boolean value = ledger.evaluateLedger();
        assertEquals(value,Boolean.TRUE);
    }
}