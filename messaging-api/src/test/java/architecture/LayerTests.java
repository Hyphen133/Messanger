package architecture;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.Test;

public class LayerTests {

    @Test
    public void domainClassesShouldBeFreeOfCycles() {
        final JavaClasses importedClasses = new ClassFileImporter().importPackages("domain");

        final ArchRule rule = slices().matching("domain.(*)..").should().beFreeOfCycles();
        rule.check(importedClasses);
    }

    @Test
    public void domainDrivenDesignLayerTest(){
        final JavaClasses importedClasses = new ClassFileImporter().importPackages("infrastructure", "domain","ports", "application", "presentation");

        final Architectures.LayeredArchitecture layeredArchitecture = layeredArchitecture()
                .layer("Presentation").definedBy("presentation..")
                .layer("Application Logic").definedBy("application..")
                .layer("Ports").definedBy("ports..")
                .layer("Domain Logic").definedBy("domain..")
                .layer("Infrastructure").definedBy("infrastructure..")

                .whereLayer("Presentation").mayNotBeAccessedByAnyLayer()
                .whereLayer("Application Logic").mayOnlyBeAccessedByLayers("Presentation")
                .whereLayer("Domain Logic").mayOnlyBeAccessedByLayers("Domain Logic", "Application Logic", "Infrastructure", "Ports", "Presentation");


        layeredArchitecture.check(importedClasses);
    }
}
