import java.util.*;

public class KeyGenerator {

    int P, Q, f, d;
    long e, N;
    private Random random = new Random();



    private static KeyGenerator sKeyGenerator;

    public static KeyGenerator get(){
        if (sKeyGenerator == null){
            sKeyGenerator = new KeyGenerator();
        }
        return sKeyGenerator;
    }

    private KeyGenerator(){
        setP();
        setQ();
        setN(getP(), getQ());
        setF(getP(), getQ());
        setD(getF());
    }

    public int getP() {
        return P;
    }

    public void setP() {
        P = random.nextInt(255); // 1 байтное число
        for (int i = 2; i <= P / 2; i++){
            int tmp = P % i;
            if (tmp == 0){
                setP();
            }
        }
    }

    public int getQ() {
        return Q;
    }

    public void setQ() {
        Q = random.nextInt(255); // 1 байтное число

        for (int i = 2; i <= Q / 2; i++){
            int tmp = Q % i;
            if (tmp == 0){
                setQ();
            }
        }
    }

    public long getN() {
        return N;
    }

    public void setN(int p, int q) {
        N = p * q;
    }

    public int getF() {
        return f;
    }

    public void setF(int p, int q) {
        f = (p - 1) * (q - 1);
    }

    public int getD() {
        return d;
    }

    public void setD(int f) {

        d = random.nextInt(255); // 1 байтное число

        if ((d < f) && (f % d != 0)){
            for (int i = 2; i <= d / 2; i++){
                int tmp = d % i;
                if (tmp == 0){
                    setD(f);
                }
            }
        }
    }



}
