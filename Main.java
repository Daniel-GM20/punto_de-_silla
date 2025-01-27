import java.util.Random;
import java.util.Scanner;

public class Main {
  // variables para las dimensiones de la matriz
  public static int numF = 0;
  public static int numC = 0;
  public static int[][] matriz;
  public static Random rm = new Random();
  public static Scanner sc = new Scanner(System.in);
  public static void main(String[] args) {
    // banderas para silla encontrada, validacion de dimension de matriz
    boolean flagF = false, flagC = false;
    // variable para opcion de llenado
    int opLlenado = 0;
    do {
      System.out.println("Ingrese las dimensiones de la matriz");
      do {
        System.out.print("Numero de filas:");
        numF = sc.nextInt();
        flagF = validarTam(numF);
      } while (!flagF);
      do {
        System.out.print("Numero de columnas: ");
        numC = sc.nextInt();
        flagC = validarTam(numC);
      } while (!flagC);
      matriz = new int[numF][numC];
      do {
        System.out.println("Seleccione el metodo de llenado:\n1.- Aleatorio\n2.- Manual\n3.- Salir");
        opLlenado = sc.nextInt();
        switch (opLlenado) {
          case 1:
            llenadoAutomatico();
            recorrerMatriz();
            break;
          case 2:
            llenadoManual();
            recorrerMatriz();
            break;
          case 3:
            System.out.println("Bye :)");
            break;
          default:
            System.out.println("Ingrese un valor correcto.");
        }
      } while (opLlenado != 3);
    } while (opLlenado != 3);
  }

  public static boolean validarTam(int tam) {
    if (tam > 1) {
      return true;
    }
    System.out.println("La dimension debe ser mayor a 1");
    return false;
  }

  public static void llenadoAutomatico() {
    for (int f = 0; f < numF; f++) {
      for (int c = 0; c < numC; c++) {
        matriz[f][c] = rm.nextInt(20) + 1;
      }
    }
    imprimirMatriz();
  }

  public static void llenadoManual() {
    for (int f = 0; f < numF; f++) {
      for (int c = 0; c < numC; c++) {
        System.out.print("Ingrese el valor para la posicion [" + f + "][" + c + "]: ");
        matriz[f][c] = sc.nextInt();
      }
    }
    imprimirMatriz();
  }

  public static void recorrerMatriz(){
    boolean puntoSilla = false;
    for(int f=0;f<numF;f++){
      for(int c=0;c<numC;c++){
        boolean flagMenor = buscarMenor(f, c);
        if(flagMenor){
          boolean flagMayor = buscarMayor(f, c);
          if(flagMayor){
            puntoSilla = true;
            System.out.println("Punto de Silla encontrado, en la posición [" + f + "][" + c + "] con el valor " + matriz[f][c]);
          }
        }
        // boolean flagMayor = buscarMayor(f,c);
        // if(flagMayor){
        //   boolean flagMenor = buscarMenor(f,c);
        //   if(flagMenor){
        //     puntoSilla=true;
        //     System.out.println("Punto de Silla encontrado, en la posicion ["+f+"]["+c+"] con el valor "+matriz[f][c]);
        //   }
        // }
      }
    }
    if(!puntoSilla){
      System.out.println("no se encontro punto de silla");
    }
  }

  public static boolean buscarMenor(int row,int col){
    for(int c=0;c<numC;c++){
      if(matriz[row][col]>matriz[row][c]){
        return false;
      }
    }
    return true;
  }

  public static boolean buscarMayor(int row,int col){
    for(int f=0;f<numF;f++){
      if(matriz[row][col]<matriz[f][col]){
        return false;
      }
    }
    return true;
  }

  public static void imprimirMatriz(){
    for (int i = 0; i < numF; i++) {
      for (int j = 0; j < numC; j++) {
        System.out.print(matriz[i][j] + " ");
      }
      System.out.println("");
    }
  }
}