class Special(val sign: String): Type(){

    override fun getIndex(str: String): Int = if (parse(delIgnore(str))) sign.length else 0
    override fun parse(str: String): Boolean = sign == str.substring(0,sign.length)
    override fun toString(): String = "<Special>"
}