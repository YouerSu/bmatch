class Special(val sign: String): Type(){

    override fun getIndex(str: String): Int = if (parser(delIgnore(str))) sign.length else 0
    override fun parser(str: String): Boolean = sign == str.substring(0,sign.length)
    override fun toString(): String = "<Special>"
}