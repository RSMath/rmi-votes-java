# rmi-votes-java
# Sistema de Arquivos Distribuídos
Projeto de implementação RMI de um Sistema de Votação para a matéria de Sistemas Distribuídos 2022.2 TADS IV

# RMI
Java RMI é um mecanismo para permitir a invocação de métodos que residem em diferentes máquinas virtuais Java (JVM). O JVM pode estar em diferentes máquinas ou podem estar na mesma máquina. Em ambos os casos, o método pode ser executado em um endereço diferente do processo de chamada. Java RMI é um mecanismo de chamada de procedimento remoto orientada a objetos.

Uma aplicação RMI é frequentemente composto por dois programas diferentes, um servidor e um cliente. O servidor cria objetos remotos e faz referências a esses objetos disponíveis. Em seguida, ele é válido para clientes invocarem seus métodos sobre os objetos.

O cliente executa referências remotas aos objetos remotos no servidor e invoca métodos nesses objetos remotos.

O modelo de RMI fornece uma aplicação de objetos distribuídos para o programador. Ele é um mecanismo de comunicação entre o servidor e o cliente para se comunicarem e transmitirem informações entre si. A aplicação de objetos distribuídos tem de prover as seguintes propriedades:

- Localização de objetos remotos: O sistema tem de obter referências a objetos remotos. Isto pode ser feito de duas maneiras. Ou, usando as instalações de nomeação do RMI, o registro RMI, ou passando e retornando objetos remotos.
- Comunicação com objetos remotos: O desenvolvedor não tem de lidar com a comunicação entre os objetos remotos desde que este é tratado pelo sistema RMI.
- Carregar os bytecodes de classe dos objetos que são transferidos como argumentos ou valores.

Fonte: [DevMedia](https://www.devmedia.com.br/uma-introducao-ao-rmi-em-java/28681)

# Descrição do que foi solicitado

Implemente um servidor de arquivos distribuídos que permita os seguintes:
- a. permitir que seja carregada uma lista de Candidatos com seus respectivos *nomes* e *números*;
- b. o cliente deve se conectar ao servidor e coletar a lista de *Candidatos* e apresentá-la ao *Eleitor*;
- c. o eleitor deve escolher *apenas um Candidato* e realizar seu *Voto*;
- d. Um *eleitor* não pode votar mais de uma vez;
- e. no *Servidor* a cada *Voto* deve ser apresentada a lista computada de *Votos* com a porcentagem de cada *Candidato*;
- f. na apresentaçao do trabalho, o *Servidor* deve computar a lista usando o método de outro servidor para realizar a tarefa;

# O Projeto

* Cliente:
A classe Cliente possui a tentativa de conexão ao servidor

```

  String objName = "rmi://localhost:" + porta + "/server";

  VotacaoInterface votacao = (VotacaoInterface) Naming.lookup(objName);

```

Já a interface RMI do Cliente possui os comandos dados pelo usuário.
```

  public List<CandidatoImpl> listarCandidatos() throws RemoteException;
  public int salvarVoto(int posicao) throws RemoteException;
  public int buscarCandidato(String numero) throws RemoteException;
  public void apuracao() throws RemoteException;

```

Permitindo quê este em sua conexão estabelecida possa realizar o consumo e envio de dados através de um servidor RMI e podendo assim realizar a Apuração, Listagem, Busca e Armazenamento de votos / candidatos (esse último por ser void executa no servidor caso chamado).

* Servidor:
A interface do servidor possui as configurações de conexão para permitir o acesso do cliente:

```

  String objName = "rmi://localhost:" + porta + "/server";

  LocateRegistry.createRegistry(porta);

  candidatos = new ArrayList<>();
  candidatos.add(new CandidatoImpl(11, "Fulano da Silva"));
  candidatos.add(new CandidatoImpl(33, "Beltrano de Nóbrega"));

  votacaoImpl = new VotacaoImpl(candidatos);

  Naming.rebind(objName, votacaoImpl);

```

O controle de acesso, assim como a cadeia de comandos que pode ser recebida pelo cliente, permitindo então ele executar as ações desejadas através de Method Invocation.

Abaixo estão os métodos utilizados:
```

  public List<CandidatoImpl> listarCandidatos() throws RemoteException;
  public int salvarVoto(int posicao) throws RemoteException;
  public int buscarCandidato(String numero) throws RemoteException;
  public void apuracao() throws RemoteException;

```
- ** O que não foi desenvolvido: **
Caso necessário, meu servidor não implementa um Registo RMI para buscar a lista de candidatos e afins em um servidor secundário, nele (em meu servidor) residem os candidatos e os passos dos métodos.

# Objetivo

Implementar a arquitetura RMI(Remote Method Invocation) mostrada em sala de aula para clarificar desenvolver um sistema de votação através de RMI onde apenas um voto seja permitido por participante (eleitor).

# Como executar

Inicialmente deve-se executar o servidor de Nomes usando o comando:

```
git clone https://github.com/MatheusADStudy/rmi-votes-java.git
```

Então devemos acessar a pasta do projeto e cerfique-se que a versão do java é JDK 1.8 (jdk 8)

```
  cd rmi-votes-java/

  java -version # => // openjdk version "1.8.0_342"
                     // OpenJDK Runtime Environment (build 1.8.0_342-8u342-b07-0ubuntu1~20.04-b07)
                     // OpenJDK 64-Bit Server VM (build 25.342-b07, mixed mode)
```
Então devemos compilar o servidor para produzirmos os arquivos ```.class```.

```
  // iremos compilar todos os arquivos .java dentro de interface, implementações junto com Cliente e Servidor.
 
  javac javac -d bin/ -sourcepath src/ src/interfaces/*.java src/implementacoes/*.java src/Servidor.java src/Cliente.java

```

Execute o programa Servidor:

```
  cd bin/ && java Servidor
```

Em seguida, execute o Cliente(abra outro terminal e navegue até o path anterior):
```

  cd bin/ && java Cliente
```
