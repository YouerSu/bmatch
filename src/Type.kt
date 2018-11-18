import kotlin.Int

abstract class Type {
    abstract fun getIndex(str: String): Int
    abstract fun parser(str: String): Boolean

    companion object {
        fun delIgnore(str: String): String{
            fun ignore(char: Char) =
                    char == '\n' ||
                            char == '\t' ||
                            char == ' '

            for (count in str.indices)
                if (!ignore(str[count])) return str.substring(count,str.length)
            return str
        }
    }


    fun split(str: String): String{
        val string = delIgnore(str)
        return  string.substring(getIndex(string),string.length)
    }
}