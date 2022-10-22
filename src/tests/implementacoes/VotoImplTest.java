package tests.implementacoes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.rmi.RemoteException;
import implementacoes.CandidatoImpl;
import implementacoes.VotoImpl;

public class VotoImplTest {
  private VotoImpl vote1, vote2;
  private CandidatoImpl candidate1, candidate2;

  @Before
  public void setup() throws RemoteException {
    candidate1 = new CandidatoImpl(1, "fulano");
    candidate2 = new CandidatoImpl(2, "beltrano");

    vote1 = new VotoImpl("ip_identificador", candidate1);
    vote2 = new VotoImpl("ip_identificador2", candidate2);
  }

  @Test
  public void testGetCandidato1() {
    assertEquals(candidate1, vote1.getCandidato());
    assertNotEquals(candidate2, vote1.getCandidato());
  }

  @Test
  public void testGetCandidato2() {
    assertEquals(candidate2, vote2.getCandidato());
    assertNotEquals(candidate1, vote2.getCandidato());
  }

  @Test
  public void testGetIdentificator1() {
    assertEquals("ip_identificador", vote1.getIdentificador());
    assertNotEquals("ip_identificador2", vote1.getIdentificador());
  }

  @Test
  public void testGetIdentificator2() {
    assertEquals("ip_identificador2", vote2.getIdentificador());
    assertNotEquals("ip_identificador", vote2.getIdentificador());
  }
}
