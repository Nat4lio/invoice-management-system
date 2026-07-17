import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A classe POOFSApp representa a aplicação principal que gerencia clientes e recibos.
 */
public class POOFSApp 
{
    private final Scanner scanner;
    private final InputHandler inputHandler;
    private ArrayList<Client> clients;
    private ArrayList<Receipt> receipts;

    /**
     * Construtor padrão da classe POOFSApp.
     * Inicializa os atributos com valores padrão.
     */
    public POOFSApp() 
    {
        this.scanner = new Scanner(System.in); 
        this.clients = new ArrayList<>(); 
        this.receipts = new ArrayList<>(); 
        this.inputHandler = new InputHandler(scanner);
    }

    /**
     * Construtor da classe POOFSApp com um Scanner personalizado.
     *
     * @param scanner O Scanner a ser utilizado para entrada de dados.
     */
    public POOFSApp(Scanner scanner) 
    {
        this.scanner = scanner;
        this.clients = new ArrayList<>();
        this.receipts = new ArrayList<>();
        this.inputHandler = new InputHandler(scanner);
    }

    /**
     * Adiciona um novo cliente à lista de clientes.
     *
     * @param client O cliente a ser adicionado.
     */
    public void addClient(Client client) 
    {
        clients.add(client);
    }

    /**
     * Adiciona um novo recibo à lista de recibos.
     *
     * @param receipt O recibo a ser adicionado.
     */
    public void addReceipt(Receipt receipt) 
    {
        receipts.add(receipt);
    }

    /**
     * Salva a lista de clientes em um arquivo.
     *
     * @param filename O nome do arquivo onde os clientes serão salvos.
     * @throws IOException Se ocorrer um erro de E/S.
     */
    public void saveClientsToFile(String filename) throws IOException 
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) 
        {
            oos.writeObject(clients);
        }
    }

    /**
     * Carrega a lista de clientes de um arquivo.
     *
     * @param filename O nome do arquivo de onde os clientes serão carregados.
     * @throws IOException Se ocorrer um erro de E/S.
     * @throws ClassNotFoundException Se a classe dos objetos não for encontrada.
     */
    public void loadClientsFromFile(String filename) throws IOException, ClassNotFoundException 
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) 
        {
            clients = (ArrayList<Client>) ois.readObject();
        }
    }

    /**
     * Salva a lista de recibos em um arquivo.
     *
     * @param filename O nome do arquivo onde os recibos serão salvos.
     * @throws IOException Se ocorrer um erro de E/S.
     */
    public void saveReceiptsToFile(String filename) throws IOException 
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) 
        {
            oos.writeObject(receipts);
        }
    }

    /**
     * Carrega a lista de recibos de um arquivo.
     *
     * @param filename O nome do arquivo de onde os recibos serão carregados.
     * @throws IOException Se ocorrer um erro de E/S.
     * @throws ClassNotFoundException Se a classe dos objetos não for encontrada.
     */
    public void loadReceiptsFromFile(String filename) throws IOException, ClassNotFoundException 
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) 
        {
            receipts = (ArrayList<Receipt>) ois.readObject();
        }
    }

    public ArrayList<Client> getClients() 
    {
        return new ArrayList<>(clients); 
    }
    public ArrayList<Receipt> getReceipts() 
    {
        return new ArrayList<>(receipts);
    }
    public void setClients(ArrayList<Client> clients) 
    {
        this.clients = new ArrayList<>(clients); 
    }
    public void setReceipts(ArrayList<Receipt> receipts) 
    {
        this.receipts = new ArrayList<>(receipts); 
    }



    public static void main(String[] args) 
    {
        try (Scanner scanner = new Scanner(System.in)) 
        {
            POOFSApp app = new POOFSApp(scanner);
            boolean program = true;

            app.loadDataFromArquive();
            
            while (program)
            {
                System.out.println();
                app.showMenu();
                int opcao = app.inputHandler.validIntegerInput("\nEscolha uma opção: ",0,8);
                
                switch (opcao) 
                {
                    case 1 -> 
                    {
                        System.out.println("\nDigite o número.");
                        System.out.println("1 - Criar Cliente");
                        System.out.println("2 - Editar Cliente");

                        int choice = app.inputHandler.validIntegerInput("\nIntroduza: ",1,2);
                        
                        switch (choice) 
                        {
                            case 1 -> app.createClient();
                            case 2 -> app.editClient();
                            default -> 
                            {
                                System.out.println("\nOpção inválida.");
                            }
                        }
                    }
                    case 2 -> app.listClients();
                    case 3 -> 
                    {
                        System.out.println("\nDigite o número.");
                        System.out.println("1 - Criar Fatura");
                        System.out.println("2 - Editar Fatura");

                        int choice = app.inputHandler.validIntegerInput("\nIntroduza: ",1,2);
                        
                        switch (choice) 
                        {
                            case 1 -> app.createReceipt();
                            case 2 -> app.editReceipt();
                            default -> 
                            {
                                
                                System.out.println("\nOpção inválida.");
                            }
                        }
                    }
                    case 4 -> app.listReceipts();
                    case 5 -> app.seeReceipt();
                    case 6 ->
                    {
                        String fileName = app.inputHandler.inputName("\nDigite o nome do ficheiro a importar: ");
                        app.loadTextData(fileName);
                    }
                    case 7 -> 
                    {
                        String fileName = app.inputHandler.inputName("\nDigite o nome do ficheiro a exportar: ");
                        app.saveTextData(fileName);
                    }
                    case 8 -> System.out.println(app);
                    case 0 -> 
                    {
                        app.saveDataToArchive();
                        program = false;
                    }
                    default -> 
                    {
                        System.out.println("\nOpção inválida. Tente novamente.\n");
                    }
                }
            }
            System.out.println("\nA sair...\n");
        }
    }

    private void showMenu() 
    {
        System.out.println("\n--------------------- Bem-vindo ao POOFS App ------------------\n");
        System.out.println("\t\t\t  ---- MENU ----\n");
        System.out.println("1. Criar e editar cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Criar e editar fatura");
        System.out.println("4. Listar Faturas");
        System.out.println("5. Visualizar fatura");
        System.out.println("6. Importar faturas");
        System.out.println("7. Exportar faturas");
        System.out.println("8. Mostrar estatísticas");
        System.out.println("0. Sair");
        System.out.println("\n------------------------------- // -----------------------------\n");
    }

    private void menuBoolean(String prompt) 
    {
        System.out.println(prompt);
        System.out.println("--> True");
        System.out.println("--> False");
        System.out.print("\nIntroduza: ");
    }



    private void editClient()
    {
        listClients();
        System.out.println("\nTerá que introduzir o NIF do cliente a ser editado.");
        String clientNIF = inputHandler.inputNIF("\nIntroduza o NIF: ");
        
        Client client = searchClientByNIF(clientNIF); 
        if (client == null) 
        {
            System.out.println("\nCliente não encontrado.");
            return;
        }

        boolean editing = true;

        while (editing) 
        {
            System.out.println("\n------------------ Editar Cliente ------------------\n");
            System.out.println(client);
            System.out.println("\nDigite o número do que quer editar.");
            System.out.println("1 - Nome");
            System.out.println("2 - NIF");
            System.out.println("3 - Localização");
            System.out.println("4 - Sair");

            int option = inputHandler.validIntegerInput("\nEscolha uma opção: ", 1, 4);

            switch (option) 
            {
                case 1 -> 
                {
                    String newName = inputHandler.inputName("\nDigite o novo nome: ");
                    client.setName(newName);
                    System.out.println("\nNome atualizado com sucesso!");
                }
                case 2 -> 
                {
                    boolean validNIF = false;

                    while (!validNIF) 
                    {
                        String newNIF = inputHandler.inputNIF("\nIntroduza o novo NIF: ");

                        try 
                        {
                            verifyExistClient(newNIF);
                            client.setNif(newNIF);
                            System.out.println("\nNIF atualizado com sucesso!");
                            validNIF = true;
                        } catch (IllegalArgumentException e) 
                        {
                            System.out.println("\nJá existe!");
                        }
                    }
                }
                case 3 -> 
                {
                    System.out.print("\nDigite a localização do cliente: \n");
                    System.out.println("--> Continente");
                    System.out.println("--> Madeira");
                    System.out.println("--> Acores");
                    String newLocation = inputHandler.inputLocation("\nIntroduza: ");
                    client.setLocation(newLocation);
                    System.out.println("\nLocalização atualizada com sucesso!");
                }
                case 4 -> 
                {
                    System.out.println("\nCliente atualizado com sucesso!");
                    System.out.println("\n---------------------- // ----------------------");
                    editing = false;
                }
                default -> 
                {
                    System.out.println("\nOpção inválida.");
                }
            }
        }
    }

    private void editReceipt()
    {
        listReceipts();
        System.out.println("\nTerá que introduzir o número da fatura a ser editada.");
        int receiptNumber = inputHandler.inputInt("\nIntroduza: ");

        Receipt receipt = searchReceiptByNumber(receiptNumber);
        if (receipt == null) 
        {
            System.out.println("\nFatura não encontrada.");
            return;
        }

        boolean editing = true;

        while (editing) 
        {
            System.out.println(receipt);
            System.out.println("\nDigite o número do que quer editar.");
            System.out.println("1 - Cliente");
            System.out.println("2 - Data");
            System.out.println("3 - Número");
            System.out.println("4 - Produtos");
            System.out.println("5 - Sair");

            int option = inputHandler.validIntegerInput("\nEscolha uma opção: ", 1, 5);

            switch (option) 
            {
                case 1 -> 
                {
                    System.out.print("Acerca do NIF do novo cliente.\n");
                    String newClientNIF = inputHandler.inputNIF("\nIntroduza o NIF: ");
                    Client newClient = searchClientByNIF(newClientNIF);
                    if (newClient == null) 
                    {
                        System.out.println("\nCliente não encontrado.");
                    } else 
                    {
                        receipt.setClient(newClient); 
                        System.out.println("\nCliente atualizado com sucesso!");
                    }
                }
                case 2 ->
                {
                    System.out.print("Acerca da nova data. ");
                    String newDate = inputHandler.inputDate("\nDigite a data (xx/xx/xxxx): ");
                    receipt.setDate(newDate);
                    System.out.println("\nData atualizada com sucesso!");
                }
                case 3 -> 
                {
                    while (true) 
                    {
                        try 
                        {
                            int newReceiptNumber = inputHandler.inputInt("\nIntroduza o novo número da fatura: ");
                            if (newReceiptNumber <= 0) 
                            {
                                System.out.println("\nO número da fatura deve ser um inteiro positivo. Tente novamente.\n");
                                continue;
                            }
                            
                            Receipt receiptSearched = searchReceiptByNumber(newReceiptNumber);

                            if (receiptSearched != null) 
                            {
                                System.out.print("\nEsse número já está atribuído a outra fatura.");
                            } 
                            else 
                            {
                                receipt.setNumber(newReceiptNumber);
                                System.out.println("\nNúmero da fatura atualizado com sucesso!\n");
                                break;
                            }
                        } 
                        catch (NumberFormatException e) 
                        {
                            System.out.println("\nEntrada inválida. Insira um número inteiro válido.\n");
                        }
                    }
                }
                case 4 -> 
                {
                    editProducts(receipt); 
                }
                case 5 -> 
                {
                    System.out.println("\nFatura atualizada com sucesso!");
                    editing = false;
                }
                default -> 
                {
                    System.out.println("\nOpção inválida.");
                }
            }
        }
    }
    
    private void editProducts(Receipt receipt) 
    {
        List<Product> products = receipt.getProducts();
        boolean edit = true;
    
        while (edit) 
        {
            System.out.println("\nLista de produtos da fatura:\n");

            int total = products.size();
            int max = receipt.getProducts().size();

            for (int i = 0; i < total; i++) 
            {
                System.out.println("\nProduto " + (i + 1) + " - " + products.get(i).getName());
            }
            System.out.println("\n 1 - Adicionar um produto");
            System.out.println(" 2 - Editar um produto (dados gerais)");
            System.out.println(" 3 - Remover um produto");
            System.out.println(" 4 - Sair\n");
    
            int option = inputHandler.validIntegerInput("\nEscolha o número: ", 1, 4);
    
            switch (option) 
            {
                case 1 -> 
                {
                    System.out.println("\nAdicionando novo produto...\n");
                    createProduct(receipt);
                }
                case 2->
                {
                    System.out.println(receipt.getProducts());
                    int productToEdit = inputHandler.validIntegerInput("\nDigite o número do produto que deseja editar: ", 1, max);
                    
                    editProductDetails(products.get(productToEdit - 1));
                }
                case 3 -> 
                {
                    if (products.isEmpty()) 
                    {
                        System.out.println("\nNão há produtos para remover.\n");
                    } 
                    else 
                    {
                        int productToRemove = inputHandler.validIntegerInput("\nDigite o número do produto que deseja remover: ", 1, max);

                        Product product = products.get(productToRemove - 1);

                        receipt.removeProduct(product);

                        System.out.println("\nProduto removido com sucesso!\n");
                    }
                }
                case 4 -> 
                {
                    System.out.println("\nEdição de produtos concluída.\n");
                    edit = false;
                }
                default -> 
                {
                    System.out.println("\nOpção inválida...\n");
                }
            }
        }
    }

    private void editProductDetails(Product product) 
    {
        boolean editing = true;
    
        while (editing) 
        {
            System.out.println("\nProduto selecionado:\n" + product);
            System.out.println("\nDigite o número do que quer editar:");
            System.out.println("1 - Nome");
            System.out.println("2 - Preço");
            System.out.println("3 - Quantidade");
            System.out.println("4 - Descrição");
            System.out.println("5 - Sair");
    
            int option = inputHandler.validIntegerInput("\nEscolha uma opção: ", 1, 6);
    
            switch (option) 
            {
                case 1 -> 
                {
                    String newName = inputHandler.inputName("\nDigite o novo nome: ");
                    product.setName(newName);
                    System.out.println("\nNome atualizado com sucesso!");
                }
                case 2 -> 
                {
                    double newPrice = inputHandler.inputDouble("\nDigite o novo preço: ");
                    product.setUnitPrice(newPrice);
                    System.out.println("\nPreço atualizado com sucesso!");
                }
                case 3 -> 
                {
                    int newQuantity = inputHandler.inputInt("\nDigite a nova quantidade: ");
                    product.setQuantity(newQuantity);
                    System.out.println("\nQuantidade atualizada com sucesso!");
                }
                case 4 -> 
                {
                    System.out.print("\nDigite a nova descrição: ");
                    String newDescription = scanner.nextLine();
                    product.setDescription(newDescription);
                    System.out.println("\nDescrição atualizada com sucesso!");
                }
                case 5 -> 
                {
                    editing = false;
                    System.out.println("\nProduto atualizado com sucesso!\n");
                }
                default -> 
                {
                    System.out.println("\nOpção inválida.\n");
                }
            }
        }
    }

    private void createClient() 
    {
        System.out.println("\n----------- Adicionar Cliente -----------\n");

        String name = inputHandler.inputName("\nDigite o nome do cliente: ");

        String nif = inputHandler.inputNIF("\nIntroduza o NIF: ");

        try 
        {
            verifyExistClient(nif);
            System.out.print("\nDigite a localização do cliente: \n");
            System.out.println("--> Continente");
            System.out.println("--> Madeira");
            System.out.println("--> Acores");
            String location = inputHandler.inputLocation("\nIntroduza: ");
            Client client = new Client(name, nif, location);
            clients.add(client);
            System.out.println("\nCliente adicionado com sucesso...");
        } catch (IllegalArgumentException e) 
        {
            System.out.println(e.getMessage());
        }
        System.out.println("\n---------------------//------------------");
    }

    private void createReceipt() 
    {
        System.out.println("\n----------- Adicionar Fatura -----------\n");

        int numberReceipt = receipts.size() + 1;

        String clientNIF = inputHandler.inputNIF("\nIntroduza o NIF: ");
    
        Client client = searchClientByNIF(clientNIF);
        if (client == null) {
            System.out.println("\nCliente com NIF " + clientNIF + " não encontrado.");
            return;
        }
    
        String date = inputHandler.inputDate("\nDigite a data (xx/xx/xxxx): ");
    
        Receipt receipt = new Receipt(numberReceipt, client, date, new ArrayList<>());
    
        System.out.println("\nAdicione os produtos à fatura:");
        while (true) 
        {
            createProduct(receipt);

            menuBoolean("\nDeseja adicionar outro produto?");

            if (!inputHandler.inputBoolean()) 
            {
                break;
            }
        }
    
        receipts.add(receipt);

        System.out.println("\nFatura adicionada com sucesso...");
        System.out.println("\n-----------------------------------------");
    }

    private void createProduct(Receipt receipt)
    {
        String code = String.valueOf(receipt.getProducts().size() + 1);

        String productName = inputHandler.inputName("\nNome do Produto: ");

        System.out.print("\nDigite a descrição do produto: ");
        String description = scanner.nextLine();

        int quantity = inputHandler.inputInt("\nQuantidade: ");

        double price = inputHandler.inputDouble("\nPreço Unitário: ");
        
        System.out.println("\nDigite o tipo de produto: ");
        System.out.println("1 - Alimentar");
        System.out.println("2 - Farmácia");
        int type = inputHandler.validIntegerInput("\nEscolha uma opção: ",1,2);

        switch (type) 
        {
            case 1 -> 
            {
                menuBoolean("\nO produto é biológico? ");
                boolean organic = inputHandler.inputBoolean();

                System.out.print("\nDigite o tipo de taxa: \n");
                System.out.println("--> Reduzida");
                System.out.println("--> Intermedia");
                System.out.println("--> Normal");
                String taxType = inputHandler.inputTaxType("\nIntroduza: ");

                FoodProduct foodProduct;
                switch (taxType.toLowerCase()) 
                {
                    case "reduzida" -> 
                    {
                        List<String> certifications = inputHandler.inputCertifications("\nDigite uma certificação: ");
                        foodProduct = new ReducedIVA(code, productName, description, quantity, price, organic, taxType,  certifications);
                    }
                    case "intermedia" -> 
                    {
                        System.out.println("\nCategorias:");
                        System.out.println("--> Congelados");
                        System.out.println("--> Enlatados");
                        System.out.println("--> Vinhos");

                        String chosenCategory = inputHandler.inputFoodCategory("\nDigite o nome da categoria: ");
                        foodProduct = switch (chosenCategory) 
                        {
                            case "congelados" -> new Frozen(code, productName, description, quantity, price, organic, taxType, chosenCategory);
                            case "enlatados" -> new Wines(code, productName, description, quantity, price, organic, taxType, chosenCategory);
                            case "vinhos" -> new Canned(code, productName, description, quantity, price, organic, taxType, chosenCategory);
                            default -> throw new IllegalArgumentException("\nCategoria intermedia inválida.");
                        };
                    }
                    case "normal" -> 
                    {
                        foodProduct = new NormalIVA(code, productName, description, quantity, price, organic, taxType);
                    }
                    default -> throw new IllegalArgumentException("Tipo de taxa inválida.");
                }
                receipt.addProduct(foodProduct);
            }
    
            case 2 -> 
            {
                menuBoolean("\nO produto exige prescrição? ");
                boolean prescrition = inputHandler.inputBoolean();

                if (prescrition) 
                {
                    String medicName = inputHandler.inputName("\nDigite o nome do médico: ");
                    WithPrescription withPrescription = new WithPrescription(code, productName, description, quantity, price, prescrition, medicName);
                    receipt.addProduct(withPrescription);
                } else 
                {
                    System.out.println("\nCategorias:");
                    System.out.println("--> Animais");
                    System.out.println("--> Bebe");
                    System.out.println("--> Bem estar");
                    System.out.println("--> Beleza");
                    System.out.println("--> Outro");

                    String category = inputHandler.inputPharmacyCategory("\nDigite a categoria do produto: ");

                    WithouthPrescription withouthPrescription = switch (category) 
                    {
                        case "animais" -> new Animal(code, productName, description, quantity, price, prescrition, category);
                        case "bebes" -> new Baby(code, productName, description, quantity, price, prescrition, category);
                        case "bem estar" -> new WellBeing(code, productName, description, quantity, price, prescrition, category);
                        case "beleza" -> new Beauty(code, productName, description, quantity, price, prescrition, category);
                        case "outro" -> new Other(code, productName, description, quantity, price, prescrition, category);
                        default -> throw new IllegalArgumentException("\nCategoria inválida.");
                    };
                    receipt.addProduct(withouthPrescription);
                }
            }
    
            default -> System.out.println("\nTipo de produto inválido. Escolha 1 ou 2.");
        }
    }

    private void listClients() 
    {
        System.out.println("\n----------------------------- Lista de Clientes -----------------------------\n");
        System.out.printf("%-30s %-30s %-30s%n", "Nome", "NIF", "Localização");
        System.out.println("-----------------------------------------------------------------------------\n");

        if (clients.isEmpty()) 
        {
            System.out.println("Nenhum cliente cadastrado.");
        } 
        else 
        {
            for (Client client : clients) 
            {
                System.out.printf("%-30s %-30s %-30s\n", 
                    client.getName(), 
                    client.getNif(), 
                    client.getLocation());
            }
        }
        System.out.println("\n------------------------------------ // -------------------------------------");
    }

    private void listReceipts() 
    {
        System.out.println("\n--------------------------------------------------------------------  Lista de Faturas  -------------------------------------------------------------------\n");
        System.out.printf("%-25s  %-25s  %-25s  %-25s  %-25s  %-25s%n",
                         "Número", "Cliente", "Localização", "N.º de Prod.", "Valor Total S/ IVA", "Valor Total C/ IVA");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        if (receipts.isEmpty()) 
        {
            System.out.println("Nenhuma fatura cadastrada.");
        } 
        else 
        {
            for (Receipt receipt : receipts) 
            {
                System.out.printf("%-26d %-26s %-26s %-26d %-26.2f %-26.2f%n",
                                    receipt.getNumber(),
                                    receipt.getClient().getName(),
                                    receipt.getClient().getLocation(),
                                    receipt.getProducts().size(),
                                    receipt.valueWithoutTax(),
                                    receipt.calculateTotalWithIVA());
            }
        }
        System.out.println("\n---------------------------------------------------------------------------- // ----------------------------------------------------------------------------");
    }

    private void seeReceipt() 
    {
        int number = inputHandler.inputInt("\nDigite o número da fatura: ");
    
        Receipt receipt = searchReceiptByNumber(number);
        if (receipt == null) 
        {
            System.out.println("\nFatura não encontrada.");
            return;
        }
    
        System.out.println(receipt);
    }

    private void loadTextData(String fileName) 
    {
        File file = new File(fileName);
        if (!file.exists()) 
        {
            System.out.println("\nFicheiro desconhecido com esse nome.\n");
            return; 
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line;
            boolean readingClients = true;
            Receipt currentReceipt = null;
    
            while ((line = reader.readLine()) != null) 
            {
                if (readingClients && line.trim().isEmpty()) 
                {
                    readingClients = false;
                    continue;
                }
                if (readingClients) 
                {
                    String[] parts = line.split("#");
                    if (parts.length == 3) 
                    {
                        String name = parts[0];
                        String clientNIF = parts[1];
                        String date = parts[2];

                        Client client = new Client(name, clientNIF, date);
                        clients.add(client);
                    }
                } 
                else 
                {
                    if (line.equals("---")) 
                    {
                        currentReceipt = null;
                        continue;
                    }
    
                    String[] parts = line.split("#");
        
                    if (parts.length == 3 && currentReceipt == null) 
                    {
                        int number = Integer.parseInt(parts[0]);
                        String clientNIF = parts[1];
                        String date = parts[2];
        
                        Client client = searchClientByNIF(clientNIF);
                        if (client != null) 
                        {
                            Receipt existingReceipt = searchReceiptByNumber(number);
                            if (existingReceipt != null) 
                            {
                                int maxNumber = number;
                                for (Receipt receipt : receipts) 
                                {
                                    if (receipt.getNumber() > maxNumber) 
                                    {
                                        maxNumber = receipt.getNumber();
                                    }
                                }
                                number = maxNumber + 1;
                            }
                            currentReceipt = new Receipt(number, client, date, new ArrayList<>());
                            receipts.add(currentReceipt);
                        } else 
                        {
                            System.out.println("Cliente com NIF " + clientNIF + " não encontrado.");
                        }
                    } 
                    else if (parts.length >= 8 && currentReceipt != null) 
                    {
                        String productType = parts[0];
                        String productID = parts[1];
                        String productName = parts[2];
                        String productDescription = parts[3];
                        int quantity = Integer.parseInt(parts[4]);
                        double price = Double.parseDouble(parts[5]);
                        boolean booleanVariable = Boolean.parseBoolean(parts[6]);
                        String typeOrMedic = parts[7];
                        String extraType = null;
                        List<String> certifications = null;
    
                        if (parts.length > 8) 
                        {
                            if (parts[8].contains("ISO22000") || parts[8].contains("FSSC22000") 
                                || parts[8].contains("HACCP") || parts[8].contains("GMP")) 
                            {
                                certifications = List.of(parts[8].split(","));
                            } 
                            else 
                            {
                                extraType = parts[8];
                            }
                        } 
    
                        Product product;
    
                        switch (productType) 
                        {
                            case "NormalIVA" -> 
                                product = new NormalIVA(productID, productName, productDescription, quantity, price, booleanVariable,  typeOrMedic);
                            case "ReducedIVA" -> 
                                product = new ReducedIVA(productID, productName, productDescription, quantity, price, booleanVariable,  typeOrMedic, certifications);
                            case "Frozen" -> 
                                product = new Frozen(productID, productName, productDescription, quantity, price, booleanVariable,  typeOrMedic, extraType);
                            case "Wines" -> 
                                product = new Wines(productID, productName, productDescription, quantity, price, booleanVariable,  typeOrMedic, extraType);
                            case "Canned" -> 
                                product = new Canned(productID, productName, productDescription, quantity, price, booleanVariable,  typeOrMedic, extraType);
                            case "WithPrescription" -> 
                                product = new WithPrescription(productID, productName, productDescription, quantity, price, booleanVariable, typeOrMedic);
                            case "Other" -> 
                                product = new Other(productID, productName, productDescription, quantity, price, booleanVariable, typeOrMedic);
                            case "Animal" -> 
                                product = new Animal(productID, productName, productDescription, quantity, price, booleanVariable,  typeOrMedic);
                            case "Beauty" -> 
                                product = new Beauty(productID, productName, productDescription, quantity, price, booleanVariable, typeOrMedic);
                            case "WellBeing" -> 
                                product = new WellBeing(productID, productName, productDescription, quantity, price, booleanVariable, typeOrMedic);
                            case "Baby" -> 
                                product = new Baby(productID, productName, productDescription, quantity, price, booleanVariable,  typeOrMedic);
                            default ->
                            {
                                System.out.println("Tipo de produto desconhecido: " + productType);
                                continue;
                            }
                        }
    
                        currentReceipt.addProduct(product);
                    }
                }
            }
            reader.close();
            System.out.println("\nFicheiro descarregado!");
        } catch (IOException e) 
        {
            System.out.println("Erro ao ler o arquivo de texto: " + e.getMessage());
        }
    }

    private void saveTextData(String fileName) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) 
        {
            for (Client client : clients) 
            {
                String clientLine = client.getName() + "#" + client.getNif() + "#" + client.getLocation();
                writer.write(clientLine);
                writer.newLine();
            }
    
            for (Receipt receipt : receipts) 
            {
                writer.write("\n---");
                writer.newLine();

                String receiptLine = receipt.getNumber() + "#" + receipt.getClient().getNif() + "#" + receipt.getDate();
                writer.write(receiptLine);
                writer.newLine();
    
                for (Product product : receipt.getProducts()) 
                {
                    String productLine = formatProductLine(product);
                    writer.write(productLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    private String formatProductLine(Product product) 
    {
        String productLine = 
            product.getCode() + "#" +
            product.getName() + "#" +
            product.getDescription() + "#" +
            product.getQuantity() + "#" +
            product.getUnitPrice() + "#" +
            product.exportDetails();
    
        return productLine;
    }



    private void verifyExistClient(String nif) 
    {
        if (searchClientByNIF(nif) != null) 
        {
            throw new IllegalArgumentException("\nCliente já existente.");
        }
    }

    private Receipt searchReceiptByNumber(int number) 
    {
        for (Receipt receipt : receipts) 
        {
            if (receipt.getNumber() == number) 
            {
                return receipt;
            }
        }
        return null; 
    }

    private Client searchClientByNIF(String nif) 
    {
        for (Client client : clients) 
        {
            if (client.getNif().equals(nif)) 
            {
                return client;
            }
        }
        return null;
    }

    private void loadDataFromArquive() 
    {
        File objectFile = new File("Clients_receipts.obj");
        String textFile = "Clients_receipts.txt";
        if (objectFile.exists()) 
        {
            try
            {
                FileInputStream fos = new FileInputStream(objectFile);
                ObjectInputStream ois = new ObjectInputStream(fos);
                clients = (ArrayList<Client>) ois.readObject();
                receipts = (ArrayList<Receipt>) ois.readObject();
                ois.close();
                System.out.println("\nDados carregados do arquivo de objetos.");
            } catch (IOException | ClassNotFoundException e) 
            {
                System.err.println("\nErro ao carregar arquivo de objetos ");
            }
        } else 
        {
            System.out.println("\nArquivo de objetos não encontrado. A descarregar o arquivo de texto.");
            loadTextData(textFile);
        }
    }

    private void saveDataToArchive() 
    {
        File objectFile = new File("Clients_receipts.obj");
        try 
        {
            FileOutputStream fos = new FileOutputStream(objectFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(clients);
            oos.writeObject(receipts);
            oos.close();
            System.out.println("\nDados salvos no arquivo de objetos.");
        } catch (IOException e) 
        {
            System.out.println("\nErro ao salvar arquivo de objetos, salvando o de texto " + e.getMessage());
        }
    }

    @Override
    public String toString() 
    {
        System.out.println("\n------------------------ Estatísticas -------------------------\n");

        double totalWithouthIVA = 0.0;
        double totalIVA = 0.0;
        int totalProducts = 0;

        for (Receipt receipt : receipts) 
        {
            totalWithouthIVA += receipt.valueWithoutTax();
            totalProducts += receipt.getProducts().size();
            totalIVA += receipt.calculateTotalIVA();
        }

        String result = "Número de Faturas: " + receipts.size() + "\n";
        result += "Número de Produtos: " + totalProducts + "\n";
        result += "Valor total sem IVA: " + String.format("%.2f", totalWithouthIVA) + "\n";
        result += "Valor total do IVA: " + String.format("%.2f", totalIVA) + "\n";
        result += "Valor total com IVA: " + String.format("%.2f", (totalWithouthIVA + totalIVA)) + "\n";
        result += "\n----------------------------- // ------------------------------\n";

        return result;
    }
}