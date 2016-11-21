import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Максим on 21.11.2016.
 */
public class GradientDescent {
    private static final double EPS = 1e-10, ALPHA = 1e-3;
    private ArrayList<Pair<Double, Double>> experience;

    public GradientDescent() {
        experience = new ArrayList<>();
    }

    public void feed(double x, double y) {
        experience.add(new Pair<>(x, y));
    }

    public Pair<Double, Double> countTheta() {
        double a = 1, b = 1, ta, tb;
        double m = experience.size();
        do {
            ta = tb = 0;
            for (Pair<Double, Double> ex : experience) {
                double comm = a * ex.getKey() + b - ex.getValue();
                ta -= comm * ex.getKey();
                tb -= comm;
            }
            ta *= ALPHA / m;
            ta += a;
            tb *= ALPHA / m;
            tb += b;
//            System.out.println("ta = " + ta);
//            System.out.println("tb = " + tb);
            if (Math.abs(a - ta) < EPS && Math.abs(b - tb) < EPS)
                break;
            a = ta;
            b = tb;
        } while (true);
        return new Pair<>(a, b);
    }

    public double hypotesis(double x) {
        Pair<Double, Double> k = countTheta();
        double free = k.getKey();
        double first = k.getValue();
        return free + first * x;
    }

    public static void main(String[] args) {
        GradientDescent alg = new GradientDescent();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        double t1[] = new double[n];
        double[] t2 = new double[n];
        for (int i = 0; i < n; i++) {
            t1[i] = in.nextDouble();
            t2[i] = in.nextDouble();
        }
        for (int i = 0; i < n; i++) {
            alg.feed(t1[i % n], t2[i % n]);
        }
        Pair<Double, Double> res = alg.countTheta();
        System.out.println("y = " + res.getKey() + " * x + " + res.getValue());
    }

}
