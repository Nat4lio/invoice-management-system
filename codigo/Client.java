import java.io.Serializable;

/**
 * A classe Client representa um cliente com nome, NIF e localização.
 * Esta classe implementa a interface Serializable.
 */
public class Client implements Serializable 
{
    private String name;
    private String nif;
    private String location; 

    /**
     * Construtor padrão da classe Client.
     * Inicializa os atributos com valores padrão.
     */
    public Client() 
    {
        this.name = "Desconhecido";
        this.nif = "xxxxxxxxx";
        this.location = "Desconhecido";
    }

    /**
     * Construtor da classe Client com parâmetros.
     *
     * @param name Nome do cliente.
     * @param nif Número de Identificação Fiscal do cliente.
     * @param location Localização do cliente.
     */
    public Client(String name, String nif, String location) 
    {
        this.name = name;
        this.nif = nif;
        this.location = location;
    }

    /**
     * Retorna o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getName() 
    { 
        return name; 
    }

    /**
     * Retorna o NIF do cliente.
     *
     * @return O NIF do cliente.
     */
    public String getNif() 
    { 
        return nif; 
    }

    /**
     * Retorna a localização do cliente.
     *
     * @return A localização do cliente.
     */
    public String getLocation() 
    { 
        return location; 
    }

    /**
     * Define o nome do cliente.
     *
     * @param name O novo nome do cliente.
     */
    public void setName(String name) 
    { 
        this.name = name; 
    }

    /**
     * Define o NIF do cliente.
     *
     * @param nif O novo NIF do cliente.
     */
    public void setNif(String nif) 
    { 
        this.nif = nif; 
    }

    /**
     * Define a localização do cliente.
     *
     * @param location A nova localização do cliente.
     */
    public void setLocation(String location) 
    { 
        this.location = location; 
    }

    /**
     * Retorna uma representação em string do objeto Client.
     *
     * @return Uma string representando o objeto Client.
     */
    @Override
    public String toString() 
    {
        return "Client{" +
                "name='" + name + '\'' +
                ", nif='" + nif + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}