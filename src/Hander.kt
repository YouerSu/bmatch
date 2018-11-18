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

fun atom(key: TypeName): Type = atom[key] as Type
fun type(key: TypeName): Type = type[key] as Type

val statement = mapOf(
        Pair(Statement.ID,Link.link(type(TypeName.String),atom(TypeName.Minus),type(TypeName.Int)))
)

fun main(args: Array<String>) {
    while (true){
        println("""
            You can:
            |Match statement :ps
            |Match type :pt
            |Return :q
        """.trimIndent())
        when(readLine()){
            ":ps" -> parser(statement)
            ":pt" -> parser(type)
            ":q" -> return
            else -> println("Unable to recognize command")
        }
    }
}

fun<K> parser(array: Map<K,Type>) {
    println("input values")
    while (true){
        val str = readLine()+""
        if (str==":q") return
        var success = false
        array.forEach {
            if (it.value.parser(str)){
                println("<${it.key}>::=${it.value}")
                success = true
            }
        }
        if (success.not()) println("Your input are illegal")
    }
}