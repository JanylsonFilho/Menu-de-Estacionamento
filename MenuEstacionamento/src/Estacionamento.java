import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Estacionamento {
    private final int maxLinhas;
    private final int maxColunas;
    private final Veiculo[][] vagas;
    private final Map<String, String> estadosMap;

    private final double taxaInicial = 10.00; // Taxa até 3 horas
    private final double taxaAdicional = 5.00; // Taxa por hora extra

    public Estacionamento(int maxLinhas, int maxColunas) {
        this.maxLinhas = maxLinhas;
        this.maxColunas = maxColunas;
        vagas = new Veiculo[maxLinhas][maxColunas];
        estadosMap = new HashMap<>();
        inicializarEstados();
    }

    private void inicializarEstados() {
        estadosMap.put("AAA", "São Paulo");
        estadosMap.put("BBB", "Rio de Janeiro");
        estadosMap.put("CCC", "Minas Gerais");
        // Adicione outras sequências de letras e estados conforme necessário.
    }

    public boolean acomodarVeiculo(String placa, LocalTime horaEntrada) {
        String estado = obterEstado(placa);
        if (estado == null) {
            System.out.println("Placa inválida ou de outro estado.");
            return false;
        }

        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                if (vagas[i][j] == null) {
                    vagas[i][j] = new Veiculo(placa, horaEntrada, estado);
                    System.out.println("Veículo de " + estado + " acomodado na posição: [" + i + "][" + j + "]");
                    return true;
                }
            }
        }
        System.out.println("Estacionamento cheio!");
        return false;
    }

    public boolean liberarVeiculo(String placa, LocalTime horaSaida) {
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                Veiculo veiculo = vagas[i][j];
                if (veiculo != null && veiculo.getPlaca().equals(placa)) {
                    veiculo.setHoraSaida(horaSaida);
                    double valor = calcularValorTicket(veiculo);
                    vagas[i][j] = null;
                    System.out.println("Veículo removido com sucesso!");
                    System.out.printf("Valor do ticket: R$ %.2f\n", valor);
                    return true;
                }
            }
        }
        System.out.println("Veículo não encontrado.");
        return false;
    }

    private double calcularValorTicket(Veiculo veiculo) {
        long minutosEstacionados = veiculo.calcularTempoEstacionado();

        if (minutosEstacionados <= 15) {
            return 0.0; // Tolerância de 15 minutos
        } else if (minutosEstacionados <= 180) {
            return taxaInicial; // Até 3 horas
        } else {
            long horasExtras = (long) Math.ceil((minutosEstacionados - 180) / 60.0); // Horas excedentes
            return taxaInicial + (horasExtras * taxaAdicional);
        }
    }

    private String obterEstado(String placa) {
        String prefixo = placa.substring(0, 3).toUpperCase();
        return estadosMap.getOrDefault(prefixo, null);
    }

    // Novo método para calcular o tempo de permanência em minutos
    public long calcularTempoPermanencia(String placa, LocalTime horaSaida) {
        for (int i = 0; i < maxLinhas; i++) {
            for (int j = 0; j < maxColunas; j++) {
                Veiculo veiculo = vagas[i][j];
                if (veiculo != null && veiculo.getPlaca().equals(placa)) {
                    veiculo.setHoraSaida(horaSaida);
                    return veiculo.calcularTempoEstacionado();
                }
            }
        }
        throw new IllegalArgumentException("Veículo não encontrado no estacionamento.");
    }
}
