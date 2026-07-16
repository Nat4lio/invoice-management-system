
public class Cliente {
    private String nome;
    private String nif;
    private String localizacao; // "Continente", "Madeira", ou "Açores"

    public Cliente() {
        this.nome = "";
        this.nif = "";
        this.localizacao = "";
    }

    public Cliente(String nome, String nif, String localizacao) {
        verificarNome(nome);
        verificarNif(nif);
        verificarLocalizacao(localizacao);
        this.nome = nome;
        this.nif = nif;
        this.localizacao = localizacao;
    }

    public String getNome() {
        return nome;
    }

    public String getNif() {
        return nif;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setNome(String nome) {
        verificarNome(nome);
        this.nome = nome;
    }

    public void setLocalizacao(String localizacao) {
        verificarLocalizacao(localizacao);
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nome='" + nome + '\'' + ", nif='" + nif + '\'' + ", localizacao='" + localizacao + '\'' + '}';
    }

    // Métodos de verificação
    public static void verificarNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        for (char c : nome.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("O nome não pode conter números.");
            }
        }
    }

    public static void verificarNif(String nif) {
        if (nif == null || nif.isEmpty()) {
            throw new IllegalArgumentException("O NIF não pode ser nulo ou vazio.");
        }
        if (nif.length() != 9) {
            throw new IllegalArgumentException("O NIF deve ter 9 dígitos.");
        }
        for (char c : nif.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("O NIF deve conter apenas dígitos.");
            }
        }
    }

    public static void verificarLocalizacao(String localizacao) {
        if (localizacao == null || localizacao.isEmpty()) {
            throw new IllegalArgumentException("A localização não pode ser nula ou vazia.");
        }
        if (!localizacao.equals("Continente") && !localizacao.equals("Madeira") && !localizacao.equals("Açores")) {
            throw new IllegalArgumentException("A localização deve ser Continente, Madeira ou Açores.");
        }
    }
}
