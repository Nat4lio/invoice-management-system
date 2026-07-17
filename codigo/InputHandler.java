
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler
{
    private final Scanner scanner;

    public InputHandler() 
    {
        this.scanner = new Scanner(System.in);
    }

    public InputHandler(Scanner scanner) 
    {
        this.scanner = scanner;
    }
        
    public String inputName(String prompt)
    {
        while (true) 
        {
            System.out.print(prompt);
            String name = scanner.nextLine();
            try 
            {
                verifyName(name);
                return name;
            } catch (IllegalArgumentException e) 
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public String inputNIF(String prompt)
    {
        while (true) 
        {
            System.out.print(prompt);
            String nif = scanner.nextLine();
            try 
            {
                verifyNIF(nif);
                return nif;
            } catch (IllegalArgumentException e) 
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public String inputLocation(String prompt)
    {
        while (true) 
        {
            System.out.print(prompt);
            String location = scanner.nextLine();
            try {
                verifyLocation(location);
                return location; 
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public int inputInt(String prompt) 
    {
        while (true) 
        {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) 
            {  
                System.out.println("Não é permitido deixar o campo vazio.");
                continue; 
            }

            try 
            {
                int number = Integer.parseInt(input); 
                verifyInt(number); 
                return number; 
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Digite um número inteiro válido.");
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println(e.getMessage());  
            }
        }
    }
    
    public double inputDouble(String prompt) 
    {
        while (true) 
        {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
    
            if (input.isEmpty()) {  
                System.out.println("O campo não pode ser vazio.");
                continue; 
            }
    
            try {
                double number = Double.parseDouble(input);
                verifyDouble(number);  
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número decimal válido.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); 
            }
        }
    }

    public String inputDate(String prompt)
    {
        while (true) 
        { 
            System.out.print(prompt);
            String date = scanner.nextLine();
            try 
            {
                verifyDate(date);
                return date;
            } catch (NumberFormatException e) 
            {
                System.out.println("Respeite o formato da data.");
            }
        }
    }
    
    public boolean inputBoolean() 
    {
        while (true) 
        {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("true")) 
            {
                return true;
            } else if (input.equalsIgnoreCase("false")) 
            {
                return false;
            } else 
            {
                System.out.println("Entrada inválida. Digite 'true' ou 'false'.");
                System.out.print("\nEscreva corretamente:");
            }
        }
    }

    public String inputPharmacyCategory(String prompt)
    {
        while (true) 
        {
            System.out.print(prompt);
            String category = scanner.nextLine();
            try 
            {
                verifyCategory(category);
                return category; 
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<String> inputCertifications(String prompt) 
    {
        List<String> certifications = new ArrayList<>();
        String[] validCertifications = {"ISO22000", "FSSC22000", "HACCP", "GMP"};
        
        System.out.println("\nCertificações disponíveis:");
        for (String cert : validCertifications) 
        {
            System.out.println(" - " + cert);
        }

        System.out.println("Insira uma ou mais certificações (máximo 4). Digite 'fim' para concluir.");

        while (certifications.size() < 4) 
        {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equalsIgnoreCase("FIM") && !certifications.isEmpty()) 
            {
                break;
            }

            boolean isValid = false;
            for (String validCert : validCertifications) 
            {
                if (validCert.equals(input)) 
                {
                    isValid = true;
                    break;
                }
            }

            if (!isValid) {
                System.out.println("Certificação inválida ou não escolheu pelo menos um. Escolha entre: ISO22000, FSSC22000, HACCP, GMP.");
            } else if 
            (certifications.contains(input)) 
            {
                System.out.println("Certificação já adicionada.");
            } else {
                certifications.add(input);
            }
        }

        return certifications;
    }

    public String inputFoodCategory(String prompt)
    {
        while (true) 
        {
            System.out.print(prompt);
            String taxType = scanner.nextLine();
            if (taxType.equalsIgnoreCase("congelados") || taxType.equalsIgnoreCase("enlatados")
                || taxType.equalsIgnoreCase("vinhos")) 
                {
                return taxType;
            } else {
                System.out.println("Categoria inválida. Escolha entre 'congelados', 'enlatados' ou 'vinho'.");
            }
        }
    }

    public String inputTaxType(String prompt)
    {
        while (true) 
        {
            System.out.print(prompt);
            String taxType = scanner.nextLine();
            if (taxType.equalsIgnoreCase("Reduzida") || taxType.equalsIgnoreCase("Intermedia")
                || taxType.equalsIgnoreCase("Normal")) {
                return taxType;
            } else {
                System.out.println("Tipo de taxa inválido. Escolha entre 'Reduzida', 'Intermedia' ou 'Normal'.");
            }
        }
    }

    private void verifyName(String name) 
    {
        if (name == null || name.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        for (char c : name.toCharArray()) 
        {
            if (Character.isDigit(c)) 
            {
                throw new IllegalArgumentException("O nome não pode conter números.");
            }
        }
    }

    private void verifyNIF(String nif) 
    {
        for (char c : nif.toCharArray()) 
        {
            if (!Character.isDigit(c)) 
            {
                throw new IllegalArgumentException("O NIF deve conter apenas números.");
            }
        }
        if (nif.isEmpty()) 
        {
            throw new IllegalArgumentException("O NIF não pode ser nulo ou vazio.");
        }
        if (nif.length() != 9) 
        {
            throw new IllegalArgumentException("O NIF deve ter 9 dígitos.");
        }
    }

    private void verifyLocation(String location) 
    {
        if (location == null || location.isEmpty()) 
        {
            throw new IllegalArgumentException("A localização não pode ser nula ou vazia.");
        }

        for (char c : location.toCharArray()) 
        {
            if (Character.isDigit(c)) 
            {
                throw new IllegalArgumentException("O nome não pode conter números.");
            }
        }

        if (!location.equalsIgnoreCase("Continente") 
            && !location.equalsIgnoreCase("Madeira") 
            && !location.equalsIgnoreCase("Acores")) 
        {
            throw new IllegalArgumentException("A localização deve ser Continente, Madeira ou Acores.");
        }
    }

    private void verifyDate(String date)
    {
        if (date.length() != 10 || date.charAt(2) != '/' || date.charAt(5) != '/') 
        {
            throw new NumberFormatException("Data inválida. Respeite o formato dd/mm/yyyy.");
        }

        for (int i = 0; i < date.length(); i++) 
        {
            char c = date.charAt(i);
            if (i != 2 && i != 5) 
            { 
                if (!Character.isDigit(c)) 
                {
                    throw new NumberFormatException("Data inválida. Apenas dígitos são permitidos.");
                }
            }
        }

        String[] parts = date.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        switch (month) 
        {
            case 2 -> 
            {
                if (day < 1 || day > 28)
                {
                    throw new NumberFormatException("Data inválida. Fevereiro tem 28 dias.");
                }
            }
            case 4, 6, 9, 11 -> 
            {
                if (day < 1 || day > 30)
                {
                    throw new NumberFormatException("Data inválida. O mês " + month + " tem 30 dias.");
                }
            }
            case 1, 3, 5, 7, 8, 10, 12 -> 
            {
                if (day < 1 || day > 31) 
                {
                throw new NumberFormatException("Data inválida. O mês " + month + " tem 31 dias.");
                }
            }
            default -> 
            {
                throw new NumberFormatException("Digita um mês válido.");
            }
        }
    }
 
    private void verifyInt(int number)
    {
        if (number == 0) 
        {
            throw new IllegalArgumentException("O número não pode ser 0.");
        }
    
        if (number < 0) 
        {
            throw new IllegalArgumentException("O número deve ser positiva.");
        }
    }

    private void verifyDouble(double number) 
    {
        if (number < 0 || number == 0) 
        {
            throw new IllegalArgumentException("O valor deve ser um número decimal não negativo.");
        }
    }

    private void verifyCategory(String category)
    {
        if (category == null || category.isEmpty()) 
        {
            throw new IllegalArgumentException("A categoria não pode ser nula ou vazia.");
        }

        for (char c : category.toCharArray()) 
        {
            if (Character.isDigit(c)) 
            {
                throw new IllegalArgumentException("O nome não pode conter números.");
            }
        }

        if (!category.equalsIgnoreCase("Animais") 
            && !category.equalsIgnoreCase("Bebe") 
            && !category.equalsIgnoreCase("Beleza")
            && !category.equalsIgnoreCase("Bem estar")
            && !category.equalsIgnoreCase("Outro")) 
        {
            throw new IllegalArgumentException("A categoria deve ser Animais, Bebe, Beleza, Bem-estar ou Outro.");
        }
    }

    public int validIntegerInput(String text, int min, int max) 
    {
        int option = -1;
        while (true) 
        {
            System.out.print(text);
            try 
            {
                option = Integer.parseInt(scanner.nextLine());
                if (option >= min && option <= max) 
                {
                    break;
                } 
                else 
                {
                    System.out.println("Por favor, insira um número entre " + min + " e " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número entre " + min + " e " + max);
            }
        }
        return option;
    }

    @Override
    public String toString() 
    {
        return "Classe responsável por receber, processar e proteger inputs do usuário\n";
    }
}