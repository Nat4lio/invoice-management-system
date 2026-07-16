public class ProdutoFarmacia extends Produto {
    private boolean requerPrescricao;
    private String nomeMedico; // Caso prescrição seja necessária
    private String categoria; // Caso não seja necessário (beleza, bem-estar, etc.)

    public ProdutoFarmacia(String codigo, String nome, String descricao, int quantidade, double valorUnitario,
                           boolean requerPrescricao, String nomeMedico, String categoria) {
        super(codigo, nome, descricao, quantidade, valorUnitario);
        this.requerPrescricao = requerPrescricao;
        this.nomeMedico = nomeMedico;
        this.categoria = categoria;
    }

    @Override
    public double calcularIVA(Cliente cliente) {
        // Implementar cálculo de IVA específico para produtos de farmácia
        return 0.0; // Exemplo
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Requer Prescrição: " + requerPrescricao +
                ", Nome do Médico: " + (requerPrescricao ? nomeMedico : "N/A") +
                ", Categoria: " + (requerPrescricao ? "N/A" : categoria);
    }
}