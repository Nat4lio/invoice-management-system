import java.io.Serializable;

/**
 * A classe abstrata Product representa um produto genérico.
 * Esta classe implementa a interface Serializable.
 */
public abstract class Product implements Serializable
{
    private String code;
    private String name;
    private String description;
    private int quantity;
    private double unitPrice;

    /**
     * Construtor padrão da classe Product.
     * Inicializa os atributos com valores padrão.
     */
    public Product() 
    {
        this.code = "Desconhecido";
        this.name = "Desconhecido";
        this.description = "Desconhecido";
        this.quantity = 0;
        this.unitPrice = 0.0;
    }

    /**
     * Construtor da classe Product com parâmetros.
     *
     * @param code Código do produto.
     * @param name Nome do produto.
     * @param description Descrição do produto.
     * @param quantity Quantidade do produto.
     * @param unitPrice Preço unitário do produto.
     */
    public Product(String code, String name, String description, int quantity, double unitPrice) 
    {
        this.code = code;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    /**
     * Retorna o código do produto.
     *
     * @return O código do produto.
     */
    public String getCode() 
    {
        return code;
    }

    /**
     * Define o código do produto.
     *
     * @param code O novo código do produto.
     */
    public void setCode(String code) 
    {
        this.code = code;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return O nome do produto.
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Define o nome do produto.
     *
     * @param name O novo nome do produto.
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * Retorna a descrição do produto.
     *
     * @return A descrição do produto.
     */
    public String getDescription() 
    {
        return description;
    }

    /**
     * Define a descrição do produto.
     *
     * @param description A nova descrição do produto.
     */
    public void setDescription(String description) 
    {
        this.description = description;
    }

    /**
     * Retorna a quantidade do produto.
     *
     * @return A quantidade do produto.
     */
    public int getQuantity() 
    {
        return quantity;
    }

    /**
     * Define a quantidade do produto.
     *
     * @param quantity A nova quantidade do produto.
     */
    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }

    /**
     * Retorna o preço unitário do produto.
     *
     * @return O preço unitário do produto.
     */
    public double getUnitPrice() 
    {
        return unitPrice;
    }

    /**
     * Define o preço unitário do produto.
     *
     * @param unitPrice O novo preço unitário do produto.
     */
    public void setUnitPrice(double unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    /**
     * Exporta os detalhes do produto.
     *
     * @return Uma string com os detalhes do produto.
     */
    public String exportDetails() 
    {
        return code + "#" + name + "#" + description + "#" + quantity + "#" + unitPrice;
    }

    /**
     * Calcula a taxa de imposto com base na localização.
     *
     * @param location Localização para calcular a taxa de imposto.
     * @return A taxa de imposto correspondente à localização.
     * @throws IllegalArgumentException Se a localização for inválida.
     */
    public abstract double calculateTaxRate(String location);

    /**
     * Calcula o IVA com base na localização.
     *
     * @param location Localização para calcular o IVA.
     * @return O valor do IVA correspondente à localização.
     * @throws IllegalArgumentException Se a localização for inválida.
     */
    public abstract double calculateIVA(String location);
}