package com.mariesto.buckpal;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import org.junit.jupiter.api.Test;
import com.mariesto.buckpal.archunit.HexagonalArchitecture;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.elements.ClassesShouldConjunction;

class DependencyRuleTests {
    @Test
    void domainLayerDoesNotDependOnApplicationLayer() {
        ClassesShouldConjunction conjunction = noClasses().that().resideInAPackage("com.mariesto.buckpal.account.domain").should()
                                                          .dependOnClassesThat().resideInAnyPackage("..application..");
        conjunction.check(new ClassFileImporter().importPackages("com.mariesto.buckpal"));
    }

    @Test
    void validateRegistrationContextArchitecture() {
        HexagonalArchitecture.boundedContext("com.mariesto.buckpal.account")
                             .withDomainLayer("domain")
                             .withAdaptersLayer("adapter")
                             .incoming("in.web")
                             .outgoing("out.persistence")
                .and()
                             .withApplicationLayer("application")
                .services("service")
                .incomingPorts("port.in")
                .outgoingPorts("port.out")
                .and()
//                .withConfiguration("configs")
                .check(new ClassFileImporter().importPackages("com.mariesto.buckpal.."));
    }
}
