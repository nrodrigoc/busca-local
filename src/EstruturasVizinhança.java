import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class EstruturasVizinhança {

    private Construtivo construtivo;

    public EstruturasVizinhança(Construtivo construtivo) {
        this.construtivo = construtivo;
    }

    public void busca(){


        System.out.println("\n\n--------------------------------------");
        System.out.println("INICIANDO ESTRUTURA DE VIZINHANÇA");
        System.out.println("--------------------------------------");

        int menor_multa = construtivo.getTotalMulta();

//        Integer[][] matriz = construtivo.getMatriz();

        ArrayList<Integer> visitas = (ArrayList<Integer>) construtivo.getVisitas();

        ArrayList<Integer> melhor = (ArrayList<Integer>) visitas.clone();

        // True sempre que um novo caminho menor for encontrado
        boolean novo = true;


        while(novo) {
            novo = false;
            // For para fazer as trocas entre os índices
            for (int i = 1; i < visitas.size()-1; i++) {
                //Array para auxiliar a comparação
                ArrayList<Integer> aux = (ArrayList<Integer>) visitas.clone();

                Collections.swap(aux, 0, i);

                int multa = construtivo.calculaMulta(aux);
                if(menor_multa > multa){
                    menor_multa = multa;
                    melhor = aux;
                    System.out.println();
                    System.out.println("MENOR MULTA ATUAL: " + menor_multa);
                    System.out.print("MELHOR SEQUENCIA ATUAL: |");
                    for ( Integer visita : melhor ){
                        System.out.print(" " + visita + " |");
                    }
                    novo = true;
                }
            }

            visitas = melhor;

        }

        System.out.println("\n--------------------------------------");
        System.out.println("FIM ESTRUTURA DE VIZINHANÇA");
        System.out.println("--------------------------------------");


        System.out.println("\nMelhor multa: "+menor_multa);
        System.out.print("MELHOR SEQUÊNCIA: |");
        for ( Integer visita : visitas ){
            System.out.print(" " + visita + " |");
        }
    }


}
