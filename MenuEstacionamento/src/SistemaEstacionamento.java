import java.time.LocalTime;
import java.util.Scanner;

public class SistemaEstacionamento {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento(3, 3);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Entrada de Veículo");
            System.out.println("2. Saída de Veículo e Cálculo do Ticket");
            System.out.println("3. Calcular Tempo de Permanência do Veículo");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite a placa do veículo: ");
                    String placaEntrada = scanner.nextLine();
                    System.out.print("Digite a hora de entrada (HH:MM): ");
                    String horaEntradaStr = scanner.nextLine();
                    LocalTime horaEntrada = LocalTime.parse(horaEntradaStr);
                    estacionamento.acomodarVeiculo(placaEntrada, horaEntrada);
                    break;

                case 2:
                    System.out.print("Digite a placa do veículo para saída: ");
                    String placaSaida = scanner.nextLine();
                    System.out.print("Digite a hora de saída (HH:MM): ");
                    String horaSaidaStr = scanner.nextLine();
                    LocalTime horaSaida = LocalTime.parse(horaSaidaStr);
                    estacionamento.liberarVeiculo(placaSaida, horaSaida);
                    break;

                case 3:
                    System.out.print("Digite a placa do veículo: ");
                    String placaTempo = scanner.nextLine();
                    System.out.print("Digite a hora de saída (HH:MM): ");
                    String horaSaidaTempoStr = scanner.nextLine();
                    LocalTime horaSaidaTempo = LocalTime.parse(horaSaidaTempoStr);
                    long tempoPermanencia = estacionamento.calcularTempoPermanencia(placaTempo, horaSaidaTempo);
                    if (tempoPermanencia >= 0) {
                        System.out.println("Tempo de permanência: " + tempoPermanencia + " minutos.");
                    }
                    break;

                case 4:
                    System.out.println("Encerrando o sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
