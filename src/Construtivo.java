import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *
 */
public class Construtivo {


    private Integer[][] matriz;

    private List<Integer> visitas;

    // True se um pedido já estiver em produção
    private Boolean emProducao;

    public Construtivo(Integer[][] matriz) {
        this.emProducao = false;
        this.visitas = new ArrayList<>();
        this.matriz = matriz;
    }

    public void initialiaze(){

        Integer [][] copia = matriz;
        int contDias = 0;
        int totalMulta = 0;


        int nDeProcessados = 0;
        //Array Para saber quem ja pode iniciar
        Integer [] arrayIdsStart = new Integer[this.matriz.length];

        System.out.println("--------------------------------------");
        System.out.println("INICIANDO ALGORITIMO GULOSO");
        System.out.println("--------------------------------------");

        Integer id_processando = -1;

        //Pedidos ainda nao processados
        List<Integer> pendentes = new ArrayList<>();
        for (Integer[] i : copia) {
            pendentes.add(i[Instancia.ID_PEDIDO]-1);
        }

        //Pedidos candidatos a próximo em produção
        List<Integer> prontos = new ArrayList<>();


        // Loop que continua enquanto houverem pedidos ainda não precessados
        while (nDeProcessados < matriz.length) {

            System.out.println("DIA: " + contDias + " | PRODUZINDO: " + (id_processando+1));

            Iterator<Integer> iterator = pendentes.iterator();
            while (iterator.hasNext()) {
                Integer id_pendente = iterator.next();
                //Adiciona pedidos que atingiram a data mínima de início à fila de prontos;
                if (copia[id_pendente][Instancia.DATA_MINIMA] - contDias <= 0)
                {
                    iterator.remove();
                    prontos.add(id_pendente);
                }
            }

            double priority = 0;

            if(!emProducao) {
                // Loop que analisa entre os prontos o que tem maior prioridade
                for (Integer id_pronto : prontos) {

                    double k = (copia[id_pronto][Instancia.MULTA_ATRASO]);
                    double d = (copia[id_pronto][Instancia.DATA_ENTREGA] - copia[id_pronto][Instancia.DATA_MINIMA]);
                    double calcPriority = k / d;

                    if (calcPriority > priority) {
                        priority = calcPriority;
                        id_processando = id_pronto;
                        emProducao = true;
                    }
                }
            }

            if(id_processando != -1) {
                copia[id_processando][Instancia.DURACAO]--;
            }

            contDias++;

            // Libera a próxima avaliação de pedidos
            if(copia[id_processando][Instancia.DURACAO] == 0) {
                //Calcula a multa
                if (contDias - copia[id_processando][Instancia.DATA_ENTREGA] > 0){
                    Integer diasAtrasados = contDias - copia[id_processando][Instancia.DATA_ENTREGA];
                    System.out.println("MULTA: " + copia[id_processando][Instancia.MULTA_ATRASO] * diasAtrasados);
                    totalMulta += copia[id_processando][Instancia.MULTA_ATRASO] * diasAtrasados;
                }

                emProducao = false;

                // Adiciona na lista de visitação
                visitas.add(id_processando+1);

                prontos.remove(id_processando);
                nDeProcessados++;
                id_processando = -1;
            }


        }

//        while (this.confereDuracao(copia)){
//
//            int id = 0;
//            double priority = 0;
//
//            for(Integer[] i : copia){
//
////                System.out.println("id atual : " + i[Instancia.ID_PEDIDO]);
//
//                if(i[Instancia.DATA_MINIMA] - contDias <= 0){
//                    double k = (i[Instancia.MULTA_ATRASO]);
//                    double d = (i[Instancia.DATA_ENTREGA] - i[Instancia.DATA_MINIMA]);
//                    double calcPriority = k / d ;
//                    System.out.println("Prioridade: "+calcPriority);
//                    System.out.println("Atual Prioridade: "+priority);
//
//                   if(calcPriority > priority && i[Instancia.DURACAO] > 0){
//                       priority = calcPriority;
//                       if(!emProducao) {
//                           id = i[Instancia.ID_PEDIDO];
//                           emProducao = true;
//                       }
////                       visitas[contadorr] = id;
////                       contadorr++;
//                   }
//                }
//
//                // Adiciona o valor da multa do pedido para cada dia ultrapassado
//                if(contDias > i[Instancia.DATA_ENTREGA] && i[Instancia.DURACAO] > 0){
//                    totalMulta += i[Instancia.MULTA_ATRASO];
//                    System.out.println("Pagando multa de "+ i[Instancia.MULTA_ATRASO] +
//                            " pelo id "+ i[Instancia.ID_PEDIDO]+ " no dia "+contDias );
//                    System.out.println("Total de multa no valor: "+totalMulta);
//                }
//
//            }
//            contDias++;
//
////            System.out.println("Escolhido id: "+ id +" com prioridade de "+ priority);
//            System.out.println("Dia: "+contDias);
//
//            if(copia[id-1][Instancia.DURACAO] > 0){
//                copia[id-1][Instancia.DURACAO] -= 1 ;
//            }
//            if (copia[id-1][Instancia.DURACAO] == 0) {
//                visitas.add(id);
//                contadorr++;
//                emProducao = false;
//            }
//
//        }
        System.out.println("--------------------------------------");
        System.out.println("FIM ALGORITIMO GULOSO");
        System.out.println("--------------------------------------");

        System.out.println("TOTAL DE MULTAS: "+totalMulta);

        System.out.println("VISITAS:");
        for ( Integer visita : visitas ){
            System.out.println(visita);
        }
    }

    private boolean confereDuracao(Integer [][] data){
        for(Integer[] i: data){
            if(i[Instancia.DURACAO] > 0){
                return true;
            }
        }
        return false;
    }


    /**
     *
     * @return a sequência de ids visitada
     */
    public List<Integer> getVisitas() {
        return visitas;
    }

}
