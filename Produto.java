public abstract class Produto {
    private String codigo;
    private String nome;
    private String descricao;
    private int quantidade;
    private double valorUnitario;

    public Produto(String codigo, String nome, String descricao, int quantidade, double valorUnitario) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public double calcularTotal() {
        return quantidade * valorUnitario;
    }

    public abstract double calcularIVA(Cliente cliente);

    @Override
    public String toString() {
        return "Código: " + codigo +
                ", Nome: " + nome +
                ", Descrição: " + descricao +
                ", Quantidade: " + quantidade +
                ", Valor Unitário: " + valorUnitario;
    }
}