package bonilla.mariela;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("bonilla.mariela");

        noClasses()
            .that()
            .resideInAnyPackage("bonilla.mariela.service..")
            .or()
            .resideInAnyPackage("bonilla.mariela.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..bonilla.mariela.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
