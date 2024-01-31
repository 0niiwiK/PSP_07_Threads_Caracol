/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread2_RSA;

/**
 *
 * @author bosque
 */
public class Principal {
    public static void main( String args[] ) {
        ThreadGenerarClavesRSA t1,t2,t3;

        // Creamos los threads
        // tamaños clave 1024,2048 (0,4sg),4096 (8sg),8192
        int tamanio=8192;
        t1 = new ThreadGenerarClavesRSA("uno",tamanio);
        t1.setPriority(Thread.MIN_PRIORITY); //sirve cuando hay recursos compartidos.
        t2 = new ThreadGenerarClavesRSA("dos",tamanio);
        t3 = new ThreadGenerarClavesRSA("tres",tamanio);
        t3.setPriority(Thread.MAX_PRIORITY);

        // Arrancamos los threads, es decir, llamamos al método run().
        t1.start();
        t1.setPriority(Thread.MIN_PRIORITY); //sirve cuando hay recursos compartidos.
        t2.start();
        t3.start();
        t3.setPriority(Thread.MAX_PRIORITY);
        
        //Caso1: imprime los datos en el hilo, al final del método run().
        
        //Caso2: Estos tiempos darán 0 ya que se ejecutan secuencialmente, sin esperar a que ternimes los hilos
        System.out.println("Caso 2 "+t1.getName()+" "+t1.getTiempoEjecucion());
        System.out.println("Caso 2 "+t2.getName()+" "+t2.getTiempoEjecucion());
        System.out.println("Caso 2 "+t3.getName()+" "+t3.getTiempoEjecucion());
        
        //Caso3: Controla repetidamente (bucle While (true)) cuando terniman. Es muy costoso.
        //A veces no funciona. PE: si sola falta por terminar el hilo 1 y termina cuando este bucle está
        //en el segundo if, llegará al último if y se saldrá, ya que en ese momento todos los hilos han terminado.
        //solución: añadir hay las banderas (o en el While)-> MUY COSTOSO.
        boolean ban1=true, ban2=true, ban3=true; //Banderas para que solo escriba un mensaje por hilo.
        while (true){
            if (!t1.isAlive() && ban1){
                System.out.println("Caso 3 "+t1.getName()+" "+t1.getTiempoEjecucion());
                ban1=false;
            }
            if (!t2.isAlive() && ban2){
                System.out.println("Caso 3 "+t2.getName()+" "+t2.getTiempoEjecucion());
                ban2=false;
            }
            if (!t2.isAlive() && ban3){
                System.out.println("Caso 3 "+t3.getName()+" "+t3.getTiempoEjecucion());
                ban3=false;
            }
            if (!t1.isAlive() && !t2.isAlive() && !t3.isAlive()){
                System.out.println("Todos han terminado");
                break;
            }
        }
    }
}
