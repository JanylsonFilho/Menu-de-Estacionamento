public class Ticket {
    private static final double VALOR_PRIMEIRAS_TRES_HORAS = 10.00; // exemplo para x = 10 reais e y = 5 reais
    private static final double VALOR_HORA_ADICIONAL = 5.00;

    public static void gerarTicket(Veiculo veiculo) {
        long minutosEstacionados = veiculo.calcularTempoEstacionado();
        double valorTotal = calcularValor(minutosEstacionados);

        System.out.println("\n--- Ticket de Estacionamento ---");
        System.out.println("Placa do veículo: " + veiculo.getPlaca());
        System.out.println("Tempo total: " + minutosEstacionados + " minutos");
        System.out.println("Valor a pagar: R$ " + String.format("%.2f", valorTotal));
        System.out.println("-------------------------------\n");
    }

    private static double calcularValor(long minutosEstacionados) {
        if (minutosEstacionados <= 15) {
            return 0.00; //  15 minutos gratis
        }

        double horasEstacionadas = Math.ceil((minutosEstacionados - 15) / 60.0);
        if (horasEstacionadas <= 3) {
            return VALOR_PRIMEIRAS_TRES_HORAS;
        } else {
            return VALOR_PRIMEIRAS_TRES_HORAS + (horasEstacionadas - 3) * VALOR_HORA_ADICIONAL;
        }
    }
}
