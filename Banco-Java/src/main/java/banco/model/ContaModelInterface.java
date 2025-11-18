package main.java.banco.model;

public interface ContaInterface {
    boolean deposita(double valor);
    boolean saca(double valor);
    ClienteModel getDono();
    int getNumero();
    double getSaldo();
    void remunera();
}
