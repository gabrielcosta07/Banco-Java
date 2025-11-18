package main.java.banco.model;

public abstract class Conta implements ContaInterface {
    protected int numero;
    protected double saldo;
    protected Cliente dono;
    protected static int proximoNumero = 1000;

    public Conta(Cliente dono, double depositoInicial) {
        this.numero = proximoNumero++;
        this.dono = dono;
        this.saldo = depositoInicial;
    }

    @Override
    public boolean deposita(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        }
        return false;
    }

    @Override
    public boolean saca(double valor) {
        if (valor > 0) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public Cliente getDono() { return dono; }
    @Override
    public int getNumero() { return numero; }
    @Override
    public double getSaldo() { return saldo; }

    @Override
    public abstract void remunera();
}
