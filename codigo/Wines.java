/**
 * A classe Wines representa um produto de vinho que pode ser orgânico.
 * Esta classe herda da classe IntermediateIVA.
 */
public class Wines extends IntermediateIVA 
{
    /**
     * Construtor padrão da classe Wines.
     */
    public Wines() 
    {
        super();
    }

    /**
     * Construtor da classe Wines com parâmetros.
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
    public Wines(String code, String name, String description, int quantity, double unitPrice,
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
            case "continente" -> 14;
            case "madeira" -> 13;
            case "acores" -> 10;
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
     * Retorna uma representação em string do objeto Wines.
     *
     * @return Uma string representando o objeto Wines.
     */
    @Override
    public String toString() 
    {
        return super.toString();
    }
}
