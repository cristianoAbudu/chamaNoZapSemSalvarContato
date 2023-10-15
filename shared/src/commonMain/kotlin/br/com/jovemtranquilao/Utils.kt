package br.com.jovemtranquilao

class Utils {

    fun mascara(limpo: String, telefone: String, it: String) : String {
        var telefone1 = telefone
        if (limpo.length == 0) {
            telefone1 = it
        } else if (limpo.length == 1) {
            telefone1 = limpo.replace(Regex("^(\\d)$")) {
                "+${it.groupValues[1]}"
            }
        } else if (limpo.length == 2) {
            telefone1 = limpo.replace(Regex("^(\\d{2})$")) {
                "+${it.groupValues[1]}"
            }
        } else if (limpo.length == 3) {
            telefone1 = limpo.replace(Regex("^(\\d{2})(\\d)$")) {
                "+${it.groupValues[1]} (${it.groupValues[2]})"
            }
        } else if (limpo.length == 4) {
            telefone1 = limpo.replace(Regex("^(\\d{2})(\\d{2})$")) {
                "+${it.groupValues[1]} (${it.groupValues[2]})"
            }
        } else if (limpo.length == 5) {
            telefone1 = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d)$")) {
                "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}"
            }
        } else if (limpo.length == 6) {
            telefone1 = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d{2})$")) {
                "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}"
            }
        } else if (limpo.length == 7) {
            telefone1 = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d{3})$")) {
                "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}"
            }
        } else if (limpo.length == 8) {
            telefone1 = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d{4})$")) {
                "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}"
            }
        } else if (limpo.length == 9) {
            telefone1 = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d{4})(\\d)$")) {
                "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}-${it.groupValues[4]}"
            }
        } else if (limpo.length == 10) {
            telefone1 = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d{4})(\\d{2})$")) {
                "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}-${it.groupValues[4]}"
            }
        } else if (limpo.length == 11) {
            telefone1 = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d{4})(\\d{3})$")) {
                "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}-${it.groupValues[4]}"
            }
        } else if (limpo.length == 12) {
            telefone1 = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d{4})(\\d{4})$")) {
                "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}-${it.groupValues[4]}"
            }
        } else if (limpo.length == 13) {
            telefone1 = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d)(\\d{4})(\\d{4})$")) {
                "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]} ${it.groupValues[4]}-${it.groupValues[5]}"
            }
        }
        return telefone1;
    }

    fun limpar(telefone: String): String {
        var retorno =  telefone.replace("+", "");
        retorno =  retorno.replace(" ", "")
        retorno =  retorno.replace("(", "")
        retorno =  retorno.replace(")", "")
        return retorno.replace("-", "");
    }

}