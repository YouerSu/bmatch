class LowLetter :Type(){
    override fun getIndex(str: String): Int = if (parse(delIgnore(str))) 1 else 0
    override fun parse(str: String): Boolean = str.firstOrNull() in 'a'..'z'
    override fun toString(): String = "<LowLetter>"
}