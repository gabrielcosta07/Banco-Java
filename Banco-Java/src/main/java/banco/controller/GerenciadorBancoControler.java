package main.java.banco.controller;

import main.java.banco.model.Cliente;
import main.java.banco.model.Conta;

import java.util.*;

public class GerenciadorBanco {
    private List<Cliente> clientes;
    private Map<String, Conta> contas;

    public GerenciadorBanco() {
        clientes = new ArrayList<>();
        contas = new HashMap<>();
    }

    private String normalizar(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }

    public boolean existeCpf(String cpf) {
        String alvo = normalizar(cpf);
        for (Cliente c : clientes) {
            if (normalizar(c.getCpf()).equals(alvo)) return true;
        }
        return false;
    }

    public boolean existeRg(String rg) {
        String alvo = normalizar(rg);
        for (Cliente c : clientes) {
            if (normalizar(c.getRg()).equals(alvo)) return true;
        }
        return false;
    }

    public boolean existeNomeSobrenome(String nome, String sobrenome) {
        String n = normalizar(nome);
        String s = normalizar(sobrenome);
        for (Cliente c : clientes) {
            if (normalizar(c.getNome()).equals(n) && normalizar(c.getSobrenome()).equals(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeCpfEmOutro(String cpf, Cliente ignorar) {
        String alvo = normalizar(cpf);
        for (Cliente c : clientes) {
            if (c != ignorar && normalizar(c.getCpf()).equals(alvo)) return true;
        }
        return false;
    }

    public boolean existeRgEmOutro(String rg, Cliente ignorar) {
        String alvo = normalizar(rg);
        for (Cliente c : clientes) {
            if (c != ignorar && normalizar(c.getRg()).equals(alvo)) return true;
        }
        return false;
    }

    public boolean existeNomeSobrenomeEmOutro(String nome, String sobrenome, Cliente ignorar) {
        String n = normalizar(nome);
        String s = normalizar(sobrenome);
        for (Cliente c : clientes) {
            if (c != ignorar && normalizar(c.getNome()).equals(n) && normalizar(c.getSobrenome()).equals(s)) {
                return true;
            }
        }
        return false;
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void removerCliente(Cliente cliente) {
        clientes.remove(cliente);
        contas.remove(cliente.getCpf());
    }

    public void atualizarCliente(Cliente clienteAntigo, Cliente clienteNovo) {
        int indice = clientes.indexOf(clienteAntigo);
        if (indice >= 0) {
            clientes.set(indice, clienteNovo);
            if (contas.containsKey(clienteAntigo.getCpf())) {
                Conta conta = contas.remove(clienteAntigo.getCpf());
                contas.put(clienteNovo.getCpf(), conta);
            }
        }
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente c : clientes) {
            if (c.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Cliente> buscarPorSobrenome(String sobrenome) {
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente c : clientes) {
            if (c.getSobrenome().toLowerCase().contains(sobrenome.toLowerCase())) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public Cliente buscarPorRg(String rg) {
        for (Cliente c : clientes) {
            if (c.getRg().equals(rg)) {
                return c;
            }
        }
        return null;
    }

    public Cliente buscarPorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    public List<Cliente> ordenarPorNome() {
        List<Cliente> ordenados = new ArrayList<>(clientes);
        Collections.sort(ordenados);
        return ordenados;
    }

    public List<Cliente> ordenarPorSobrenome() {
        List<Cliente> ordenados = new ArrayList<>(clientes);
        ordenados.sort(Comparator.comparing(Cliente::getSobrenome, String.CASE_INSENSITIVE_ORDER));
        return ordenados;
    }

    public void adicionarConta(String cpf, Conta conta) {
        contas.put(cpf, conta);
    }

    public Conta buscarConta(String cpf) {
        return contas.get(cpf);
    }

    public boolean clienteTemConta(String cpf) {
        return contas.containsKey(cpf);
    }
}
