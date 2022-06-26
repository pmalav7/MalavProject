import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import milestone.Items;
import org.junit.Test;

/**
 * This class tests the Item class .
 */
public class ItemsTest {


  @Test(expected = IllegalArgumentException.class)
  public void locationIsNegative() {
    Items i = new Items(-10, 3, "Hammer");
  }

  @Test(expected = IllegalArgumentException.class)
  public void damageIsNegative() {
    Items i = new Items(10, -3, "Hammer");
  }

  @Test(expected = IllegalArgumentException.class)
  public void locationMoreThanAvailableSpaces() {
    Items i = new Items(30, -3, "Hammer");
  }

  @Test(expected = IllegalArgumentException.class)
  public void damageIsZero() {
    Items i = new Items(10, 0, "Gun");
  }

  @Test
  public void getDamage() {
    Items i = new Items(10, 23, "Hammer");
    assertEquals(23, i.getDamage());
  }

  @Test
  public void getName() {
    Items i = new Items(10, 23, "Hammer");
    assertEquals("Hammer", i.getName());
  }

  @Test
  public void getLocation() {
    Items i = new Items(10, 23, "Hammer");
    assertEquals(10, i.getLocation());
  }

  @Test
  public void testEquals() {
    Items ri = new Items(10, 23, "Gun");
    assertTrue(ri.equals(ri)); //Reflexivity
    Items ri1 = new Items(10, 23, "Gun");
    assertTrue(ri.equals(ri1)); //Symmetry
    Items ri2 = ri1;
    assertTrue(ri2.equals(ri)); //Transitivity
    Items ri3 = new Items(10, 25, "Gun");
    assertFalse(ri3.equals(ri1));
  }

  @Test
  public void testHashCode() {
    Items sr = new Items(10, 23, "Gun");
    assertTrue(sr.hashCode() == sr.hashCode()); //Reflexivity
    Items sr1 = new Items(10, 23, "Gun");
    assertTrue(sr.hashCode() == sr1.hashCode()); //Symmetry
    Items sr2 = sr1;
    assertTrue(sr2.hashCode() == sr.hashCode()); //Transitivity
  }
}