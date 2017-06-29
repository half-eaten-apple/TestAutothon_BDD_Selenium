$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("speaker_program.feature");
formatter.feature({
  "line": 2,
  "name": "Speaker Program",
  "description": "",
  "id": "speaker-program",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@speakerProgram"
    }
  ]
});
formatter.scenarioOutline({
  "line": 4,
  "name": "Create Speaker Program",
  "description": "",
  "id": "speaker-program;create-speaker-program",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 5,
  "name": "initialize test data \u003cscenario\u003e",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "login salesforce",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "create new interaction",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "add new product",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "add new location",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "add new participant",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "add new logistics",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "add new estimate",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "change interaction status",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "change location status",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "change interaction status to complete",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "pay expenses",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "add disclosure agreement",
  "keyword": "And "
});
formatter.examples({
  "line": 19,
  "name": "",
  "description": "",
  "id": "speaker-program;create-speaker-program;",
  "rows": [
    {
      "cells": [
        "scenario"
      ],
      "line": 20,
      "id": "speaker-program;create-speaker-program;;1"
    },
    {
      "cells": [
        "SpeakerProgram_AZ"
      ],
      "line": 21,
      "id": "speaker-program;create-speaker-program;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 2475499961,
  "error_message": "java.lang.NoClassDefFoundError: com/google/common/net/MediaType\r\n\tat org.openqa.selenium.remote.http.JsonHttpCommandCodec.encode(JsonHttpCommandCodec.java:212)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:131)\r\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:67)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:578)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.startSession(RemoteWebDriver.java:242)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.\u003cinit\u003e(RemoteWebDriver.java:128)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.\u003cinit\u003e(RemoteWebDriver.java:141)\r\n\tat org.openqa.selenium.chrome.ChromeDriver.\u003cinit\u003e(ChromeDriver.java:170)\r\n\tat org.openqa.selenium.chrome.ChromeDriver.\u003cinit\u003e(ChromeDriver.java:159)\r\n\tat com.epam.driver.SetupSelenium.initiateChromeDriver(SetupSelenium.java:67)\r\n\tat com.epam.driver.SetupSelenium.initializeDriver(SetupSelenium.java:39)\r\n\tat com.epam.driver.SetupSelenium$initializeDriver.call(Unknown Source)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:113)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:125)\r\n\tat com.epam.driver.GroovyBaseTest.startingMethodPreReq(GroovyBaseTest.groovy:55)\r\n\tat com.epam.steps.hooks.TestBaseHook.beforeScenario(TestBaseHook.java:18)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:497)\r\n\tat cucumber.runtime.Utils$1.call(Utils.java:37)\r\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:13)\r\n\tat cucumber.runtime.Utils.invoke(Utils.java:31)\r\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\r\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:223)\r\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:211)\r\n\tat cucumber.runtime.Runtime.runBeforeHooks(Runtime.java:201)\r\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:40)\r\n\tat cucumber.runtime.model.CucumberScenarioOutline.run(CucumberScenarioOutline.java:46)\r\n\tat cucumber.runtime.model.CucumberFeature.run(CucumberFeature.java:165)\r\n\tat cucumber.api.testng.TestNGCucumberRunner.runCucumber(TestNGCucumberRunner.java:63)\r\n\tat cucumber.api.testng.AbstractTestNGCucumberTests.feature(AbstractTestNGCucumberTests.java:21)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:497)\r\n\tat org.testng.internal.MethodInvocationHelper.invokeMethod(MethodInvocationHelper.java:84)\r\n\tat org.testng.internal.Invoker.invokeMethod(Invoker.java:714)\r\n\tat org.testng.internal.Invoker.invokeTestMethod(Invoker.java:901)\r\n\tat org.testng.internal.Invoker.invokeTestMethods(Invoker.java:1231)\r\n\tat org.testng.internal.TestMethodWorker.invokeTestMethods(TestMethodWorker.java:127)\r\n\tat org.testng.internal.TestMethodWorker.run(TestMethodWorker.java:111)\r\n\tat org.testng.TestRunner.privateRun(TestRunner.java:767)\r\n\tat org.testng.TestRunner.run(TestRunner.java:617)\r\n\tat org.testng.SuiteRunner.runTest(SuiteRunner.java:334)\r\n\tat org.testng.SuiteRunner.runSequentially(SuiteRunner.java:329)\r\n\tat org.testng.SuiteRunner.privateRun(SuiteRunner.java:291)\r\n\tat org.testng.SuiteRunner.run(SuiteRunner.java:240)\r\n\tat org.testng.SuiteRunnerWorker.runSuite(SuiteRunnerWorker.java:52)\r\n\tat org.testng.SuiteRunnerWorker.run(SuiteRunnerWorker.java:86)\r\n\tat org.testng.TestNG.runSuitesSequentially(TestNG.java:1224)\r\n\tat org.testng.TestNG.runSuitesLocally(TestNG.java:1149)\r\n\tat org.testng.TestNG.run(TestNG.java:1057)\r\n\tat org.apache.maven.surefire.testng.TestNGExecutor.run(TestNGExecutor.java:282)\r\n\tat org.apache.maven.surefire.testng.TestNGXmlTestSuite.execute(TestNGXmlTestSuite.java:83)\r\n\tat org.apache.maven.surefire.testng.TestNGProvider.invoke(TestNGProvider.java:114)\r\n\tat org.apache.maven.surefire.booter.ForkedBooter.invokeProviderInSameClassLoader(ForkedBooter.java:286)\r\n\tat org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:240)\r\n\tat org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:121)\r\nCaused by: java.lang.ClassNotFoundException: com.google.common.net.MediaType\r\n\tat java.net.URLClassLoader.findClass(URLClassLoader.java:381)\r\n\tat java.lang.ClassLoader.loadClass(ClassLoader.java:424)\r\n\tat sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)\r\n\tat java.lang.ClassLoader.loadClass(ClassLoader.java:357)\r\n\t... 60 more\r\n",
  "status": "failed"
});
formatter.scenario({
  "line": 21,
  "name": "Create Speaker Program",
  "description": "",
  "id": "speaker-program;create-speaker-program;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@speakerProgram"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "initialize test data SpeakerProgram_AZ",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "login salesforce",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "create new interaction",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "add new product",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "add new location",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "add new participant",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "add new logistics",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "add new estimate",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "change interaction status",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "change location status",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "change interaction status to complete",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "pay expenses",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "add disclosure agreement",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "SpeakerProgram_AZ",
      "offset": 21
    }
  ],
  "location": "SpeakerProgramSteps.initialize_test_data(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "SpeakerProgramSteps.login_salesforce()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.after({
  "duration": 1016075,
  "status": "passed"
});
});