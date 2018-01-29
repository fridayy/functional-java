package ninja.harmless.functional.common

import spock.lang.Specification

import java.util.function.Consumer
import java.util.function.Function

/**
 * @author bnjm@harmless.ninja - 1/29/18.
 */
class ResultTest extends Specification {

    void "Result<T> showcase"() {
        given:
            Consumer<String> successConsumer = { value -> println("Success! Result: $value")}
            Consumer<String> errorConsumer = { errorMessage -> print("An error occured: $errorMessage")}
        when:
            Function<String, Result<String>> uppercase = { str ->
                if (str == null) {
                    return Result.failure("Can not apply uppercase(str) on null")
                } else if (str.length() == 0) {
                    return Result.failure("Can not apply uppercase(str) on empty strings")
                } else {
                    return Result.success(str.toUpperCase())
                }
            }
    }
}
