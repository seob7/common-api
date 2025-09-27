package io.github.seob7.example.ok;

import io.github.seob7.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleOkController {

    /**
     * Returns a successful response containing a custom object as the payload.
     * In this example, a User object is returned with both a custom message and a custom HTTP status.
     */
    @GetMapping("/example1")
    public ResponseEntity<Api<User>> example1() {
        return Api.OK(new User("tester", 1), "success response!!", HttpStatus.CREATED);
    }

    /**
     * Returns a successful response with HTTP 200 (OK) status and a message set to null.
     */
    @GetMapping("/example2")
    public ResponseEntity<Api<String>> example2() {
        return Api.OK("example response");
    }

    /**
     * Returns a successful response with a custom HTTP status (201 CREATED).
     */
    @GetMapping("/example3")
    public ResponseEntity<Api<String>> example3() {
        return Api.OK("example response", HttpStatus.CREATED);
    }


    /**
     * Returns a successful response with a custom message while keeping the status as 200 (OK).
     */
    @GetMapping("/example4")
    public ResponseEntity<Api<String>> example4() {
        return Api.OK("example response", "success response!!");
    }

    /**
     * Returns a successful response with both a custom message and a custom HTTP status.
     */
    @GetMapping("/example5")
    public ResponseEntity<Api<String>> example5() {
        return Api.OK("example response", "success response!!", HttpStatus.CREATED);
    }

    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

}
