import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        System.out.println("Enter file name that is in the \"instance\" folder. Ex.in: \"instance1.data\": ");
        Scanner ler = new Scanner(System.in);
        String filename = ler.next();
        ler.close();

        Instancia instancia = new Instancia(filename);

        Construtivo construtivo = new Construtivo(instancia.getMatriz());
        construtivo.initialiaze();


        EstruturasVizinhanca estruturasVizinhanca = new EstruturasVizinhanca(construtivo);
        estruturasVizinhanca.busca();
    }

}
