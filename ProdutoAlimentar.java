import java.util.List;

public class ProdutoAlimentar extends Produto {
    private boolean biologico;
    private String tipoTaxa; // "Reduzida", "Intermediaria", "Normal"
    private List<String> certificacoes; // Máximo 4 certificações

    public ProdutoAlimentar(String codigo, String nome, String descricao, int quantidade, double valorUnitario,
                            boolean biologico, String tipoTaxa, List<String> certificacoes) {
        super(codigo, nome, descricao, quantidade, valorUnitario);
        this.biologico = biologico;
        this.tipoTaxa = tipoTaxa;
        this.certificacoes = certificacoes;
    }

    @Override
    public double calcularIVA(Cliente cliente) {
        // Implementar cálculo de IVA específico para produtos alimentares
        return 0.0; // Exemplo
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Biológico: " + biologico +
                ", Tipo de Taxa: " + tipoTaxa +
                ", Certificações: " + certificacoes;
    }
}