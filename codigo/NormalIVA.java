/**
 * A classe NormalIVA representa um produto alimentício com IVA normal.
 * Esta classe herda da classe FoodProduct.
 */
public class NormalIVA extends FoodProduct
{
    /**
     * Construtor padrão da classe NormalIVA.
     */
    public NormalIVA() 
    {
        super();
    }

    /**
     * Construtor da classe NormalIVA com parâmetros.
     *
     * @param code Código do produto.
     * @param name Nome do produto.
     * @param description Descrição do produto.
     * @param quantity Quantidade do produto.
     * @param unitPrice Preço unitário do produto.
     * @param organic Indica se o produto é orgânico.
     * @param taxType Tipo de imposto aplicado ao produto.
     */
    public NormalIVA(String code, String name, String description, int quantity, double unitPrice,
                     boolean organic, String taxType) {
        super(code, name, description, quantity, unitPrice, organic, taxType);
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
            case "continente" -> 23;
            case "madeira" -> 22;
            case "acores" -> 16;
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

    @Override
    public String toString() 
    {
        return super.toString();
    }
}