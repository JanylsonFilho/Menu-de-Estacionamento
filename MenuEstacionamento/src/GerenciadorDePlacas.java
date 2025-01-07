import java.util.HashMap;
import java.util.Map;

public class GerenciadorDePlacas {
    private Map<String, String> estados;

    public GerenciadorDePlacas() {
        estados = new HashMap<>();
        estados.put("AAA", "São Paulo");
        estados.put("BBB", "Rio de Janeiro");


    }

    public String identificarEstado(String placa) {
        if (placa.length() >= 3) {
            String prefixo = placa.substring(0, 3);
            return estados.getOrDefault(prefixo, "Estado desconhecido");
        }
        return "Placa inválida";
    }
}
