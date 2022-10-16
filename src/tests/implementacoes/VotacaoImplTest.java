package tests.implementacoes;

import org.junit.Before;
import org.junit.Test;

import implementacoes.CandidatoImpl;
import implementacoes.VotacaoImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class VotacaoImplTest {
  private VotacaoImpl voteImpl;
  private List<CandidatoImpl> listOfCandidates;

  @Before
  public void setup() throws RemoteException {
    List<CandidatoImpl> candidates = new ArrayList<>();

    CandidatoImpl fulano   = new CandidatoImpl(11, "fulano");
    CandidatoImpl beltrano = new CandidatoImpl(33, "beltrano");

    candidates.add(fulano);
    candidates.add(beltrano);
    listOfCandidates = candidates;

    voteImpl = new VotacaoImpl(listOfCandidates);
  }

  @Test
  public void shouldSaveVoteAndReturnPosition() throws RemoteException {
    assertEquals(0, voteImpl.salvarVoto(0));
    assertEquals(1, voteImpl.salvarVoto(1));
  }

  @Test
  public void shouldMatchCandidatesList() throws RemoteException {
    assertNotEquals(voteImpl.listarCandidatos(), new ArrayList<>());
    assertEquals(voteImpl.listarCandidatos(), listOfCandidates);
  }

  @Test
  public void shouldSearchAndReturnPositionCandidate1() throws RemoteException {
    assertEquals(0, voteImpl.buscarCandidato("11"));
  }

  @Test
  public void shouldSearchAndReturnPositionCandidate2() throws RemoteException {
    assertEquals(1, voteImpl.buscarCandidato("33"));
  }

  @Test
  public void shouldSearchAndReturnMinusOneToNonExistentCandidate() throws RemoteException {
    assertEquals(-1, voteImpl.buscarCandidato("1111"));
  }
}
