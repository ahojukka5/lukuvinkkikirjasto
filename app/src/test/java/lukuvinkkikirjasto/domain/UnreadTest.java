package lukuvinkkikirjasto.domain.matcher;

import static org.junit.Assert.*;

import java.util.*;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.domain.matcher.*;
import org.junit.*;

public class UnreadTest {
	@Test
	public void matchesUnreadTip() {
		Tip tip = new Tip(0, "a", "b");

		Matcher match = new Unread();
		assertTrue(match.matches(tip));
	}

	@Test
	public void matchFailsOnUnread() {
		Tip tip = new Tip(0, "a", "b");
		tip.markRead();

		Matcher match = new Unread();
		assertFalse(match.matches(tip));
	}
}