public class Main {

    public static void main(String[] args) {

        SymbolsCode symbolsCode = SymbolsCode.get();

        Subscriber subA = new Subscriber("Alice");
        Subscriber subB = new Subscriber("Bob");

        subA.send("Hello, World!", symbolsCode.mapOfSymbolsCode);
        System.out.println("P = " + subA.getP());
        System.out.println("Q = " + subA.getQ());
        System.out.println("F = " + subA.getF());
        System.out.println("N = " + subA.getN());
        System.out.println("D = " + subA.getD());
        System.out.println("E = " + subA.getE());
        subB.recieve(subA.getListOfEncodedBlocks(), symbolsCode.mapOfSymbolsCode);

    }
}
