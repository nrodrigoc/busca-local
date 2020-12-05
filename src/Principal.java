import java.util.List;

public class Principal {

    public static void main(String[] args) {
        System.out.println("Bom dia");

        String filename = "instance4.data";

        Instancia instancia = new Instancia(filename);

        Integer [][] matriz = instancia.getMatriz();


        Construtivo construtivo = new Construtivo(matriz);

        construtivo.initialiaze();

        EstruturasVizinhança estruturasVizinhança = new EstruturasVizinhança(construtivo);
        estruturasVizinhança.busca();

    }

}
