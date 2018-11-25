enum class TypeName {
    Letter,Point,Minus,PositiveInt,NegativeInt,Int,String
}

enum class Statement{
    ID
}

val atom = mapOf(
        Pair(TypeName.Letter,Condition(LowLetter(),UpperLetter())),
        Pair(TypeName.Point,Special(".")),
        Pair(TypeName.Minus,Special("-"))
)


val type = mapOf(
        Pair(TypeName.PositiveInt,Cycle.notIgnore(Digital())),
        Pair(TypeName.NegativeInt,Link.link(atom(TypeName.Minus),Cycle.notIgnore(Digital()))),
        Pair(TypeName.Int,Link.link(Optional(atom(TypeName.Minus)),Cycle.notIgnore(Digital()))),
        Pair(TypeName.String,Cycle.notIgnore(atom(TypeName.Letter)))
)

fun atom(key: TypeName): AbsType = atom[key] as AbsType
fun type(key: TypeName): AbsType = type[key] as AbsType

val statement = mapOf(
        Pair(Statement.ID,Link.link(type(TypeName.String),atom(TypeName.Minus),type(TypeName.Int)))
)

fun main(args: Array<String>) {
    while (true){
        println("""
            You can:
            |Match statement :ps
            |Match type :pt
            |Create bnf :cb
            |Return :q
        """.trimIndent())
        when(readLine()){
            ":ps" -> parser(statement)
            ":pt" -> parser(type)
            ":cb" -> parser()
            ":q" -> return
            else -> println("Unable to recognize command")
        }
    }
}

fun parser() {
    fun parser(str: String): String{
        for (type in type.values)
            when{
                str.isEmpty() -> return ""
                type.parse(str) -> return "$type${parser(type.split(str))}"
            }
        val fail = Special(AbsType.delIgnore(str).first().toString())
        return "$fail${parser(fail.split(str))}"
    }
    while (true){
        val str = readLine()+""
        if (str==":q") return
        println(parser(str))
    }
}

fun<K> parser(array: Map<K,AbsType>) {
    println("input values")
    while (true){
        val str = readLine()+""
        if (str==":q") return
        var success = false
        array.forEach {
            if (it.value.parse(str)){
                println("<${it.key}>::=${it.value}")
                success = true
            }
        }
        if (success.not()) println("Can't Match")
    }
}