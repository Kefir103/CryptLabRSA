import java.util.Map;
import java.util.TreeMap;

class SymbolsCode {

    private static SymbolsCode sSymbolsCode;

    Map<Character, Integer> mapOfSymbolsCode = new TreeMap<>();

    static SymbolsCode get(){
        if (sSymbolsCode == null){
            sSymbolsCode = new SymbolsCode();
        }
        return sSymbolsCode;
    }

    // Инициализация в конструкторе словаря своих кодировок символов
    private SymbolsCode(){
        char c = ' ';
        for (int i = 0; i <= 90; i++){
            mapOfSymbolsCode.put(c, i);
            c++;
        }
        c = 'А';
        for (int i = 91; i < 155; i++){
            mapOfSymbolsCode.put(c, i);
            c++;
        }
    }
}
