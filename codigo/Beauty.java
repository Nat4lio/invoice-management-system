/**
 * A classe Beauty representa um produto que não necessita de prescrição.
 * Esta classe herda da classe WithouthPrescription.
 */
public class Beauty extends WithouthPrescription 
{
    /**
     * Construtor padrão da classe Beauty.
     */
    public Beauty() 
    {
        super();
    }

    /**
     * Construtor da classe Beauty com parâmetros.
     *
     * @param code Código do produto.
     * @param name Nome do produto.
     * @param description Descrição do produto.
     * @param quantity Quantidade do produto.
     * @param unitPrice Preço unitário do produto.
     * @param prescription Indica se o produto necessita de prescrição.
     * @param category Categoria do produto.
     */
    public Beauty(String code, String name, String description, int quantity, double unitPrice, boolean prescription, String category) {
        super(code, name, description, quantity, unitPrice, prescription, category);
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
            case "continente", "madeira", "acores" -> 23;
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
        return super.exportDetails();
    }
    
    /**
     * Retorna uma representação em string do objeto Beauty.
     *
     * @return Uma string representando o objeto Beauty.
     */
    @Override
    public String toString() 
    {
        return super.toString();
    }
}