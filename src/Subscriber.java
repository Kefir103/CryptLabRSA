import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Subscriber {
    String name;
    int P, Q, f, d;
    long e, N;

    StringBuilder encodedMessage = new StringBuilder();
    StringBuilder messageCodes = new StringBuilder();
    StringBuilder decodedMessage = new StringBuilder();

    List<Integer> listOfEncodedBlocks = new ArrayList<>();
    List<Integer> listOfDecodedBlocks = new ArrayList<>();

    Subscriber(String name){
        KeyGenerator keyGenerator = KeyGenerator.get();
        this.P = keyGenerator.getP();
        this.Q = keyGenerator.getQ();
        this.f = keyGenerator.getF();
        this.N = keyGenerator.getN();
        this.d = keyGenerator.getD();
        this.e = setE(this.d, this.f);
        this.name = name;
    }

    public long getE() {
        return e;
    }

    private long setE(int d, int f) {
        e = 0;
        while (e * d % f != 1){
            e++;
        }
        return e;
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

        System.out.println(getName() + "'s message is: " + msg);
        System.out.println(getName() + "'s encodedMessage is: " + getEncodedMessage());

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

        System.out.println(getName() + " recieved : " + getDecodedMessage());
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

    public String getName() {
        return name;
    }

    public int getP() {
        return P;
    }

    public int getQ() {
        return Q;
    }

    public int getF() {
        return f;
    }

    public int getD() {
        return d;
    }

    public long getN() {
        return N;
    }
}
