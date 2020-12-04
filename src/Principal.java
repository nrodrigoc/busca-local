import java.util.List;

public class Principal {

    public static void main(String[] args) {
        System.out.println("Bom dia");

        String filename = "instance2.data";

        Instancia instancia = new Instancia(filename);

        Integer [][] matriz = instancia.getMatriz();

        Construtivo construtivo = new Construtivo(matriz);

        //construtivo.initialiaze();

        List<Integer> result = construtivo.getResult();

        //int multa = construtivo.calculaMulta(result);

        //System.out.println("\nVerificação da multa: "+multa);

        EstruturasVizinhança estruturasVizinhança = new EstruturasVizinhança(construtivo);
        estruturasVizinhança.busca();

    }

}
