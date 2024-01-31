package Thread2_RSA;

import java.math.BigInteger;
import java.util.Random;


public class ThreadGenerarClavesRSA extends Thread {
	
    BigInteger e,d,n,p,q,totient;	
    int tamanioClave;
    long tiempoEjecucion;
  

    public ThreadGenerarClavesRSA(String nombre, int tamanioClave) {
        super();
        this.setName(nombre);
        this.tamanioClave = tamanioClave;
        this.tiempoEjecucion = 0;
    }
    
    @Override
    public void run(){ 
                this.tiempoEjecucion = generaClavesRSA();
                System.out.println("Caso 1 "+this.getName() + " -> " + this.tiempoEjecucion);                
    }

    public long getTiempoEjecucion() {
        return tiempoEjecucion;
    }
        
    public long generaClavesRSA() {
        long ini = System.currentTimeMillis();
        generaPrimos(); //Genera p y q
        generaClaves(); //Genera e y d
        long fin = System.currentTimeMillis();
        //System.out.println("Clave Creada y Finalizada");
        return fin-ini;
    }

    public void generaPrimos()	{
        p = new BigInteger(tamanioClave/2, 10, new Random());
        do
            q = new BigInteger(tamanioClave/2, 10, new Random());
        while(q.compareTo(p)==0);   			
    }

    public void generaClaves(){
        // n = p * q
        n = p.multiply(q);
        // toltient = (p-1)*(q-1)
        totient = p.subtract(BigInteger.valueOf(1));
        totient = totient.multiply(q.subtract(BigInteger.valueOf(1)));
        // Elegimos un e coprimo de y menor que n
        do
            e = new BigInteger(tamanioClave, new Random());
        while((e.compareTo(totient) != -1) ||(e.gcd(totient).compareTo(BigInteger.valueOf(1)) != 0));
            // d = e^1 mod totient
            d = e.modInverse(totient);
    }

   
    
    
}