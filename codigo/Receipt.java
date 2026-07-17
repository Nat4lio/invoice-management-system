import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe Receipt representa um recibo que contém informações sobre um cliente e uma lista de produtos.
 * Esta classe implementa a interface Serializable.
 */
public class Receipt implements Serializable
{
    private int number;
    private Client client;
    private String date;
    private List<Product> products;

    /**
     * Construtor padrão da classe Receipt.
     * Inicializa os atributos com valores padrão.
     */
    public Receipt() 
    {
        this.number = 0; 
        this.client = null; 
        this.date = "xx/xx/xxxx"; 
        this.products = new ArrayList<>();
    }

    /**
     * Construtor da classe Receipt com parâmetros.
     *
     * @param number Número do recibo.
     * @param client Cliente associado ao recibo.
     * @param date Data do recibo.
     * @param products Lista de produtos no recibo.
     */
    public Receipt(int number, Client client, String date, List<Product> products) 
    {
        this.number = number;
        this.client = client;
        this.date = date;
        this.products = products;
    }

    /**
     * Retorna o número do recibo.
     *
     * @return O número do recibo.
     */
    public int getNumber() 
    {
        return number;
    }

    /**
     * Define o número do recibo.
     *
     * @param number O novo número do recibo.
     */
    public void setNumber(int number) 
    {
        this.number = number;
    }

    /**
     * Retorna o cliente associado ao recibo.
     *
     * @return O cliente associado ao recibo.
     */
    public Client getClient() 
    {
        return client;
    }

    /**
     * Define o cliente associado ao recibo.
     *
     * @param client O novo cliente associado ao recibo.
     */
    public void setClient(Client client) 
    {
        this.client = client;
    }

    /**
     * Retorna a data do recibo.
     *
     * @return A data do recibo.
     */
    public String getDate() 
    {
        return date;
    }

    /**
     * Define a data do recibo.
     *
     * @param date A nova data do recibo.
     */
    public void setDate(String date) 
    {
        this.date = date;
    }

    /**
     * Retorna a lista de produtos no recibo.
     *
     * @return A lista de produtos no recibo.
     */
    public List<Product> getProducts() 
    {
        return products;
    }

    /**
     * Define a lista de produtos no recibo.
     *
     * @param products A nova lista de produtos no recibo.
     */
    public void setProducts(List<Product> products) 
    {
        this.products = products;
    }

    /**
     * Adiciona um produto à lista de produtos no recibo.
     *
     * @param product O produto a ser adicionado.
     */
    public void addProduct(Product product) 
    {
        this.products.add(product);
    }

    public void removeProduct(Product product)
    {
        products.remove(product);
    }

    public double valueWithoutTax() 
    {
        double totalWithoutIVA = 0.0;

        for (Product product : products) 
        {
            totalWithoutIVA += product.getUnitPrice() * product.getQuantity();
        }

        return totalWithoutIVA;
    }

    public double calculateTotalIVA() 
    {
        double totalIVA = 0;
        for (Product product : this.products) 
        {
            totalIVA += product.calculateIVA(client.getLocation()); 
        }
        return totalIVA;
    }
    
    public double calculateTotalWithIVA() 
    {
        double totalWithIVA = 0.0;

        for (Product product : products) 
        {
            double productValue = product.getUnitPrice() * product.getQuantity();
            double iva = product.calculateIVA(client.getLocation()); 
            totalWithIVA += productValue + iva;
        }

        return totalWithIVA;
    }

    /**
     * Retorna uma representação em string do objeto Receipt.
     *
     * @return Uma string representando o objeto Receipt.
     */
    @Override
    public String toString() 
    {
        String result = "\n-------------------------  Fatura #" + number + "  -------------------------\n";
        result += "\n-------->   Cliente   <--------\n";
        result += "\n" + client + "\n\n";
        
        result += "------>  Lista de produtos  <------\n\n";
        
        for (Product product : products) 
        {
            double totalWithouthIVA = product.getUnitPrice() * product.getQuantity();
            double iva = product.calculateIVA(client.getLocation());
            double totalWithIVA = totalWithouthIVA + iva;

            result += product + "\n";

            result += "\n--> Total Sem IVA: " + String.format("%.2f", totalWithouthIVA) + "\n";
            result += "--> IVA: " + String.format("%.2f", iva) + "\n";
            result += "--> Total Com IVA: " + String.format("%.2f", totalWithIVA) + "\n\n\n";
        }

        result += "-------->  Total  <--------\n";

        result += "\nSem IVA: " + String.format("%.2f", valueWithoutTax()) + "\n";
        result += "Do IVA: " + String.format("%.2f", calculateTotalIVA()) + "\n";
        result += "Com IVA: " + String.format("%.2f", calculateTotalWithIVA()) + "\n";

        result += "\n------------------------------ // -----------------------------\n";

        return result;
    }
}