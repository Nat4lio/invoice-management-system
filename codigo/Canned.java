/**
 * A classe Canned representa um produto enlatado que pode ser orgânico.
 * Esta classe herda da classe IntermediateIVA.
 */
public class Canned extends IntermediateIVA 
{
    /**
     * Construtor padrão da classe Canned.
     */
    public Canned() 
    {
        super();
    }

    /**
     * Construtor da classe Canned com parâmetros.
     *
     * @param code Código do produto.
     * @param name Nome do produto.
     * @param description Descrição do produto.
     * @param quantity Quantidade do produto.
     * @param unitPrice Preço unitário do produto.
     * @param organic Indica se o produto é orgânico.
     * @param taxType Tipo de imposto aplicado ao produto.
     * @param category Categoria do produto.
     */
    public Canned(String code, String name, String description, int quantity, double unitPrice,
                    boolean organic, String taxType, String category) {
        super(code, name, description, quantity, unitPrice, organic, taxType, category);
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
        int baseRate = switch (location.toLowerCase()) 
        {
            case "continente" -> 13;
            case "madeira" -> 12;
            case "acores" -> 9;
            default -> throw new IllegalArgumentException("Localização inválida.");
        };

        return baseRate;
    }

    /**
     * Exporta os detalhes do produto.
     *
     * @return Uma string com os detalhes do produto.
     */
    @Override
    public String exportDetails()
    {
        return super.exportDetails();
    }
    
    /**
     * Retorna uma representação em string do objeto Canned.
     *
     * @return Uma string representando o objeto Canned.
     */
    @Override
    public String toString() 
    {
        return super.toString();
    }
}
