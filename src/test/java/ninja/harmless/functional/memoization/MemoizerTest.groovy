package ninja.harmless.functional.memoization

import ninja.harmless.functional.Stopwatch
import spock.lang.Specification

import java.util.function.Function

/**
 * @author benjamin.krenn@leftshift.one - 8/5/18.
 * @since 1.0.0
 */
class MemoizerTest extends Specification {

    Function<Integer, Integer> slowFuntion = { integer ->
        Thread.sleep(2000)
        return integer**2
    }

    void "unmemoized"() {
        expect:
            def result1 = Stopwatch.start(slowFuntion).apply(2)
            println("Result=$result1")

            def result2 = Stopwatch.start(slowFuntion).apply(2)
            println("Result=$result2")
    }

    void "memoized"() {
        expect:
            Function<Integer, Integer> memoized = Memoizer.memoize(slowFuntion)
            def r1 = Stopwatch.start(memoized).apply(2)
            println("Result=$r1")
            def r2 = Stopwatch.start(memoized).apply(2)
            println("Result=$r2")
    }
}
