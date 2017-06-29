package com.epam.steps;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

/**
 * Created by vsharma on 02-11-2016.
 */


@CucumberOptions(strict = false,
features = {"src/test/resources/"},tags = {"@Calculator"},
format = {"pretty", 
		"html:target/site/cucumber-pretty",
		"json:target/cucumber.json"})

public class CukesRunner extends AbstractTestNGCucumberTests{}