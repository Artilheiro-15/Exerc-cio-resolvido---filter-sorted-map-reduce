import entities.Product;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

  public static void main(String[] args) throws Exception {
    System.out.println("==============================================");
    Scanner sc = new Scanner(System.in);

    // caminho do arquivo:   C:\Users\Guest\Documents\projetos\curso java\ARQUIVOS\resolvido.txt;
    System.out.print("Insira o caminho completo do arquivo: ");
    String path = sc.nextLine();

    //Abrir o arquivo
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      //Instanciando lista de produtos
      List<Product> list = new ArrayList<>();

      //lendo uma linha do meu arquivo
      String line = br.readLine();
      while (line != null) {
        String[] fields = line.split(",");
        list.add(new Product(fields[0], Double.parseDouble(fields[1])));
        line = br.readLine();
      }
      //Com isso Aqui eu acho o preço medio de todos os prudutos
      double avg = list
        .stream()
        //a minha lista e de produtos e eu quero pegar so o preço que e double ai tenho que usar o map
        //agora eu gerei um novo stream com a penas os preços dos produtos
        .map(p -> p.getPreço())
        .reduce(0.0, (x, y) -> x + y / list.size());

      System.out.println("Preço médio: " + String.format("%.2f", avg));

      //isso aki e um comparador de string independimente de letras maiusculas ou minusculas
      Comparator<String> comp = (s1, s2) ->
        s1.toUpperCase().compareTo(s2.toUpperCase());

      //isso aqui e para achar os nomes dos produtos que estao abaixo da media
      List<String> names = list
        .stream()
        .filter(p -> p.getPreço() < avg)
        .map(p -> p.getName())
        .sorted(comp.reversed())
        .collect(Collectors.toList());

      names.forEach(System.out::println);
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }

    System.out.println("==============================================");
    sc.close();
  }
}
