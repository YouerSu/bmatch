class Optional(val type: Type): Type(){
    override fun getIndex(str: String): Int = type.getIndex(str)
    override fun parse(str: String): Boolean =true
    override fun toString(): String = "[$type]"
}