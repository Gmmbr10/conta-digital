public class Main {
    public static void main(String[] args) {
        ContaCorrente ccG = new ContaCorrente(new Cliente("Giovanne"));
        ccG.depositar(1000.00);

        ContaPoupanca cpG = new ContaPoupanca(new Cliente("Giovanne"));
        ccG.transferir(500.00, cpG);

        ContaPoupanca cpB = new ContaPoupanca(new Cliente("Billy"));
        ccG.transferir(500.00,cpB);

        ccG.imprimirExtrato();
        System.out.println("");
        cpB.imprimirExtrato();
        System.out.println("");
        System.out.println(Banco.getClientes());
        System.out.println("");
        System.out.println(Banco.getContas());
        System.out.println("");
        System.out.println(Banco.getContasPorAgencias());
        System.out.println("");
        System.out.println(Banco.buscarClientesPorPeriodoDeSaldo(1000.0,0));
    }
}
