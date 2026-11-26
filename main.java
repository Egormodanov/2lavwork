import java.io.PrintStream;
import java.util.Scanner;

class Pair implements Comparable<Pair>{
    public int menT, bolT;
    
    public Pair(int menT, int bolT){
        this.menT = menT;
        this.bolT = bolT;
    }
    
    @Override
    public int compareTo(Pair a){
        int answ = 0;
        if (this.menT > a.menT) answ = 1;
        else{
            if (this.menT < a.menT) answ = -1;
            else{
                if (this.bolT > a.bolT) answ = 1;
                else if (this.bolT < a.bolT) answ = -1;
            }
        }
        return answ;
    }
}


public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    
    public static void swap(double[][] a, int ind){
        for (int i = 0; i < a.length; i++){
            double t = a[i][ind];
            a[i][ind] = a[i][ind + 1];
            a[i][ind + 1] = t;
        }
    }
    
    public static int count(double[][] a, double value){
        int answ = 0;
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < a[0].length; j++){
                if (a[i][j] == value) answ++;
            }
        }
        return answ;
    }
    
    public static void circle(double[][] a){
        int x = a[0].length / 2, y = a.length / 2, dlinx = 1, dliny = 1, countEl = a.length * a[0].length;
        
        while (countEl > 0){
            int t = dliny;
            while (t > 0){
                if (x >= 0 && x < a[0].length && y >= 0 && y < a.length){
                    out.printf("%.3f ", a[y][x]);
                    countEl--;
                }
                y++;
                t--;
            }
            dliny++;
            
            t = dlinx;
            while (t > 0){
                if (x >= 0 && x < a[0].length && y >= 0 && y < a.length){
                    out.printf("%.3f ", a[y][x]);
                    countEl--;
                }
                x++;
                t--;
            }
            dlinx++;
            
            t = dliny;
            while (t > 0){
                if (x >= 0 && x < a[0].length && y >= 0 && y < a.length){
                    out.printf("%.3f ", a[y][x]);
                    countEl--;
                }
                y--;
                t--;
            }
            dliny++;
            
            t = dlinx;
            while (t > 0){
                if (x >= 0 && x < a[0].length && y >= 0 && y < a.length){
                    out.printf("%.3f ", a[y][x]);
                    countEl--;
                }
                x--;
                t--;
            }
            dlinx++;
        }
    }
        
        
    public static void main(String[] args) {
        
        int n = in.nextInt(), m = in.nextInt();
        double[][] a = new double[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                a[i][j] = in.nextDouble();
            }
        }
        
        double T = in.nextDouble();
        
        Pair[] vspom = new Pair[m];
        for (int i = 0; i < m; i++) vspom[i] = new Pair(0,0);
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (a[j][i] < T) vspom[i].menT++;
                else if (a[j][i] > T) vspom[i].bolT++;
            }
        }
        
        for(int i = 1; i < m; i++){
            for (int j = 0; j < m - i; j++){
                if (vspom[j].compareTo(vspom[j + 1]) > 0){
                    swap(a, j);
                    Pair t = vspom[j];
                    vspom[j] = vspom[j + 1];
                    vspom[j + 1] = t;
                }
            }
        }
        
        int maxcount = 0;
        double maxCountT = a[0][0];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                int counta = count(a, a[i][j]);
                if (maxcount < counta || (maxcount == counta && a[i][j] < maxCountT)){
                    maxcount = counta;
                    maxCountT = a[i][j];
                }
            }
        }
        out.printf("Самая часто встречающаяся температура - %.3f\n\n", maxCountT);
        
        out.printf("отсортированная матрица \n\n");
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                out.printf("%.3f ", a[i][j]);
            }
            out.printf("\n");
        }
        
        out.printf("\nЗапись вихрем\n\n");
        
        circle(a);
        
        out.printf("\n\n Обновленный массив\n\n");
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (a[i][j] < T) a[i][j] = T;
                out.printf("%.3f ", a[i][j]);
            }
            out.printf("\n");
        }
    }
}
