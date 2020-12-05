import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Instancia {

    //Colunas
    public static final Integer ID_PEDIDO = 0;
    public static final Integer DATA_MINIMA = 1;
    public static final Integer DURACAO = 2;
    public static final Integer DATA_ENTREGA = 3;
    public static final Integer MULTA_ATRASO = 4;

    // Primeira dimensão: id do pedido - 1
    // Segunda dimensão: tamanho 5, 1 para cada requisito
    private Integer[][] pedido_requisito;

    public Instancia(String filename) {

        int n_de_pedidos = 0;


        try {
            String path = new File(".").getCanonicalPath();
            path = path.replace("\\", "/");

            File file =  new File( path + "/instance/" + filename);

            BufferedReader br = new BufferedReader(new FileReader(file));


            // Lê todas as linhas do arquivo
            List<String> lines = br.lines().collect(Collectors.toList());

            // Remove as linhas inuteis
            lines.remove(0);
            lines.remove(1);
            lines.remove(1);

            n_de_pedidos = Integer.parseInt(lines.get(0));
            lines.remove(lines.get(0));

            // Cria a matriz
            pedido_requisito = new Integer[n_de_pedidos][5];

            System.out.println("N de pedidos: " + n_de_pedidos);

            int conti = 0;
            int contj = 0;

            //Transforma em matriz
            for (String line : lines) {
                String [] list = line.split(" ");

                for( String l : list){
                    this.pedido_requisito[conti][contj] = Integer.parseInt(l);
                    contj++;
                }
                conti++;
                contj = 0;
            }


            br.close();
        } catch (IOException e) {
            //Caso o nome da instancia esteja troll
            System.out.println("\nEnter a valid filename!\n");
            e.printStackTrace();
        }
    }

    //Get da matriz
    public Integer[][] getMatriz(){
        return this.pedido_requisito;
    }


}
