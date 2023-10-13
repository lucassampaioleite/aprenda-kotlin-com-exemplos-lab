// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val nome: String)

data class ConteudoEducacional(val nome: String, val duracao: Int, val nivel: Nivel)

data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()

    //O atributo nivel usa a inicialização tardia (lazy) para calcular o nível da formação baseado nos níveis dos conteúdos
    val nivel: Nivel by lazy {
        calcularNivelFormacao()
    }

    //Função que efetua matrícula de um ou mais usuários na formação.
    fun matricular(vararg usuarios: Usuario) {
        inscritos.addAll(usuarios)
    }

    /* Função que calcula o nível da formação com base nos níveis dos conteúdos.
    Inicialmente é mapeado os níveis dos conteúdos da formação.
    Em seguida é encontrado o nível mais frequente entre os conteúdos.
    É retornado o nível mais frequente ou o nível básico caso não haja conteúdos adicionados a formação. */
    private fun calcularNivelFormacao(): Nivel {
        val niveisConteudos = conteudos.map { it.nivel }
        val nivelMaisFrequente = niveisConteudos.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key

        return nivelMaisFrequente ?: Nivel.BASICO
    }

    //Sobrescrita do toString para considerar o cálculo do nivel
    override fun toString(): String {
        return "Formacao(nome='$nome', nivel=$nivel, conteudos=$conteudos)"
    }
}

fun main() {
    // criação de usuários
    val usuario1 = Usuario("Lucas")
    val usuario2 = Usuario("Maria")
    val usuario3 = Usuario("João")

    // imprimindo um usuário criado para verificação
    println(usuario1)

    // criação de conteúdos
    val conteudo1 = ConteudoEducacional("Introdução ao Kotlin", 1, Nivel.BASICO)
    val conteudo2 = ConteudoEducacional("Operadores e estruturas condicionais", 2, Nivel.BASICO)
    val conteudo3 = ConteudoEducacional("Estruturas de repetição", 2, Nivel.INTERMEDIARIO)
    val conteudo4 = ConteudoEducacional("Funções", 2, Nivel.INTERMEDIARIO)
    val conteudo5 = ConteudoEducacional("Recursividade", 2, Nivel.INTERMEDIARIO)
    val conteudo6 = ConteudoEducacional("Programação funcional com kotlin", 3, Nivel.AVANCADO)

    // imprimindo um conteúdo criado para verificação
    println(conteudo6)

    // criação de uma formação e matrícula de usuários
    val formacao1 = Formacao("Programação em Kotlin",
        listOf(conteudo1, conteudo2, conteudo3, conteudo4, conteudo5, conteudo6))
    formacao1.matricular(usuario1, usuario2)

    // imprimindo o toString completo para verificação do nome, nível calculado e lista de conteúdos da formação
    println(formacao1)

    // imprimindo apenas os conteúdos educacionais da formação
    println("Conteúdos Educacionais da formação ${formacao1.nome}:")
    formacao1.inscritos.forEach { println(it.nome) }

    // imprimindo os alunos matriculados
    println("Alunos matriculados na formação ${formacao1.nome}:")
    formacao1.inscritos.forEach { println(it.nome) }

    //teste de caso especial de formação sem conteúdo (nível básico)
    val formacao2 = Formacao("Programação em Kotlin", listOf())
    println(formacao2)
}