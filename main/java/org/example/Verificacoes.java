package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Verificacoes {

    public static void validar(String entrada, String tipo) {
        switch (tipo.toLowerCase()) {
            case "nome":
                if (!entrada.matches("[A-Za-zÀ-ÿ ]+")) {
                    throw new IllegalArgumentException("Nome deve conter apenas letras e espaços!");
                }
                break;
            case "email":
                if (!entrada.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    throw new IllegalArgumentException("E-mail invalido!");
                }
                break;
            case "telefone":
                if (!entrada.matches("\\d{8,15}")) {
                    throw new IllegalArgumentException("Telefone deve conter apenas numeros!");
                }
                break;
            case "cpf":
                if (!entrada.matches("\\d{11}")) {
                    throw new IllegalArgumentException("CPF deve conter apenas numeros!");
                }
                break;
        }
    }

    public static Date validarData(String entrada) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);
        try {
            return formato.parse(entrada);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Data inválida. Use o formato dd/MM/yyyy.");
        }
    }
}
