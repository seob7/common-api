package io.github.seob7.example.error;

import io.github.seob7.Api;
import io.github.seob7.BaseErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleErrorController {


    /**
     * Throws a custom exception which will be handled by the exception handler.
     */
    @GetMapping("/error1")
    public ResponseEntity<Api<BaseErrorCode>> error1() {
        throw new CustomExampleException(ExampleErrorCode.USER_NOT_FOUND);
    }

    /**
     * Returns an error response based on a predefined error code.
     */
    @GetMapping("/error2")
    public ResponseEntity<Api<BaseErrorCode>> error2() {
        return Api.ERROR(ExampleErrorCode.USER_NOT_FOUND);
    }

    /**
     * Returns an error response with a custom message and status without using an enum.
     */
    @GetMapping("/error3")
    public ResponseEntity<Api<String>> error3() {
        return Api.ERROR("Custom error message", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
