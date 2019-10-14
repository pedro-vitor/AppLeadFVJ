package com.NTI.AppFVJ.Data;

public class Filter {
    public static boolean Email(String email) {
        if (email.trim().isEmpty() || email.length() > 80)
            return false;
        return true;
    }

    public static boolean Nome(String nome) {
        if (nome.trim().isEmpty() || nome.length() < 3 || nome.length() > 50)
            return false;
        return true;
    }

    public static boolean Senha(String senha) {
        if (senha.trim().isEmpty() || senha.length() < 4 || senha.length() > 15)
            return false;
        return true;
    }

    public static boolean Cidade(String cidade) {
        if (cidade.trim().isEmpty() || cidade.length() < 2 || cidade.length() > 20)
            return false;
        return true;
    }

    public static boolean Endereco(String endereco) {
        if (endereco.trim().isEmpty() || endereco.length() < 5 || endereco.length() > 80)
            return false;
        return  true;
    }
}
