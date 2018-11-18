class Condition(vararg var types: Type): Type(){

    override fun getIndex(str: String): Int {
        for (type in types){
            val index = type.getIndex(delIgnore(str))
            if (index>0) return index
        }
        return 0
    }

    override fun parser(str: String): Boolean {
        for (type in types)
            if (type.parser(delIgnore(str)))
                return true
        return false
    }

    override fun toString(): String {
        var str = ""
        for (type in 0..types.size-2)
            str += types[type].toString()+"|"
        return str+types.last().toString()
    }
}