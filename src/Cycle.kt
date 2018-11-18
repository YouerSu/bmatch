class Cycle(val type: Type, val ignore: Boolean = true): Type(){

    override fun getIndex(str: String): Int {
        val string = delIgnore(str)
        val index = type.getIndex(string)
        if (index==0) return 0
        return index+getIndex(type.split(string))
    }

    override fun parser(str: String): Boolean = type.parser(delIgnore(str))||ignore
    override fun toString(): String = if (ignore) "{$type}" else "$type{$type}"
    companion object {
        fun ignore(type: Type) = Cycle(type)
        fun notIgnore(type: Type) = Cycle(type,false)
    }

}