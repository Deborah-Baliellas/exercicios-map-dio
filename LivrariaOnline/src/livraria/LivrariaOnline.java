package livraria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LivrariaOnline {
    private Map<String, Livro> livrosMap;

    public LivrariaOnline() {
        this.livrosMap = new HashMap<>();
    }

    public void adicionarLivro(String link, Livro livro) {
        livrosMap.put(link, livro);
    }

    public void removerLivro(String titulo) {
        List<String> chavesRemover = new ArrayList<>();
        if (!livrosMap.isEmpty()) {
            for (Map.Entry<String, Livro> entry : livrosMap.entrySet()) {
                if (entry.getValue().getTitulo().equalsIgnoreCase(titulo)) {
                    chavesRemover.add(entry.getKey());
                }
            }
            for (String chave : chavesRemover) {
                livrosMap.remove(chave);
            }
            System.out.println("Livro removido com sucesso!");

        } else {
            System.out.println("O conjunto está vazio!");
        }
    }

    public Map<String, Livro> exibirLivrosOrdenadosPorPreco() {
        List<Map.Entry<String, Livro>> livrosParaOrdenarPorPreco = new ArrayList<>(livrosMap.entrySet());

        Collections.sort(livrosParaOrdenarPorPreco, new ComparatorPorPreco());

        Map<String, Livro> livrosOrdenadosPorPreco = new LinkedHashMap<>();

        for (Map.Entry<String, Livro> entry : livrosParaOrdenarPorPreco) {
            livrosOrdenadosPorPreco.put(entry.getKey(), entry.getValue());
        }
        return livrosOrdenadosPorPreco;

    }

    public Map<String, Livro> pesquisarLivrosPorAutor(String autor) {
        Map<String, Livro> livrosPorAutor = new LinkedHashMap<>();
        if (!livrosMap.isEmpty()) {
            for (Map.Entry<String, Livro> entry : livrosMap.entrySet()) {
                if (entry.getValue().getAutor().equalsIgnoreCase(autor)) {
                    livrosPorAutor.put(entry.getKey(), entry.getValue());
                }
            }
            return livrosPorAutor;
        } else {
            throw new RuntimeException("Autor não encontrado");
        }
    }

    public List<Livro> obterLivroMaisCaro() {
        List<Livro> listaLivroMaisCaro = new ArrayList<>();
        double livroMaisCaro = Double.MIN_VALUE;
        if (!livrosMap.isEmpty()) {
            for (Map.Entry<String,Livro> entry : livrosMap.entrySet()) {
                if (entry.getValue().getPreco() > livroMaisCaro) {
                    livroMaisCaro = entry.getValue().getPreco();
                }
            }
        } else {
            System.out.println("O conjunto está vazio");
        }

        for (Map.Entry<String, Livro> entry : livrosMap.entrySet()) {
            if (entry.getValue().getPreco() == livroMaisCaro) {
                Livro livroComPrecoMaisAlto = livrosMap.get(entry.getKey());
                listaLivroMaisCaro.add(livroComPrecoMaisAlto);
            }
        }
        return listaLivroMaisCaro;
    }

    public List<Livro> exibirLivroMaisBarato() {
        List<Livro> listaLivroMaisBarato = new ArrayList<>();
        double livroMaisBarato = Double.MAX_VALUE;
        if (!livrosMap.isEmpty()) {
            for (Map.Entry<String,Livro> entry : livrosMap.entrySet()) {
                if (entry.getValue().getPreco() < livroMaisBarato) {
                    livroMaisBarato = entry.getValue().getPreco();
                }
            }
        } else {
            System.out.println("O conjunto está vazio");
        }

        for (Map.Entry<String, Livro> entry : livrosMap.entrySet()) {
            if (entry.getValue().getPreco() == livroMaisBarato) {
                Livro livroComPrecoMaisAlto = livrosMap.get(entry.getKey());
                listaLivroMaisBarato.add(livroComPrecoMaisAlto);
            }
        }
        return listaLivroMaisBarato;
    }

    public static void main(String[] args) throws Exception {
        LivrariaOnline livrariaOnline = new LivrariaOnline();

        livrariaOnline.adicionarLivro("https://amzn.to/3EclT8Z", new Livro("1984", "George Orwell", 50d));
        livrariaOnline.adicionarLivro("https://amzn.to/47Umiun",
                new Livro("A Revolução dos Bichos", "George Orwell", 7.05d));
        livrariaOnline.adicionarLivro("https://amzn.to/3L1FFI6",
                new Livro("Caixa de Pássaros - Bird Box: Não Abra os Olhos", "Josh Malerman", 19.99d));
        livrariaOnline.adicionarLivro("https://amzn.to/3OYb9jk", new Livro("Malorie", "Josh Malerman", 5d));
        livrariaOnline.adicionarLivro("https://amzn.to/45HQE1L",
                new Livro("E Não Sobrou Nenhum", "Agatha Christie", 50d));
        livrariaOnline.adicionarLivro("https://amzn.to/45u86q4",
                new Livro("Assassinato no Expresso do Oriente", "Agatha Christie", 5d));

        // Exibe todos os livros ordenados por preço
        System.out.println("Livros ordenados por preço: \n" + livrariaOnline.exibirLivrosOrdenadosPorPreco());

        // Pesquisa livros por autor
        String autorPesquisa = "Josh Malerman";
        System.out.println("Pesquisa de livros por autor: " + livrariaOnline.pesquisarLivrosPorAutor(autorPesquisa));

        // Obtém e exibe o livro mais caro
        System.out.println("Livro mais caro: " + livrariaOnline.obterLivroMaisCaro());

        // Obtém e exibe o livro mais barato
        System.out.println("Livro mais barato: " + livrariaOnline.exibirLivroMaisBarato());

        // Remover um livro pelo título
        livrariaOnline.removerLivro("1984");
        System.out.println(livrariaOnline.livrosMap);

    }

}
