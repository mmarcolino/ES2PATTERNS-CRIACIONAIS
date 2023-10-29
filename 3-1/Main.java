import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AplicacaoCriacaoNomes app1 = new AplicacaoCriacaoNomes(new NomeFormatoNomeSobrenome());
        AplicacaoCriacaoNomes app2 = new AplicacaoCriacaoNomes(new NomeFormatoSobrenomeNome());

        app1.criarNomeEArmazenar("Tiago Nunes");
        app1.criarNomeEArmazenar("Matheus Marcolino");

        app2.criarNomeEArmazenar("Nunes, Tiago");
        app2.criarNomeEArmazenar("marcolino, Matheus");

        app1.imprimirNomes();
        app2.imprimirNomes();
    }
}

class Nome {
    private String nome;
    private String sobrenome;

    public Nome(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }
}

interface NomeFactory {
    Nome criarNome(String entrada);
}

class NomeFormatoNomeSobrenome implements NomeFactory {
    @Override
    public Nome criarNome(String entrada) {
        String[] partes = entrada.split(" ");
        return new Nome(partes[0], partes[1]);
    }
}

class NomeFormatoSobrenomeNome implements NomeFactory {
    @Override
    public Nome criarNome(String entrada) {
        String[] partes = entrada.split(", ");
        return new Nome(partes[1], partes[0]);
    }
}

class AplicacaoCriacaoNomes {
    private NomeFactory factory;
    private List<Nome> nomes = new ArrayList<>();

    public AplicacaoCriacaoNomes(NomeFactory factory) {
        this.factory = factory;
    }

    public void criarNomeEArmazenar(String entrada) {
        Nome nome = factory.criarNome(entrada);
        nomes.add(nome);
    }

    public void imprimirNomes() {
        for (Nome nome : nomes) {
            System.out.println("Nome: " + nome.getNome() + " Sobrenome: " + nome.getSobrenome());
        }
    }
}

