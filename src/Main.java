public class Main {

    public static void main(String[] args) {
        SymbolsCode symbolsCode = SymbolsCode.get();
        Subscriber keyGenerator = new Subscriber();
        keyGenerator.setP();
        keyGenerator.setQ();
        keyGenerator.setF(keyGenerator.getP(), keyGenerator.getQ());
        keyGenerator.setN(keyGenerator.getP(), keyGenerator.getQ());
        keyGenerator.setD(keyGenerator.getF());
        keyGenerator.setE(keyGenerator.getD(), keyGenerator.getF());
        keyGenerator.send("Hello, World!", symbolsCode.mapOfSymbolsCode);
        System.out.println("P = " + keyGenerator.getP());
        System.out.println("Q = " + keyGenerator.getQ());
        System.out.println("F = " + keyGenerator.getF());
        System.out.println("N = " + keyGenerator.getN());
        System.out.println("D = " + keyGenerator.getD());
        System.out.println("E = " + keyGenerator.getE());
        System.out.println(keyGenerator.getMessageCodes());
        System.out.println(keyGenerator.getEncodedMessage());
        keyGenerator.recieve(keyGenerator.getListOfEncodedBlocks(), symbolsCode.mapOfSymbolsCode);
        System.out.println(keyGenerator.getDecodedMessage());

    }
}
