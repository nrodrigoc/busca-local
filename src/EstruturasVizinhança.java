import java.util.ArrayList;

public class EstruturasVizinhança {

    private Construtivo construtivo;

    public EstruturasVizinhança(Construtivo construtivo) {
        this.construtivo = construtivo;
    }

    public void busca(){

        int multa = construtivo.initialiaze();
        Integer [][] matriz = construtivo.getMatriz();
        ArrayList<Integer> visitas = (ArrayList<Integer>) construtivo.getVisitas();

        ArrayList<Integer> melhor = (ArrayList<Integer>) visitas.clone();
        int melhorMulta = multa;

        for(int i=0; i< visitas.size()-1; i++){

            if(i==0  && matriz[i-1][Instancia.DATA_MINIMA]!=0){  // Verifica opcoes da data min
                continue;
            }



            int temp = visitas.get(i+1);
            visitas.set(i+1,visitas.get(i));
            visitas.set(i, temp);
            int tempMulta = construtivo.calculaMulta(visitas);

            if(tempMulta < melhorMulta){
                melhor = visitas;
                melhorMulta = tempMulta;
            }
        }

        System.out.println("\nMelhor multa: "+melhorMulta);
        for ( Integer visita : visitas ){
            System.out.print(" " + visita + " |");
        }
    }

    public void swap(){}

    public void opt2(){}

    public void reInsert(){}


}
