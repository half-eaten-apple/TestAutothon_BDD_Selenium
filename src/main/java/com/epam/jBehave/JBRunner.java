package com.epam.jBehave;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vsharma on 02-11-2016.
 */
public class JBRunner extends JUnitStories {
    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass().getClassLoader()))
                .useStoryReporterBuilder(
                        new StoryReporterBuilder()
                                .withDefaultFormats()
                                .withFormats(Format.HTML, Format.CONSOLE)
                                .withRelativeDirectory("jbehave-report")
                );
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        ArrayList stepFileList = new ArrayList();
        stepFileList.add(new SpeakerProgramSteps());
        stepFileList.add(new FeedsValidationSteps());
        stepFileList.add(new CalculatorServiceSteps());
        stepFileList.add(new VerifyCapitalRestSteps());

        return new InstanceStepsFactory(configuration(), stepFileList);
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(CodeLocations.codeLocationFromPath("src/test/resources"),
                "**/*.story", "");
    }
    public JBRunner() {
        // If test fails due to timeout, then change the use story timout method timings
        String [] tags = {"+Capital"};
        configuredEmbedder().useMetaFilters(Arrays.asList(tags));

        configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(true)
                .doIgnoreFailureInView(true).useStoryTimeouts("12000");
    }

    @org.testng.annotations.Test
    public void run() throws Throwable {
        super.run();
    }


}
