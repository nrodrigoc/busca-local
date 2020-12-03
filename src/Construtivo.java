public class Construtivo {


    private Integer[][] matriz;


    public Construtivo(Integer[][] matriz) {
        this.matriz = matriz;
    }

    public void initialiaze(){

        Integer [][] copia = matriz;
        int contDias = 0;
        int totalMulta = 0;

        //Array Para saber quem ja pode iniciar
        Integer [] arrayIdsStart = new Integer[this.matriz.length];

        System.out.println("--------------------------------------");
        System.out.println("INICIANDO ALGORITIMO GULOSO");
        System.out.println("--------------------------------------");

        while (this.confereDuracao(copia)){


            double priority = 0;
            int id = 0;

            for(Integer[] i : copia){
                System.out.println("id atual : "+i[0]);
                if(i[1] - contDias <= 0){
                    double k = (i[4]);
                    double d = (i[3]);
                    double calcPriority = k /d ;
                    System.out.println("Prioridade: "+calcPriority);
                    System.out.println("Atual Prioridade: "+priority);
                   if(calcPriority>priority && i[2]>0){
                       priority = calcPriority;
                       id = i[0];
                   }
                }
                if(contDias>i[3] && i[2]>0){
                    totalMulta+= i[4];
                    System.out.println("Pagando multa de "+ i[4]+" pelo id "+ i[0]+ " no dia "+contDias );
                    System.out.println("Total de multa no valor: "+totalMulta);
                }

            }
            contDias++;

            System.out.println("Escolhido id: "+ id +" com prioridade de "+ priority);
            System.out.println("Dia: "+contDias);

            if(copia[id-1][2]>0){
                copia[id-1][2] -= 1 ;
            }

        }
        System.out.println("--------------------------------------");
        System.out.println("FIM ALGORITIMO GULOSO");
        System.out.println("--------------------------------------");

        System.out.println("TOTAL DE MULTAS: "+totalMulta);

    }

    private boolean confereDuracao(Integer [][] data){
        for(Integer[] i: data){
            if(i[2]>0){
                return true;
            }
        }
        return false;
    }



}
