package contador;

import java.util.HashMap;
import java.util.Map;

public class ContagemPalavras {
    private Map<String, Integer> contagemPalavrasMap;

    public ContagemPalavras() {
        this.contagemPalavrasMap = new HashMap<>();
    }

    public void adicionarPalavra(String palavra, Integer contagem) {
        contagemPalavrasMap.put(palavra, contagem);
    }

    public void removerPalavra(String palavra) {
        if (!contagemPalavrasMap.isEmpty()) {
            contagemPalavrasMap.remove(palavra);
        } else {
            System.out.println("O conjunto está vazio!");
        }
    }

    public int exibirContagemPalavras() { //exibe o número total de palavras que foram salvas.
        int contagemTotal = 0;
        if (!contagemPalavrasMap.isEmpty()) {
            for (int contagem : contagemPalavrasMap.values()) {
                contagemTotal += contagem;
            }
        } 
        return contagemTotal;

    }

    public String encontrarPalavraMaisFrequente() {
        String palavraMaisFrequente = null;
        int maiorContagem = 0;
        for (Map.Entry<String, Integer> entry : contagemPalavrasMap.entrySet()){
            if (entry.getValue() > maiorContagem) {
                maiorContagem = entry.getValue();
                palavraMaisFrequente = entry.getKey();
            }
        }
        return palavraMaisFrequente;
    }

    public static void main(String[] args) throws Exception {
        ContagemPalavras contagemLinguagens = new ContagemPalavras();

        // Adiciona linguagens e suas contagens
    contagemLinguagens.adicionarPalavra("Java", 2);
    contagemLinguagens.adicionarPalavra("Python", 8);
    contagemLinguagens.adicionarPalavra("JavaScript", 1);
    contagemLinguagens.adicionarPalavra("C#", 6);

    // Exibe a contagem total de linguagens
    System.out.println("Existem " + contagemLinguagens.exibirContagemPalavras() + " palavras.");

    // Encontra e exibe a linguagem mais frequente
    String linguagemMaisFrequente = contagemLinguagens.encontrarPalavraMaisFrequente();
    System.out.println("A linguagem mais frequente é: " + linguagemMaisFrequente);
    }
}
