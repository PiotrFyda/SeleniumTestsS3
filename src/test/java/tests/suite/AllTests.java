package tests.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.HomePageTests;
import tests.SignUpTests;
import tests.VacationsTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SignUpTests.class,
        HomePageTests.class,
        VacationsTests.class
})

public class AllTests {
}