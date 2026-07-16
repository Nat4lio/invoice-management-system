import java.util.ArrayList;
import java.util.List;

public class GestorFaturas {
    private List<Fatura> faturas;

    public GestorFaturas() {
        faturas = new ArrayList<>();
    }

    public void adicionarFatura(Fatura fatura) {
        faturas.add(fatura);
    }

    public Fatura buscarFaturaPorNumero(String numero) {
        for (Fatura fatura : faturas) {
            if (fatura.getNumero().equals(numero)) {
                return fatura;
            }
        }
        return null;
    }

    public List<Fatura> listarFaturas() {
        return faturas;
    }
}