/**
 * A classe WithPrescription representa um produto farmacêutico que necessita de prescrição.
 * Esta classe herda da classe PharmacyProduct.
 */
public class WithPrescription extends PharmacyProduct
{
    private String medicName;

    /**
     * Construtor padrão da classe WithPrescription.
     * Inicializa os atributos com valores padrão.
     */
    public WithPrescription() {
        super(); 
        this.medicName = "Desconhecido"; 
    }

    /**
     * Construtor da classe WithPrescription com parâmetros.
     *
     * @param code Código do produto.
     * @param name Nome do produto.
     * @param description Descrição do produto.
     * @param quantity Quantidade do produto.
     * @param unitPrice Preço unitário do produto.
     * @param prescription Indica se o produto necessita de prescrição.
     * @param medicName Nome do médico que prescreveu o produto.
     */
    public WithPrescription(String code, String name, String description, int quantity, double unitPrice, boolean prescription, String medicName) {
        super(code, name, description, quantity, unitPrice, prescription);
        this.medicName = medicName;
    }

    /**
     * Retorna o nome do médico que prescreveu o produto.
     *
     * @return O nome do médico que prescreveu o produto.
     */
    public String getMedicName() 
    {
        return medicName;
    }

    /**
     * Define o nome do médico que prescreveu o produto.
     *
     * @param medicName O novo nome do médico que prescreveu o produto.
     */
    public void setMedicName(String medicName) 
    {
        this.medicName = medicName;
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
            case "continente" -> 6;
            case "madeira" -> 5;
            case "acores" -> 4;
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
        return super.exportDetails() + "#" + medicName;
    }

    /**
     * Retorna uma representação em string do objeto WithPrescription.
     *
     * @return Uma string representando o objeto WithPrescription.
     */
    @Override
    public String toString() 
    {
        return super.toString() + ", MedicName='" + medicName + '\'';
    }
}