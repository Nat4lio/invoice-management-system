/**
 * A classe abstrata WithouthPrescription representa um produto farmacêutico que não necessita de prescrição.
 * Esta classe herda da classe PharmacyProduct.
 */
public abstract class WithouthPrescription extends PharmacyProduct {
    private String category;

    /**
     * Construtor padrão da classe WithouthPrescription.
     * Inicializa os atributos com valores padrão.
     */
    public WithouthPrescription() 
    {
        super(); 
        this.category = "Desconhecida";
    }

    /**
     * Construtor da classe WithouthPrescription com parâmetros.
     *
     * @param code Código do produto.
     * @param name Nome do produto.
     * @param description Descrição do produto.
     * @param quantity Quantidade do produto.
     * @param unitPrice Preço unitário do produto.
     * @param prescription Indica se o produto necessita de prescrição.
     * @param category Categoria do produto.
     */
    public WithouthPrescription(String code, String name, String description, int quantity, double unitPrice, boolean prescription, String category) {
        super(code, name, description, quantity, unitPrice, prescription);
        this.category = category;
    }

    /**
     * Retorna a categoria do produto.
     *
     * @return A categoria do produto.
     */
    public String getCategory() 
    {
        return category;
    }

    /**
     * Define a categoria do produto.
     *
     * @param category A nova categoria do produto.
     */
    public void setCategory(String category) 
    {
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
        return super.exportDetails() + category;
    }

    @Override
    public String toString() 
    {
        return super.toString() + "---> Categoria: " + category;
    }
}
