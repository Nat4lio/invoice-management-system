/**
 * A classe abstrata FoodProduct representa um produto alimentício.
 * Esta classe herda da classe Product.
 */
public abstract class FoodProduct extends Product 
{
    private boolean organic;
    private String taxType; 

    /**
     * Construtor padrão da classe FoodProduct.
     * Inicializa os atributos com valores padrão.
     */
    public FoodProduct() 
    {
        super(); 
        this.organic = false; 
        this.taxType = "Desconhecido";
    }

    /**
     * Construtor da classe FoodProduct com parâmetros.
     *
     * @param code Código do produto.
     * @param name Nome do produto.
     * @param description Descrição do produto.
     * @param quantity Quantidade do produto.
     * @param unitPrice Preço unitário do produto.
     * @param organic Indica se o produto é orgânico.
     * @param taxType Tipo de imposto aplicado ao produto.
     */
    public FoodProduct(String code, String name, String description, int quantity, double unitPrice,
                       boolean organic, String taxType) {
        super(code, name, description, quantity, unitPrice);
        this.organic = organic;
        this.taxType = taxType;
    }

    /**
     * Retorna se o produto é orgânico.
     *
     * @return true se o produto é orgânico, false caso contrário.
     */
    public boolean getOrganic() 
    {
        return organic;
    }

    /**
     * Define se o produto é orgânico.
     *
     * @param organic true se o produto é orgânico, false caso contrário.
     */
    public void setOrganic(boolean organic) 
    {
        this.organic = organic;
    }

    /**
     * Retorna o tipo de imposto aplicado ao produto.
     *
     * @return O tipo de imposto aplicado ao produto.
     */
    public String getTaxType()
    {
        return taxType;
    }

    /**
     * Define o tipo de imposto aplicado ao produto.
     *
     * @param taxType O novo tipo de imposto aplicado ao produto.
     */
    public void setTaxType(String taxType)
    {
        this.taxType = taxType;
    }

    @Override
    public final double calculateIVA(String location) 
    {
        double taxRate = calculateTaxRate(location) / 100.0;
        double iva = getUnitPrice() * getQuantity() * taxRate;

        if (organic) 
        {
            iva -= iva * 0.1;
        }

        return iva;
    }

    @Override
    public abstract double calculateTaxRate(String location);

    @Override
    public  String exportDetails()
    {
        return String.valueOf(organic) + "#" + taxType;
    }

    @Override
    public String toString() 
    {
        return super.toString() + "---> Biológico: " + organic + '\n' + "---> Tipo de taxa: " + taxType + '\n';
    }
}