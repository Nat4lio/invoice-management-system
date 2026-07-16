import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class POOFSApp {
    private GestorDeClientes gestorClientes;
    private GestorFaturas gestorFaturas;
    private Scanner scanner;
    private final String CLIENTES_FILE = "Clientes.txt";

    public POOFSApp() {
        this.gestorClientes = new GestorDeClientes();
        this.gestorFaturas = new GestorFaturas();
        this.scanner = new Scanner(System.in);
        carregarClientesDeArquivo();
    }

    public static void main(String[] args) {
        POOFSApp app = new POOFSApp();
        app.iniciar();
    }

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    criarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    eliminarCliente();
                    break;
                case 4:
                    criarFatura();
                    break;
                case 5:
                    listarFaturas();
                    break;
                case 6:
                    visualizarFatura();
                    break;
                case 7:
                    adicionarProdutoAlimentar();
                    break;
                case 8:
                    adicionarProdutoFarmacia();
                    break;
                case 0:
                    continuar = false;
                    salvarClientesParaArquivo();
                    System.out.println("Encerrando o programa ...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("-----------Bem-vindo ao POOFSApp-----------");
        System.out.println("Menu:");
        System.out.println("1 -> Criar Cliente");
        System.out.println("2 -> Listar Clientes");
        System.out.println("3 -> Eliminar Cliente");
        System.out.println("4 -> Criar Fatura");
        System.out.println("5 -> Listar Faturas");
        System.out.println("6 -> Visualizar Fatura");
        System.out.println("7 -> Adicionar Produto Alimentar a uma Fatura");
        System.out.println("8 -> Adicionar Produto de Farmácia a uma Fatura");
        System.out.println("0 -> Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void carregarClientesDeArquivo() {
        File file = new File(CLIENTES_FILE);
        if (!file.exists()) {
            return; // Se o arquivo não existir, não faz nada
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("#");
                if (partes.length == 3) {
                    Cliente cliente = new Cliente(partes[0], partes[1], partes[2]);
                    gestorClientes.adicionarCliente(cliente);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de clientes: " + e.getMessage());
        }
    }

    private void salvarClientesParaArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTES_FILE))) {
            List<Cliente> clientes = gestorClientes.listarClientes();
            for (Cliente cliente : clientes) {
                String linha = cliente.getNome() + "#" + cliente.getNif() + "#" + cliente.getLocalizacao();
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo de clientes: " + e.getMessage());
        }
    }

    private void criarCliente() {
        String nome;
        while (true) {
            System.out.print("Digite o nome do cliente: ");
            nome = scanner.nextLine();
            try {
                Cliente.verificarNome(nome);
                break; // Nome válido, sair do loop
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        String nif;
        while (true) {
            System.out.print("Digite o NIF do cliente: ");
            nif = scanner.nextLine();
            try {
                Cliente.verificarNif(nif);
                verificarClienteExistente(nif); // Verificação de duplicidade
                break; // NIF válido, sair do loop
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        String localizacao;
        while (true) {
            System.out.print("Digite a localização do cliente (Continente, Madeira, Açores): ");
            localizacao = scanner.nextLine();
            try {
                Cliente.verificarLocalizacao(localizacao);
                break; // Localização válida, sair do loop
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Cliente cliente = new Cliente(nome, nif, localizacao);
        gestorClientes.adicionarCliente(cliente);
        System.out.println("Cliente adicionado com sucesso.");
    }

    private void listarClientes() {
        for (Cliente cliente : gestorClientes.listarClientes()) {
            System.out.println("Nome: " + cliente.getNome() + ", NIF: " + cliente.getNif() + ", Localização: " + cliente.getLocalizacao());
        }
    }

    private void eliminarCliente() {
        System.out.print("Digite o NIF do cliente: ");
        String nif = scanner.nextLine();
        if (gestorClientes.removerCliente(nif)) {
            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void criarFatura() {
        System.out.print("Digite o número da fatura: ");
        String numero = scanner.nextLine();
        System.out.print("Digite o NIF do cliente: ");
        String nif = scanner.nextLine();
        Cliente cliente = gestorClientes.buscarClientePorNIF(nif);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        Fatura fatura = new Fatura(numero, cliente, new Date());
        gestorFaturas.adicionarFatura(fatura);
        System.out.println("Fatura adicionada com sucesso.");
    }

    private void listarFaturas() {
        for (Fatura fatura : gestorFaturas.listarFaturas()) {
            System.out.println("Número: " + fatura.getNumero() +
                    ", Cliente: " + fatura.getCliente().getNome() +
                    ", Localização: " + fatura.getCliente().getLocalizacao() +
                    ", Produtos: " + fatura.getProdutos().size() +
                    ", Total Sem IVA: " + fatura.calcularTotalSemIVA() +
                    ", Total Com IVA: " + fatura.calcularTotalComIVA());
        }
    }

    private void visualizarFatura() {
        System.out.print("Digite o número da fatura: ");
        String numero = scanner.nextLine();
        Fatura fatura = gestorFaturas.buscarFaturaPorNumero(numero);
        if (fatura == null) {
            System.out.println("Fatura não encontrada.");
            return;
        }
        System.out.println("Número: " + fatura.getNumero());
        System.out.println("Cliente: " + fatura.getCliente().getNome() +
                ", Localização: " + fatura.getCliente().getLocalizacao());
        for (Produto produto : fatura.getProdutos()) {
            System.out.println(produto);
            System.out.println("Total Sem IVA: " + produto.getQuantidade() * produto.getValorUnitario() +
                    ", IVA: " + produto.calcularIVA(fatura.getCliente()) +
                    ", Total Com IVA: " + produto.getQuantidade() * (produto.getValorUnitario() + produto.calcularIVA(fatura.getCliente())) );
        }
        System.out.println("Total Sem IVA: " + fatura.calcularTotalSemIVA());
        System.out.println("Total do IVA: " + fatura.calcularTotalIVA());
        System.out.println("Total Com IVA: " + fatura.calcularTotalComIVA());
    }

    private void adicionarProdutoAlimentar() {
        System.out.print("Digite o número da fatura: ");
        String numeroFatura = scanner.nextLine();
        Fatura fatura = gestorFaturas.buscarFaturaPorNumero(numeroFatura);
        if (fatura == null) {
            System.out.println("Fatura não encontrada.");
            return;
        }

        System.out.print("Digite o código do produto: ");
        String codigo = scanner.nextLine();
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a descrição do produto: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o valor unitário (sem IVA): ");
        double valorUnitario = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("O produto é biológico? (true/false): ");
        boolean biologico = scanner.nextBoolean();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Digite o tipo de taxa (Reduzida, Intermediaria, Normal): ");
        String tipoTaxa = scanner.nextLine();

        System.out.print("Digite as certificações (separadas por vírgula, máximo 4): ");
        String certificacoesStr = scanner.nextLine();
        List<String> certificacoes = List.of(certificacoesStr.split(","));

        ProdutoAlimentar produto = new ProdutoAlimentar(codigo, nome, descricao, quantidade, valorUnitario, biologico, tipoTaxa, certificacoes);
        fatura.adicionarProduto(produto);
        System.out.println("Produto alimentar adicionado com sucesso.");
    }

    private void adicionarProdutoFarmacia() {
        System.out.print("Digite o número da fatura: ");
        String numeroFatura = scanner.nextLine();
        Fatura fatura = gestorFaturas.buscarFaturaPorNumero(numeroFatura);
        if (fatura == null) {
            System.out.println("Fatura não encontrada.");
            return;
        }

        System.out.print("Digite o código do produto: ");
        String codigo = scanner.nextLine();
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a descrição do produto: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o valor unitário (sem IVA): ");
        double valorUnitario = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("O produto requer prescrição? (true/false): ");
        boolean requerPrescricao = scanner.nextBoolean();
        scanner.nextLine(); // Consumir a nova linha

        String nomeMedico = "";
        String categoria = "";
        if (requerPrescricao) {
            System.out.print("Digite o nome do médico: ");
            nomeMedico = scanner.nextLine();
        } else {
            System.out.print("Digite a categoria do produto (beleza, bem-estar, etc.): ");
            categoria = scanner.nextLine();
        }

        ProdutoFarmacia produto = new ProdutoFarmacia(codigo, nome, descricao, quantidade, valorUnitario, requerPrescricao, nomeMedico, categoria);
        fatura.adicionarProduto(produto);
        System.out.println("Produto de farmácia adicionado com sucesso.");
    }

    private void verificarClienteExistente(String nif) {
        if (gestorClientes.buscarClientePorNIF(nif) != null) {
            throw new IllegalArgumentException("Cliente com este NIF já existe.");
        }
    }
}