import java.util.Objects;

public abstract class Conta implements IConta {
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        agencia = Conta.AGENCIA_PADRAO;
        numero = Conta.SEQUENCIAL++;
        saldo = 0.0;
        this.cliente = cliente;

        Banco.addConta(this);
    }

    @Override
    public void sacar(double valor) {
        if ( valor > saldo ) return;
        saldo = saldo - valor;
    }

    @Override
    public void depositar(double valor) {
        saldo = saldo + valor;
    }

    @Override
    public void transferir(double valor, Conta conta) {
        if ( valor > saldo ) return;
        sacar(valor);
        conta.depositar(valor);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "cliente=" + cliente +
                ", numero=" + numero +
                ", agencia=" + agencia +
                ", saldo=" + saldo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return agencia == conta.agencia && numero == conta.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(agencia, numero);
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    protected void imprimirInformacoesComuns() {
        System.out.println("Agência: " + this.getAgencia());
        System.out.println("Conta: " + this.getNumero());
        System.out.println("Titular: " + this.cliente.getNome());
        System.out.println("Saldo: " + this.getSaldo());
    }
}
