import kotlin.Int

abstract class Type {
    abstract fun getIndex(str: String): Int
    abstract fun parser(str: String): Boolean

    fun delIgnore(str: String): String{
        fun ignore(char: Char) =
                char == '\n' ||
                char == '\t' ||
                char == ' '

        for (count in str.indices)
            if (!ignore(str[count])) return str.substring(count,str.length)
        return str
    }

    fun split(str: String): String = str.substring(getIndex(str),str.length)
}