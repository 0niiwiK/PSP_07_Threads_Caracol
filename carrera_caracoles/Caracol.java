/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrera_caracoles;

import java.awt.*;

public class Caracol extends Thread {
   private static final int DIAMETER = 30;
   int xinicio = 0;
   int x = 0;
   int y = 0;
   private static boolean finalCarrera = false;
   private static final int longitudCarrera = 200;
   private static Caracol ganador = null;
   private int distancia;
   private int id;
   private java.awt.Color color;
   private String nombre = "";

   public Caracol(int id) {
      this.id = id;
      switch (id) {
         case 1:
            this.color = Color.red;
            break;
         case 2:
            this.color = Color.green;
            break;
         case 3:
            this.color = Color.orange;
            break;
         case 4:
            this.color = Color.magenta;
            break;
      }
      nombre = "CARACOL NÚMERO " + id;
      distancia = 0;
      xinicio = 100 - DIAMETER / 2;
      x = xinicio;
      y = 20 + (id - 1) * 35;
   }

   public Color getColor() {
      return color;
   }

   public int getCaracId() {
      return id;
   }

   public static void iniciarCarrrera() { finalCarrera = false; }
   public static boolean haTerminado() { return finalCarrera; }
   public static Caracol getGanagor() { return ganador; }

   public void paint(Graphics2D g) {
      g.setColor(color);
      g.fillOval(x, y, DIAMETER, DIAMETER);
      g.drawString("" + id, 58, y + 26);
      g.drawString("" + distancia*3, 732 , y + 26);
      if (ganador == this) g.drawString("GANADOR", 300, 200);
   }
   public int getDistancia() { return distancia; }

   @Override
   public void run() {
      while (!finalCarrera) {
         try {
            // Dormimos el hilo. Si tenemos una m�quina muy r�pida poner poco tiempo durmiento
            Thread.sleep((int)(Math.random() * 50));
            if (++distancia >= longitudCarrera) finalCarrera = true;
         } catch (InterruptedException e) {
            System.err.println(e);
         }
      }
      if (distancia == longitudCarrera){
          ganador = this;
//          Para probar como los otros hilos siguen corriendo descomenta la línea de debajo y poner el sleep muy bajo, <5
//          System.out.println(" "+nombre);
      }
   }

   public void move() {
      x = xinicio + distancia*3;
   }
}
