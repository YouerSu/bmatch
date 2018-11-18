class UpperLetter:Type(){

    override fun getIndex(str: String): Int = if (parser(delIgnore(str))) 1 else 0
    override fun parser(str: String): Boolean = str.firstOrNull() in 'A'..'Z'
    override fun toString(): String = "<UpperLetter>"
}