/**
 * A classe abstrata PharmacyProduct representa um produto farmacêutico.
 * Esta classe herda da classe Product.
 */
public abstract class PharmacyProduct extends Product 
{
    private boolean prescription;

    /**
     * Construtor padrão da classe PharmacyProduct.
     * Inicializa os atributos com valores padrão.
     */
    public PharmacyProduct() 
    {
        super(); 
        this.prescription = false;
    }

    /**
     * Construtor da classe PharmacyProduct com parâmetros.
     *
     * @param code Código do produto.
     * @param name Nome do produto.
     * @param description Descrição do produto.
     * @param quantity Quantidade do produto.
     * @param unitPrice Preço unitário do produto.
     * @param prescription Indica se o produto necessita de prescrição.
     */
    public PharmacyProduct(String code, String name, String description, int quantity, double unitPrice,
                           boolean prescription) 
    {
        super(code, name, description, quantity, unitPrice);
        this.prescription = prescription;
    }

    /**
     * Retorna se o produto necessita de prescrição.
     *
     * @return true se o produto necessita de prescrição, false caso contrário.
     */
    public boolean getPrescricao() 
    {
        return prescription;
    }

    /**
     * Define se o produto necessita de prescrição.
     *
     * @param prescricao true se o produto necessita de prescrição, false caso contrário.
     */
    public void setPrescricao(boolean prescricao) 
    {
        this.prescription = prescricao;
    }

    /**
     * Calcula o IVA com base na localização.
     *
     * @param location Localização para calcular o IVA.
     * @return O valor do IVA correspondente à localização.
     * @throws IllegalArgumentException Se a localização for inválida.
     */
    @Override
    public double calculateIVA(String location) 
    {
        double taxRate = calculateTaxRate(location) / 100.0;
        return taxRate * getUnitPrice();
    }

    /**
     * Calcula a taxa de imposto com base na localização.
     *
     * @param location Localização para calcular a taxa de imposto.
     * @return A taxa de imposto correspondente à localização.
     * @throws IllegalArgumentException Se a localização for inválida.
     */
    public abstract double calculateTaxRate(String location);
}
