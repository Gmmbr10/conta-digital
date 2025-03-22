import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Banco {

    private String nome;

    private static List<Conta> contas = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static void addConta(Conta conta){
        Banco.contas.add(conta);
    }

    public static Set<Cliente> getClientes() {
        if ( Banco.contas.isEmpty() ) return null;

        Set<Cliente> clientes = new TreeSet<>((Cliente c1, Cliente c2) -> c1.getNome().compareToIgnoreCase(c2.getNome()));
        for ( Conta conta : Banco.contas ) {
            clientes.add(conta.cliente);
        }
        return clientes;
    }

    public static List<Conta> getContas() {
        if ( Banco.contas.isEmpty() ) return null;

        List<Conta> contasPorNumero = Banco.contas;
        contasPorNumero.sort((Conta c1, Conta c2) -> Integer.compare(c1.getNumero(),c2.getNumero()) );
        return contasPorNumero;
    }

    public static List<Conta> getContasPorAgencias() {
        if ( Banco.contas.isEmpty() ) return null;

        List<Conta> contasPorAgencia = Banco.contas;
        contasPorAgencia.sort((Conta c1, Conta c2) -> Integer.compare(c1.getAgencia(),c2.getAgencia()) );
        return contasPorAgencia;
    }

    public static Conta pegarContaMaiorSaldo() {
        if ( Banco.contas.isEmpty() ) return null;

        Conta contaMaiorSaldo = null;
        double maiorSaldo = Double.MIN_VALUE;
        for ( Conta conta : Banco.contas ) {
            if ( conta.getSaldo() > maiorSaldo ) {
                maiorSaldo = conta.getSaldo();
                contaMaiorSaldo = conta;
            }
        }

        return contaMaiorSaldo;
    }

    public static Conta pegarContaMenorSaldo() {
        if ( Banco.contas.isEmpty() ) return null;

        Conta contaMenorSaldo = null;
        double menorSaldo = Double.MAX_VALUE;
        for ( Conta conta : Banco.contas ) {
            if ( conta.getSaldo() < menorSaldo ) {
                menorSaldo = conta.getSaldo();
                contaMenorSaldo = conta;
            }
        }

        return contaMenorSaldo;
    }

    public static Map<Cliente,List<Conta>> buscarClientesPorPeriodoDeSaldo(double maxSaldo,double minSaldo) {
        if ( Banco.contas.isEmpty() ) return null;

        Set<Conta> contasPorPeriodoDeSaldo = new HashSet<>();
        for ( Conta conta : Banco.contas ) {
            if ( minSaldo <= conta.getSaldo() && conta.getSaldo() <= maxSaldo ) contasPorPeriodoDeSaldo.add(conta);
        }

        Map<Cliente,List<Conta>> clientesPorPeriodoDeSaldo = new LinkedHashMap<>();
        if ( !contasPorPeriodoDeSaldo.isEmpty() ) {
            for ( Conta conta : contasPorPeriodoDeSaldo ) {
                if ( clientesPorPeriodoDeSaldo.containsKey(conta.getCliente()) ) {
                    List<Conta> contas = clientesPorPeriodoDeSaldo.get(conta.getCliente());
                    contas.add(conta);
                    continue;
                }
                List<Conta> contas = new ArrayList<>();
                contas.add(conta);
                clientesPorPeriodoDeSaldo.put(conta.getCliente(), contas);
            }
        }
        System.out.println(clientesPorPeriodoDeSaldo);
        return clientesPorPeriodoDeSaldo;
    }
}
