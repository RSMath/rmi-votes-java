package tests.implementacoes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import implementacoes.CandidatoImpl;

public class CandidatoImplTest {
  private CandidatoImpl candidate;

  @Before
  public void setup() throws RemoteException {
    candidate = new CandidatoImpl(1, "fulano");
  }

  @Test
  public void testNewCandidatoName() {
    assertEquals("fulano", candidate.getNome());
  }

  @Test
  public void testNewCandidatoNumber() {
    assertEquals(1, candidate.getNumero());
  }
}
