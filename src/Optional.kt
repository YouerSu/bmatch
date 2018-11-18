class Optional(val type: Type): Type(){
    override fun getIndex(str: String): Int = type.getIndex(str)
    override fun parser(str: String): Boolean =true
    override fun toString(): String = "[$type]"
}