package se.ericthelin.junit.singlemethodrunner.samples.gradle;

import static org.hamcrest.MatcherAssert.assertThat;
import static se.ericthelin.junit.singlemethodrunner.samples.gradle.GradleTestDSL.aGradleRun;
import static se.ericthelin.junit.singlemethodrunner.samples.gradle.GradleTestDSL.outputOf;
import static se.ericthelin.junit.singlemethodrunner.samples.gradle.GradleTestDSL.rootDirectoryOf;
import static se.ericthelin.junit.singlemethodrunner.util.StringMatchers.containsAll;

import org.junit.Before;
import org.junit.Test;

import se.ericthelin.junit.singlemethodrunner.samples.Sample;

public class GradleTest {

    private static final Sample GRADLE_SAMPLE = Sample.GRADLE;

    @Before
    public void clean() {
	aGradleRun().in(rootDirectoryOf(GRADLE_SAMPLE)).forTasks("clean")
		.perform();
    }

    @Test
    public void reportsSuccessWhenTestMethodSucceeds() {
	assertThat(
		outputOf(aGradleRun()
			.in(rootDirectoryOf(GRADLE_SAMPLE))
			.forTasks("test")
			.withJVMArguments(
				"-Dtest.method=com.example.StubbedTest.success")),
		containsAll(":test", "BUILD SUCCESSFUL"));
    }

    @Test
    public void reportsFailureWhenTestMethodFails() {
	assertThat(
		outputOf(aGradleRun()
			.in(rootDirectoryOf(GRADLE_SAMPLE))
			.forTasks("test")
			.withJVMArguments(
				"-Dtest.method=com.example.StubbedTest.failure")),
		containsAll(":test", "BUILD FAILED"));
    }
}