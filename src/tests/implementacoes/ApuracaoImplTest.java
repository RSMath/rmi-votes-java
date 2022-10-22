package tests.implementacoes;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;

import org.junit.Test;

import implementacoes.ApuracaoImpl;

public class ApuracaoImplTest {
  @Test
  public void shouldReturn100Percent () throws RemoteException {
    assertEquals("100.00%", new ApuracaoImpl().calcularVotos(1, 1));
    assertEquals("100.00%", new ApuracaoImpl().calcularVotos(2, 2));
    assertEquals("100.00%", new ApuracaoImpl().calcularVotos(5, 5));
    // ...
    // ...
    // ...
    assertEquals("100.00%", new ApuracaoImpl().calcularVotos(100, 100));
  }

  @Test
  public void shouldReturn50Percent() throws RemoteException {
    assertEquals("50.00%", new ApuracaoImpl().calcularVotos(2, 1));
    assertEquals("50.00%", new ApuracaoImpl().calcularVotos(4, 2));
    assertEquals("50.00%", new ApuracaoImpl().calcularVotos(10, 5));
    // ...
    // ...
    // ...
    assertEquals("50.00%", new ApuracaoImpl().calcularVotos(200, 100));
  }

  @Test
  public void shouldReturn33Percent() throws RemoteException {
    assertEquals("33.00%", new ApuracaoImpl().calcularVotos(3, 1));
    assertEquals("33.00%", new ApuracaoImpl().calcularVotos(6, 2));
    assertEquals("33.00%", new ApuracaoImpl().calcularVotos(15, 5));
    // ...
    // ...
    // ...
    assertEquals("33.00%", new ApuracaoImpl().calcularVotos(300, 100));
  }
}

