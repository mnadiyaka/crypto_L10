import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("z1: " + z1(new BigInteger(String.valueOf(7)), new BigInteger(String.valueOf(11))));
        BigInteger[] p2 = z2(new BigInteger(String.valueOf(7)), new BigInteger(String.valueOf(11)));
        System.out.println("z2: " + p2[0] + " " + p2[1]);

        BigInteger[] p3 = z3(new BigInteger(String.valueOf(7)), new BigInteger(String.valueOf(11)), p2);
        System.out.println("z3: " + p3[0] + " " + p3[1]);
        System.out.println("z4: " + z4());
        System.out.println("z5: " + z5(new BigInteger(String.valueOf(7)), new BigInteger(String.valueOf(11))));
    }

    public static boolean z1(BigInteger x, BigInteger y) {
        BigInteger c = y.modPow(new BigInteger(String.valueOf(2)), new BigInteger(String.valueOf(23)));
        return c.compareTo(new BigInteger(String.valueOf(x.pow(3).add(x).add(new BigInteger(String.valueOf(1)))))) == 0;
    }

    public static BigInteger[] z2(BigInteger x, BigInteger y) {
        BigInteger p = new BigInteger(String.valueOf(23));
        BigInteger v = ((((x.pow(2).multiply(new BigInteger(String.valueOf(3))))
                .add(new BigInteger(String.valueOf(1)))))
                .multiply(y.multiply(new BigInteger(String.valueOf(2)))
                        .modPow(new BigInteger(String.valueOf(-1)), p))).mod(p);
        BigInteger[] point = new BigInteger[2];
        point[0] = (v.pow(2).subtract(x.multiply(new BigInteger(String.valueOf(2))))).mod(p);
        point[1] = (v.multiply(x.subtract(point[0])).subtract(y)).mod(p);
        return point;
    }

    public static BigInteger[] z3(BigInteger x, BigInteger y, BigInteger[] q) {
        BigInteger[] point = new BigInteger[2];
        BigInteger p = new BigInteger(String.valueOf(23));
        BigInteger v = ((q[1].subtract(y)).multiply((q[0].subtract(x)).modPow(new BigInteger(String.valueOf(-1)), p))).mod(p);
        point[0] = v.pow(2).subtract(x).subtract(q[0]).mod(p);
        point[1] = v.multiply(x.subtract(point[0])).subtract(y).mod(p);
        return point;
    }

    public static List<String> z4() {
        List<String> res = new ArrayList<>();
        BigInteger p = new BigInteger(String.valueOf(23));
        int j = 0;
        for (int i = 0; i < 23; i++) {
            BigInteger x = new BigInteger(String.valueOf(i));
            x = x.pow(3).add(x).add(new BigInteger(String.valueOf(1)));
            BigInteger y = new BigInteger(String.valueOf(j));
            j = 0;
            while (j < 23) {
                if (y.modPow(new BigInteger(String.valueOf(2)), p).compareTo(x) == 0) {
                    res.add("(" + i + ";" + y + ")");
                }
                y = new BigInteger(String.valueOf(++j));
            }
        }

        return res;
    }

    public static int z5(BigInteger x, BigInteger y) {
        int d = 2;
        BigInteger[] point = z2(x, y);
        //System.out.println("z5: " + point[0] + " " + point[1]);
        BigInteger v;
        BigInteger p = new BigInteger(String.valueOf(23));
        do {
            v = ((point[1].subtract(y)).multiply((point[0].subtract(x))
                    .modPow(new BigInteger(String.valueOf(-1)), p))).mod(p);
            point[0] = v.pow(2).subtract(x).subtract(point[0]).mod(p);
            point[1] = v.multiply(x.subtract(point[0])).subtract(y).mod(p);
            //System.out.println("z5: " + point[0] + " " + point[1]);
            d++;
        } while (point[0].compareTo(x) != 0 && point[1].compareTo(y) != 0);
        return d + 1;
    }
}
