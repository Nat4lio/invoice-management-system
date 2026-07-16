import java.util.ArrayList;
import java.util.List;

public class GestorDeClientes {
    private List<Cliente> clientes;

    public GestorDeClientes() {
        this.clientes = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public Cliente buscarClientePorNIF(String nif) {
        for (Cliente cliente : clientes) {
            if (cliente.getNif().equals(nif)) {
                return cliente;
            }
        }
        return null; // Retorna null se o cliente não for encontrado
    }

    public boolean editarCliente(String nif, Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNif().equals(nif)) {
                clientes.set(i, clienteAtualizado);
                return true; // Retorna true se o cliente foi atualizado
            }
        }
        return false; // Retorna false se o cliente não foi encontrado
    }

    public boolean removerCliente(String nif) {
        return clientes.removeIf(cliente -> cliente.getNif().equals(nif));
    }
}