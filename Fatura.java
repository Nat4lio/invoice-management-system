import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fatura {
    private String numero;
    private Cliente cliente;
    private Date data;
    private List<Produto> produtos;

    public Fatura(String numero, Cliente cliente, Date data) {
        this.numero = numero;
        this.cliente = cliente;
        this.data = data;
        this.produtos = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getData() {
        return data;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public double calcularTotalSemIVA() {
        double total = 0.0;
        for (Produto produto : produtos) {
            total += produto.calcularTotal();
        }
        return total;
    }

    public double calcularTotalIVA() {
        double totalIVA = 0.0;
        for (Produto produto : produtos) {
            totalIVA += produto.calcularIVA(cliente) * produto.getQuantidade();
        }
        return totalIVA;
    }

    public double calcularTotalComIVA() {
        return calcularTotalSemIVA() + calcularTotalIVA();
    }

    @Override
    public String toString() {
        return "Fatura{" +
                "numero='" + numero + '\'' +
                ", cliente=" + cliente +
                ", data=" + data +
                ", produtos=" + produtos +
                '}';
    }
}