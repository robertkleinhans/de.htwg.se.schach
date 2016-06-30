
import org.junit.Test;


import static org.junit.Assert.*;


import de.htwg.se.schach.PlayerHandler;
import de.htwg.se.schach.control.Position;
import de.htwg.se.schach.view.Signal;

public class PlayerHandlerTest {
	
	@Test
	public void testHandleInput() {
		PlayerHandler pl = new PlayerHandler();
		
		assertFalse(pl.handleInput("show A7", 1));
		assertFalse(pl.handleInput("show A2", 0));
		
		assertFalse(pl.handleInput("test", 1));
		assertFalse(pl.handleInput("test-test", 1));
		
		assertFalse(pl.handleInput("test-te", 1));
		assertFalse(pl.handleInput("te-test", 1));
		assertFalse(pl.handleInput("te-te", 1));
		
		
		assertFalse(pl.handleInput("A9-B9", 1));
		assertFalse(pl.handleInput("A6-B9", 1));
		
		assertFalse(pl.handleInput("A2-I9", 0));
		assertFalse(pl.handleInput("A2-B6", 0));
		assertFalse(pl.handleInput("A2-B9", 0));
		
		assertTrue(pl.handleInput("quit", 1));
		
	}
	
	@Test
	public void testCheckValue() {
		PlayerHandler pl = new PlayerHandler();
		assertTrue(pl.checkValue(3));
		assertFalse(pl.checkValue(-1));
		assertFalse(pl.checkValue(10));
	}
	
	@Test
	public void testHandleShow() {
		PlayerHandler pl = new PlayerHandler();
		pl.handleShow("test");
		pl.handleShow("show A2");
		pl.handleShow("show N2");
		pl.handleShow("show A9");
	}
	
	@Test
	public void testHandleSignal() {
		Signal sig = new Signal();
		PlayerHandler pl = new PlayerHandler();
		sig.setInput("quit");
		pl.gameHandler(sig);
	}
}
