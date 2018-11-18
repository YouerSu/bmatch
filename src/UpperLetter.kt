class UpperLetter:Type(){

    override fun getIndex(str: String): Int = if (parser(str)) 1 else 0
    override fun parser(str: String): Boolean = str.first() in 'A'..'Z'
    override fun toString(): String = "<UpperLetter>"
}