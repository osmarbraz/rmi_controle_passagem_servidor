
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Programa servidor do objeto calculadora.
 *
 * Instancia o objeto e registra no binder.
 *
 * @author osmar
 */
public class Principal {

    public static void main(String args[]) {
        try {
            // Este é o nome pelo qual o serviço será posteriormente localizado
            String nomeServico = "controlevoo";
            //Cria o objeto a ser servido
            ControladorVoo controladorvoo = new ControladorVooImpl();
            //Cria o stub dinamicamente do objeto a ser servido
            ControladorVoo stub = (ControladorVoo) UnicastRemoteObject.exportObject(controladorvoo, 0);
            //Referência para rmiregistry na porta 1099
            Registry registro = LocateRegistry.getRegistry();             
            //Registra o objeto no rmiregistry
            registro.bind(nomeServico, controladorvoo);
            System.out.println("Servidor no ar. Nome do objeto no servidor: \'" + nomeServico + "\'");
        } catch (RemoteException re) {
            System.out.println("Excecao: " + re.getMessage());
        } catch (AlreadyBoundException abe) {
            System.out.println("Excecao: " + abe.getMessage());
        }

    }
}
