class Digital: AbsType(){
    override fun getIndex(str: String): Int = if (parse(delIgnore(str))) 1 else 0
    override fun parse(str: String): Boolean = str.firstOrNull() in '0'..'9'
    override fun toString(): String = "<Digital>"
}

class UpperLetter:AbsType(){
    override fun getIndex(str: String): Int = if (parse(delIgnore(str))) 1 else 0
    override fun parse(str: String): Boolean = str.firstOrNull() in 'A'..'Z'
    override fun toString(): String = "<UpperLetter>"
}

class LowLetter :AbsType(){
    override fun getIndex(str: String): Int = if (parse(delIgnore(str))) 1 else 0
    override fun parse(str: String): Boolean = str.firstOrNull() in 'a'..'z'
    override fun toString(): String = "<LowLetter>"
}

class Special(val sign: String): AbsType(){
    override fun getIndex(str: String): Int = if (parse(delIgnore(str))) sign.length else 0
    override fun parse(str: String): Boolean = sign == str.substring(0,sign.length)
    override fun toString(): String = "<Special>"
}

class Optional(val type: AbsType): AbsType(){
    override fun getIndex(str: String): Int = type.getIndex(str)
    override fun parse(str: String): Boolean =true
    override fun toString(): String = "[$type]"
}

class Link(var type: AbsType, val next: Link?): AbsType(){
    override fun getIndex(str: String): Int = type.getIndex(str)+next?.type!!.getIndex(type.split(str))

    override fun parse(str: String): Boolean{
        if (!type.parse(str)) return false
        if (next == null) return true
        return next.parse(type.split(str))
    }
    override fun toString(): String = type.toString()+ (next?.toString() ?: "")

    companion object {
        fun link(vararg type: AbsType):Link{
            fun fac(array: Array<AbsType>):Link? {
                if (array.isEmpty()) return null
                return Link(array.first(),fac(array.drop(1).toTypedArray()))
            }
            return fac(type.toList().toTypedArray()) as Link
        }
    }
}

class Condition(vararg var types: AbsType): AbsType(){
    override fun getIndex(str: String): Int {
        for (type in types){
            val index = type.getIndex(str)
            if (index>0) return index
        }
        return 0
    }

    override fun parse(str: String): Boolean {
        for (type in types)
            if (type.parse(delIgnore(str))) return true
        return false
    }

    override fun toString(): String {
        var str = ""
        for (type in 0..types.size-2)
            str += types[type].toString()+"|"
        return str+types.last().toString()
    }
}

class Cycle(val type: AbsType, private val ignore: Boolean = true): AbsType(){

    override fun getIndex(str: String): Int {
        val string = delIgnore(str)
        val index = type.getIndex(string)
        if (index==0) return 0
        return index+getIndex(type.split(string))
    }

    override fun parse(str: String): Boolean = type.parse(str)||ignore
    override fun toString(): String = if (ignore) "{$type}" else "$type{$type}"
    companion object {
        fun ignore(type: AbsType) = Cycle(type)
        fun notIgnore(type: AbsType) = Cycle(type,false)
    }
}