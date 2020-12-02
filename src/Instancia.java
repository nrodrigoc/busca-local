import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Instancia {

    //Colunas
    //private final Integer ID_PEDIDO = 0;
    private final Integer DATA_MINIMA = 0;
    private final Integer DURACAO = 1;
    private final Integer DATA_ENTREGA = 2;
    private final Integer MULTA_ATRASO = 3;

    // Primeira dimensão: id do pedido - 1
    // Segunda dimensão: tamanho 4, 1 para cada requisito ( O ID do PEDIDO fica implícito na quantidade de linhas)
    private Integer[][] pedido_requisito;

    public Instancia(String filename) {

        int n_de_pedidos = 0;

        File file =  new File(".\\instance\\" + filename);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));


            // Lê todas as linhas do arquivo
            List<String> lines = br.lines().collect(Collectors.toList());

            // Remove as linhas inuteis
            lines.remove(0);
            lines.remove(1);
            lines.remove(1);

            n_de_pedidos = Integer.parseInt(lines.get(0));

            // Cria a matriz
            pedido_requisito = new Integer[n_de_pedidos][4];

            System.out.println("N de pedidos: " + n_de_pedidos);


//            for (String line : lines ) {
//                System.out.println(line);
//            }


            br.close();
        } catch (IOException e) {
            //Caso o nome da instancia esteja troll
            System.out.println("\nEnter a valid filename!\n");
            e.printStackTrace();
        }

    }
}
