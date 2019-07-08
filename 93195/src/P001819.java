import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Notas:
// Não altere o código apresentado
// Deve completar o código da alinea 2
// Deve comentar o código para garantir que vai executando parcialmente

public class P001819 {

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream fl = new PrintStream(new File("POO_1819IN.txt"));
		test(System.out); // executa e escreve na consola
		test(fl); // executa e escreve no ficheiro
		fl.close();
	}

	private static void test(PrintStream out) {
		alinea1(out);
		alinea2(out);
	}

	private static void alinea1(PrintStream out) {
		out.println("\nAlínea 1) ----------------------------------\n");

		Mercado mercado = new Mercado("O Festival de Petiscos", "Aveiro");

		Bar exp1 = new Bar(102, "MorroDeSede");
		exp1.add(new Bebida("Vinho Branco", 11.5));
		exp1.add(new Bebida("Sumo", 0));
		exp1.add(new Bebida("Infusao", 0));
		exp1.add(new Bebida("Cerveja", 6.3));
		out.println(exp1.getNome() + " tem " + exp1.getLista().size() + " bebidas");

		List<Bebida> lista = new ArrayList<>();
		lista.add(new Bebida("Limonada", 0));
		lista.add(new Bebida("Cerveja Trigo", 5.5));
		lista.add(new Bebida("Cerveja IPA", 7.5));
		Bar exp2 = new Bar(100, "PubOO", lista);
		out.println(exp2.getNome() + " tem " + exp2.totalProdutos() + " bebidas");

		Tasquinha pst1 = new Tasquinha(201, "So_Leitao", new String[] { "Sandocha", "PicaPica" });
		Tasquinha pst2 = new Tasquinha(202, "A_Chicha");
		pst2.addPrato("Assinhas");
		pst2.addPrato("Amburguere");

		mercado.add(exp1);
		mercado.add(exp2);
		mercado.add(pst1);
		mercado.add(pst2);

		out.println("PRODUTOS DE " + exp1.getNome() + ": " + exp1.items());
		out.println("PRODUTOS DE " + pst1.getNome() + ": " + pst1.items());
		out.println("PRODUTOS DE " + pst2.getNome() + ": " + pst1.items());

		out.println("MERCADO: " + mercado);
		out.println("TOTAL DE PRODUTOS DO MERCADO: " + mercado.totalItems());
		// devolve o numero de produtos disponiveis no mercado - em todas as tendas

	}

	private static void alinea2(PrintStream out) {
		out.println("\nAlínea 2) ----------------------------------\n");
		Mercado fest = null;

		// Adicione a seguir o código necessário para a leitura do ficheiro
		Scanner uafestfile = null;
		try {
			uafestfile = new Scanner(new File("uafest.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("Ficheiro não foi encontrado!");
		}
		// inicializar mercado
		String[] mercado = uafestfile.nextLine().split(";");

		fest = new Mercado(mercado[0], mercado[1]);

		while (uafestfile.hasNextLine()) {
			String line = uafestfile.nextLine();
			String[] palavras = line.split(";");
			switch (Integer.parseInt(palavras[0])) {
			case 1:
				Bar b = new Bar(Integer.parseInt(palavras[1]), palavras[2]);
				fest.add(b);

			case 2:
				if (palavras.length == 6) {
					Tasquinha tasca = new Tasquinha(Integer.parseInt(palavras[1]), palavras[2]);
					tasca.addPrato(palavras[3]);
					tasca.addPrato(palavras[4]);
					tasca.addPrato(palavras[5]);
					fest.add(tasca);
				} else if (palavras.length == 5) {
					Tasquinha tasca = new Tasquinha(Integer.parseInt(palavras[1]), palavras[2]);
					tasca.addPrato(palavras[3]);
					tasca.addPrato(palavras[4]);
					fest.add(tasca);
				}
			}

		}

		// não modifique o metodo a partir daqui. Pode comentar para executar o programa

		if (fest != null) {
			out.println("MERCADO: " + fest);
			for (Tenda s : fest.tendas()) // devolve o conjunto de todas as tendas
				out.println(s);
			out.println("-- LISTA DE PRODUTOS DO MERCADO --");
			for (String s : fest.getAllItems()) // devolve todos os produtos distintos do mercado, ordenados
				out.print(s + ", ");
			out.println();
		}
	}

}
