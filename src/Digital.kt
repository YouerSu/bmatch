class Digital: Type(){
    override fun getIndex(str: String): Int = if (parser(str)) 1 else 0
    override fun parser(str: String): Boolean = str[0] in '0'..'9'
    override fun toString(): String = "<Digital>"
}