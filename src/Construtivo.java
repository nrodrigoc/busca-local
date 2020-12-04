import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 *
 */
public class Construtivo {


    private Integer[][] matriz;

    public Integer[][] getMatriz() {
        return matriz;
    }

    private List<Integer> visitas;

    // True se um pedido já estiver em produção
    private Boolean emProducao;

    public Construtivo(Integer[][] matriz) {
        this.emProducao = false;
        this.visitas = new ArrayList<>();
        this.matriz = matriz;
    }

    public int initialiaze(){

        Integer [][] copia = this.clone();
        int contDias = 0;
        int totalMulta = 0;

        // Número de pedidos já processados
        int nDeProcessados = 0;

        System.out.println("--------------------------------------");
        System.out.println("INICIANDO ALGORITIMO GULOSO");
        System.out.println("--------------------------------------");

        // Id do pedido que está sendo processado no momento
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

            System.out.println("DIA: " + contDias + " | PRODUZINDO: " + (id_processando+1));

            if(id_processando != -1) {
                copia[id_processando][Instancia.DURACAO]--;
            }

            contDias++;

            // Libera a próxima avaliação de pedidos
            if(emProducao && copia[id_processando][Instancia.DURACAO] == 0) {
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
                System.out.println();
            }
        }

        System.out.println("--------------------------------------");
        System.out.println("FIM ALGORITIMO GULOSO");
        System.out.println("--------------------------------------");

        System.out.println("TOTAL DE MULTAS: "+totalMulta);

        System.out.print("VISITAS: |");
        for ( Integer visita : visitas ){
            System.out.print(" " + visita + " |");
        }
        return totalMulta;
    }


    public List<Integer> getResult(){
        return this.visitas;
    }

    public int calculaMulta(List<Integer> ordem){
        int dias = 0;
        int multa = 0;

        for(Integer i: ordem){
            dias += this.matriz[i-1][Instancia.DURACAO];
            if(dias > this.matriz[i-1][Instancia.DATA_ENTREGA]){
                multa += (this.matriz[i-1][Instancia.MULTA_ATRASO] * (dias - this.matriz[i-1][Instancia.DATA_ENTREGA]));
            }
        }
        return multa;
    }



    /**
     *
     * @return a sequência de ids visitada
     */
    public List<Integer> getVisitas() {
        return visitas;
    }

    public Integer[][] clone(){
        Integer [][] copy = Arrays.stream(this.matriz).map(Integer[]::clone).toArray(Integer[][]::new);
        return copy;
    }

}
