import java.util.*;

public class Subscriber {

    int P, Q, f, d;
    long e, N;
    private Random random = new Random();

    StringBuilder encodedMessage = new StringBuilder();
    StringBuilder messageCodes = new StringBuilder();
    StringBuilder decodedMessage = new StringBuilder();

    List<Integer> listOfEncodedBlocks = new ArrayList<>();
    List<Integer> listOfDecodedBlocks = new ArrayList<>();

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

    public long getE() {
        return e;
    }

    public void setE(int d, int f) {
        e = 0;
        while (e * d % f != 1){
            e++;
        }
    }

    void send(String msg, Map<Character, Integer> mapOfCharCodes){
        for (int i = 0; i < msg.length(); i++){
            int codeChar = mapOfCharCodes.get(msg.charAt(i));
            int rest = 1;
            for (int j = 0; j < d; j++){
                rest *= codeChar;
                rest %= N;
            }
            listOfEncodedBlocks.add(rest);
            encodedMessage.append(listOfEncodedBlocks.get(i));
        }
    }

    void recieve(List<Integer> listOfBlocks, Map mapOfCharCodes){
        ArrayList<Character> alOfChars = new ArrayList<>(mapOfCharCodes.keySet());
        ArrayList<Integer> alOfCodes = new ArrayList<>(mapOfCharCodes.values());

        for (int block: listOfBlocks){
            int rest = 1;
            for (int j = 0; j < e; j++){
                rest *= block;
                rest %= N;
            }
            listOfDecodedBlocks.add(rest);
        }

        for (int block: listOfDecodedBlocks){
            for (int i = 0; i < alOfCodes.size(); i++){
                if (alOfCodes.get(i) == block){
                    decodedMessage.append(alOfChars.get(i));
                }
            }
        }
    }

    public StringBuilder getEncodedMessage() {
        return encodedMessage;
    }

    public StringBuilder getDecodedMessage() {
        return decodedMessage;
    }

    public StringBuilder getMessageCodes(){
        return messageCodes;
    }

    public List<Integer> getListOfEncodedBlocks() {
        return listOfEncodedBlocks;
    }

}
