class Link(var type: Type,val next: Link?): Type(){

    override fun getIndex(str: String): Int {
        return type.getIndex(str)+next?.type!!.getIndex(str)
    }

    override fun parser(str: String): Boolean{
        if (!type.parser(str)) return false
        if (next == null) return true
        return next.parser(type.split(delIgnore(str)))
    }

    override fun toString(): String {
        return type.toString()+ (next?.toString() ?: "")
    }

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