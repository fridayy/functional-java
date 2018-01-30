package ninja.harmless.functional.common

import spock.lang.Specification

import java.util.function.Consumer
import java.util.function.Function

import static ninja.harmless.functional.common.Case.*
/**
 * Showcases {@link Result} and its various applications
 * @author bnjm@harmless.ninja - 1/29/18.
 */
class ResultTest extends Specification {

    Consumer<String> successConsumer = { value -> println("Success! Result: $value") }
    Consumer<String> errorConsumer = { errorMessage -> println("An error occured: $errorMessage") }

    void "Result<T> example #1"() {
        given:
            // Simple function containing imperative control-structures (if..else, no abstraction)
            Function<String, Result<String>> uppercase = { str ->
                if (str == null) {
                    return Result.failure("Can not apply uppercase(str) on null")
                } else if (str.length() == 0) {
                    return Result.failure("Can not apply uppercase(str) on empty strings")
                } else {
                    return Result.success(str.toUpperCase())
                }
            }
        when:
            uppercase.apply("someString").consumeOn(successConsumer, errorConsumer)
            uppercase.apply("").consumeOn(successConsumer, errorConsumer)
            uppercase.apply(null).consumeOn(successConsumer, errorConsumer)
        then:
            noExceptionThrown()
    }

    void "Result<T> example #2"() {
        given:
            Function<String, Result<String>> uppercase = { str ->
                match(defaultCase({ -> Result.success(str.toUpperCase())}),
                matchCondition({ -> str == null}, {-> Result.failure("Can not apply uppercase(str) on null")}),
                matchCondition({-> str.length() == 0}, {-> Result.failure("Can not apply uppercase(str) on empty strings")}),
                matchCondition({-> str.isAllWhitespace()}, {-> Result.failure("Can not apply uppercase(str) on whitespace")})
                )
            }
        when:
            uppercase.apply("someString").consumeOn(successConsumer, errorConsumer)
            uppercase.apply("   ").consumeOn(successConsumer, errorConsumer)
            uppercase.apply("").consumeOn(successConsumer, errorConsumer)
            uppercase.apply(null).consumeOn(successConsumer, errorConsumer)
        then:
            noExceptionThrown()
    }
}
