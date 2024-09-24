# consistgen

This simple Java library provides static and dynamic implementations for generating timestamps, random strings, and UUIDs, offering a structured way to inject predictable data into test cases.

## Purpose

Directly calling methods such as `System.currentTimeMillis()` for the current time, `UUID.randomUUID()` for generating UUIDs, or using `SecureRandom` for random strings can complicate testing due to their inherently unpredictable outputs. This library offers interfaces and classes that wrap these methods, enabling dependency injection to provide static responses during testing while maintaining normal behavior in production.

Although mocking frameworks like Mockito can mock implementations that call these methods, this library simplifies the process by allowing you to inject predictable data into your implementation, which can be easily swapped out for different implementations during testing.

## Getting Started

This library requires Java 17 and is available in the Maven Central Repository:

```xml
<dependency>
    <groupId>com.unitvectory</groupId>
    <artifactId>consistgen</artifactId>
    <version>0.0.7</version>
</dependency>
```

## Dependency Injection

The `EpochTimeProvider` interface implementation wraps the `System.currentTimeMillis()` method, allowing you to obtain the current system time in milliseconds or seconds. Similarly, the `UuidGenerator` interface implementation wraps the `UUID.randomUUID().toString()` function to generate UUIDs. The  `StringProvider` interfaces utilizes and alphabet to generate random strings of a specified length.

The following example is a program demonstrating how to use dependency injection to insulate the implementation from the underlying details.  The following is a simple example of dependency injection in Java, the same principle can be applied to frameworks like Spring Boot.

```java
package example;

import com.unitvectory.consistgen.epoch.EpochTimeProvider;
import com.unitvectory.consistgen.epoch.SystemEpochTimeProvider;
import com.unitvectory.consistgen.string.RandomStringProvider;
import com.unitvectory.consistgen.string.StringProvider;
import com.unitvectory.consistgen.uuid.RandomUuidGenerator;
import com.unitvectory.consistgen.uuid.UuidGenerator;

public class Demo {

    private final EpochTimeProvider epochTimeProvider;

    private final UuidGenerator uuidGenerator;

    private final StringProvider stringProvider;

    public Demo(EpochTimeProvider epochTimeProvider, UuidGenerator uuidGenerator, StringProvider stringProvider) {
        // Use dependency injection to insulate implementation from underlying details
        this.epochTimeProvider = epochTimeProvider;
        this.uuidGenerator = uuidGenerator;
        this.stringProvider = stringProvider;
    }

    public String build() {
        StringBuilder out = new StringBuilder();

        // Get the current epoch time in milliseconds
        long now = epochTimeProvider.epochTimeMilliseconds();

        // Output the current epoch time
        out.append("Current epoch time: " + now + "\n");

        // Generate a UUID
        String uuid = uuidGenerator.generateUuid();

        // Output the generated UUID
        out.append("Generated UUID: " + uuid + "\n");

        // Generate a random string
        String string = stringProvider.generate(10);

        // Output the generated string
        out.append("Generated string: " + string + "\n");

        return out.toString();
    }
}
```

The production implementation will utilize the `SystemEpochTimeProvider`, `RandomUuidGenerator`, and `RandomStringProvider` classes to provide access to the production-ready implementation.

```java
package example;

import com.unitvectory.consistgen.epoch.SystemEpochTimeProvider;
import com.unitvectory.consistgen.uuid.RandomUuidGenerator;

public class Run {
    
    public static void main(String[] args) {
        // Create a new demo instance
        Demo demo = new Demo(SystemEpochTimeProvider.getInstance(), RandomUuidGenerator.getInstance(),
                RandomStringProvider.getInstance());

        // Run the demo
        System.out.println(demo.build());
    }
}
```

Running the program will output something like this, which will vary each time the application is run. The time will be the current system time and the UUID will be randomly generated. While this is intentional for when the application is running, it can make writing test cases challenging.

```text
Current epoch time: 1727227278720
Generated UUID: 309e0b9a-632d-4dd7-8751-c5c0d29f725f
Generated string: lBDo1sZw4H

```

We can use the `StaticEpochTimeProvider`, `StaticUuidGenerator`, and  classes to provide static responses for these methods in our test cases without changing the implementation through the use of dependency injection. This allows us to write test cases that can assert the expect output of the program to verify that it is working as expected.

```java
package example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.unitvectory.consistgen.epoch.EpochTimeProvider;
import com.unitvectory.consistgen.epoch.StaticEpochTimeProvider;
import com.unitvectory.consistgen.string.StaticStringProvider;
import com.unitvectory.consistgen.string.StringProvider;
import com.unitvectory.consistgen.uuid.StaticUuidGenerator;
import com.unitvectory.consistgen.uuid.UuidGenerator;

public class DemoTest {

    @Test
    void test() {
        // Use the implementation to generate a static output
        EpochTimeProvider epochTimeProvider = StaticEpochTimeProvider.builder().epochTimeMilliseconds(1234567890L)
                .build();
        UuidGenerator uuidGenerator = StaticUuidGenerator.builder().uuid("12345678-1234-5678-1234-567812345678")
                .build();
        StringProvider stringProvider = StaticStringProvider.builder().alphabet("abcde").build();

        // Test the implementation and verify the output
        Demo demo = new Demo(epochTimeProvider, uuidGenerator, stringProvider);
        assertEquals("Current epoch time: 1234567890\n" +
                "Generated UUID: 12345678-1234-5678-1234-567812345678\n" +
                "Generated string: abcdeabcde\n", demo.build());
    }
}
```

This test case will consistently pass as the output is predictable and can be asserted. While this is library wraps very simple underlying methods, the pattern here of using dependency injection to insulate the implementation from the underlying details simplifies testing and allows for predictable test cases.

## Implementations

Each interface provides multiple implementations to allow for different use cases through dependency injection. One implementation will be the production implementation, while others will be used for testing in different scenarios.

The `EpochTimeProvider` interface provides the following implementations:

- `SystemEpochTimeProvider` - Wraps `System.currentTimeMillis()` to provide the current epoch time in milliseconds.
- `StaticEpochTimeProvider` - Provides a static epoch time every time it is called.
- `SettableEpochTimeProvider` - Allows you to set the epoch time to be returned when called.

The `UuidGenerator` interface provides the following implementations:

- `RandomUuidGenerator` - Wraps `UUID.randomUUID().toString()` to generate a random UUID.
- `StaticUuidGenerator` - Provides a static UUID every time it is called.
- `SettableUuidGenerator` - Allows you to set the UUID to be returned when called.

The `StringProvider` interface provides the following implementations:

- `RandomStringProvider` - Generates a random string of a specified length using a default alphabet.
- `StaticStringProvider` - Provides a static string every time it is called based on the alphabet.
- `SettableStringProvider` - Allows you to set the string to be returned when called based on the alphabet.
