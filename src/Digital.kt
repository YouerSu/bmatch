class Digital: Type(){
    override fun getIndex(str: String): Int = if (parse(delIgnore(str))) 1 else 0
    override fun parse(str: String): Boolean = str.firstOrNull() in '0'..'9'
    override fun toString(): String = "<Digital>"
}