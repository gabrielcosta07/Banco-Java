package main.java.banco.model;

public class ContaCorrente extends Conta {
    private double limite;

    public ContaCorrente(Cliente dono, double depositoInicial, double limite) {
        super(dono, depositoInicial);
        this.limite = limite;
    }

    public double getLimite() { return limite; }

    @Override
    public boolean saca(double valor) {
        if (valor <= 0) return false;
        if (saldo - valor >= -limite) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public void remunera() {
        saldo = saldo * 1.01;
    }

    @Override
    public String toString() {
        return "Conta Corrente - Número: " + numero +
                " - Saldo: R$ " + String.format("%.2f", saldo) +
                " - Limite: R$ " + String.format("%.2f", limite);
    }
}
