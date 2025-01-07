import java.time.Duration;
import java.time.LocalTime;

public class Veiculo {
    private String placa;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;
    private String estado;

    public Veiculo(String placa, LocalTime horaEntrada, String estado) {
        this.placa = placa;
        this.horaEntrada = horaEntrada;
        this.estado = estado;
    }

    public String getPlaca() {
        return placa;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public String getEstado() {
        return estado;
    }

    // Novo método para calcular o tempo de permanência em minutos
    public long calcularTempoEstacionado() {
        if (horaSaida == null) {
            throw new IllegalStateException("Hora de saída não definida.");
        }
        Duration duracao = Duration.between(horaEntrada, horaSaida);
        return duracao.toMinutes();
    }
}
