import java.util.ArrayList;
import java.util.List;

/**
 * A classe ReducedIVA representa um produto alimentício com IVA reduzido.
 * Esta classe herda da classe FoodProduct.
 */
public class ReducedIVA extends FoodProduct
{
    private List<String> certifications;

    /**
     * Construtor padrão da classe ReducedIVA.
     * Inicializa os atributos com valores padrão.
     */
    public ReducedIVA() 
    {
        super();
        this.certifications = new ArrayList<>();
    }

    /**
     * Construtor da classe ReducedIVA com parâmetros.
     *
     * @param code Código do produto.
     * @param name Nome do produto.
     * @param description Descrição do produto.
     * @param quantity Quantidade do produto.
     * @param unitPrice Preço unitário do produto.
     * @param organic Indica se o produto é orgânico.
     * @param taxType Tipo de imposto aplicado ao produto.
     * @param certifications Lista de certificações do produto.
     */
    public ReducedIVA(String code, String name, String description, int quantity, double unitPrice,
                      boolean organic, String taxType, List<String> certifications) {
        super(code, name, description, quantity, unitPrice, organic, taxType);
        this.certifications = certifications;
    }

    /**
     * Retorna a lista de certificações do produto.
     *
     * @return A lista de certificações do produto.
     */
    public List<String> getCertifications() 
    {
        return certifications;
    }

    /**
     * Define a lista de certificações do produto.
     *
     * @param certifications A nova lista de certificações do produto.
     */
    public void setCertifications(List<String> certifications) 
    {
        this.certifications = certifications;
    }

    /**
     * Calcula a taxa de imposto com base na localização.
     *
     * @param location Localização para calcular a taxa de imposto.
     * @return A taxa de imposto correspondente à localização.
     * @throws IllegalArgumentException Se a localização for inválida.
     */
    @Override
    public double calculateTaxRate(String location) 
    {
        return switch (location.toLowerCase()) 
        {
            case "continente" -> 6;
            case "madeira" -> 5;
            case "acores" -> 4;
            default -> throw new IllegalArgumentException("Localização inválida.");
        };
    }

    /**
     * Exporta os detalhes do produto.
     *
     * @return Uma string com os detalhes do produto.
     */
    @Override
    public String exportDetails()
    {
        return super.exportDetails() + "#" + String.join(",", certifications);
    }

    @Override
    public String toString() 
    {
        return super.toString() + "---> Certificações: " + certifications + '\n';
    }
}