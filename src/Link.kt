class Link(var type: Type,val next: Link?): Type(){

    override fun getIndex(str: String): Int = type.getIndex(str)+next?.type!!.getIndex(type.split(str))

    override fun parse(str: String): Boolean{
        if (!type.parse(str)) return false
        if (next == null) return true
        return next.parse(type.split(str))
    }

    override fun toString(): String = type.toString()+ (next?.toString() ?: "")

    companion object {
        fun link(vararg type: Type):Link{
            fun fac(array: Array<Type>):Link? {
                if (array.isEmpty()) return null
                return Link(array.first(),fac(array.drop(1).toTypedArray()))
            }
            return fac(type.toList().toTypedArray()) as Link
        }
    }

}