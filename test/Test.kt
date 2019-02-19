import org.junit.Test

class Test {

    constructor()

    fun <T>T.shouldBe(result: T): Unit {
        if (result != this) error("Error:result is $result,but receive is $this")
    }

    @Test
    fun letter() {
        with(LowLetter()) {
            parse("a").shouldBe(true)
            split(" d").shouldBe("")
        }
        with(UpperLetter()) {
            parse("D").shouldBe(true)
            split("   E").shouldBe("")
        }
    }


    @Test
    fun digital() {
        with(Digital()) {
            parse("3").shouldBe(true)
            split(" 6").shouldBe("")
        }
    }

    @Test
    fun special() {
        with(Special("YouYax")) {
            parse("YouYax").shouldBe(true)
            split("  YouYax").shouldBe("")
        }
    }

    @Test
    fun optional() {
        with(Optional(Digital())) {
            parse("").shouldBe(true)
            split("1").shouldBe("")
        }
    }

    @Test
    fun link() {
        with(Link.link(Special("-"), Digital(), Cycle(LowLetter()))) {
            parse("-3asdaa").shouldBe(true)
            split("-1qwertyuiopasdfghjklzxcvbnmYouYax").shouldBe("YouYax")
        }
    }
}