/**
 * A classe abstrata IntermediateIVA representa um produto alimentício com IVA intermediário.
 * Esta classe herda da classe FoodProduct.
 */
public abstract class IntermediateIVA extends FoodProduct {
    private String category;

    /**
     * Construtor padrão da classe IntermediateIVA.
     * Inicializa os atributos com valores padrão.
     */
    public IntermediateIVA() 
    {
        super();
        this.category = "Desconhecido"; 
    }

    /**
     * Construtor da classe IntermediateIVA com parâmetros.
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
    public IntermediateIVA(String code, String name, String description, int quantity, double unitPrice,
                           boolean organic, String taxType, String category) {
        super(code, name, description, quantity, unitPrice, organic, taxType);
        this.category = category; 
    }

    /**
     * Retorna a categoria do produto.
     *
     * @return A categoria do produto.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Define a categoria do produto.
     *
     * @param category A nova categoria do produto.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Calcula a taxa de imposto com base na localização.
     *
     * @param location Localização para calcular a taxa de imposto.
     * @return A taxa de imposto correspondente à localização.
     * @throws IllegalArgumentException Se a localização for inválida.
     */
    @Override
    public abstract double calculateTaxRate(String location);

    /**
     * Exporta os detalhes do produto.
     *
     * @return Uma string com os detalhes do produto.
     */
    @Override
    public String exportDetails()
    {
        return super.exportDetails() + "#" + category;
    }

    @Override
    public String toString() 
    {
        return super.toString() + "---> Categoria: " + category  + '\n';
    }
}