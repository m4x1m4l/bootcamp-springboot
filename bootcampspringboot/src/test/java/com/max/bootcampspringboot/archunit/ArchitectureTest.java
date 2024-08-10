package com.max.bootcampspringboot.archunit;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class ArchitectureTest {
    @Test
    public void packageDependencyCheck() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.max.bootcampspringboot");

        ArchRule rule = classes().that().resideInAPackage("..api..")
                .should().onlyHaveDependentClassesThat().resideInAnyPackage("..api..", "..service..");

        rule.check(importedClasses);

        rule = noClasses().that().resideInAPackage("..api..")
                .should().onlyHaveDependentClassesThat().resideInAPackage("..data..");

        rule.check(importedClasses);

        rule = classes().that().resideInAPackage("..service..")
                .should().onlyHaveDependentClassesThat().resideInAnyPackage("..api..", "..data..", "..service..");

        rule.check(importedClasses);

        rule = classes().that().resideInAPackage("..data..")
                .should().onlyHaveDependentClassesThat().resideInAnyPackage( "..data..", "..service..");

        rule.check(importedClasses);

        rule = noClasses().that().resideInAPackage("..data..")
                .should().onlyHaveDependentClassesThat().resideInAPackage("..api..");

        rule.check(importedClasses);
    }



//classDependencyChecks: not usefull in the current state of the application

    @Test
    public void classAndPackageContainmentCheck() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.max.bootcampspringboot");
        ArchRule rule = classes().that().haveNameMatching(".*Controller")
                .should().resideInAPackage("..api..");
        rule.check(importedClasses);

        rule = classes().that().haveNameMatching(".*Service")
                .should().resideInAPackage("..service..");
        rule.check(importedClasses);

        rule = classes().that().haveNameMatching(".*Repository")
                .should().resideInAPackage("..data..");
        rule.check(importedClasses);

        rule = classes()
                .that().areAnnotatedWith(RestController.class)
                .should().resideInAPackage("..api..");
        rule.check(importedClasses);

        rule = classes()
                .that().areAnnotatedWith(Entity.class)
                .should().resideInAPackage("..data..");
        rule.check(importedClasses);

        rule = classes()
                .that().haveSimpleNameEndingWith("Repository")
                .should().resideInAPackage("..data.repository..");
        rule.check(importedClasses);


    }

    //inheritanceCheck: not usefully in the current state of the application


    @Test
    public void annotationChecks() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.max.bootcampspringboot");

        ArchRule rule = classes()
                .that().haveSimpleNameEndingWith("Service")
                .should().beAnnotatedWith(Service.class);

        rule.check(importedClasses);

        rule = classes()
                .that().resideInAPackage("..entity..")
                .should().beAnnotatedWith(Entity.class);

        rule = classes()
                .that().haveSimpleNameEndingWith("Controller")
                .should().beAnnotatedWith(RestController.class);

        rule.check(importedClasses);

    }


    @Test
    public void layeredArchitectureCheck() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.max.bootcampspringboot");
        ArchRule rule = layeredArchitecture()
                .consideringAllDependencies()
                .layer("api").definedBy("..api..")
                .layer("service").definedBy("..service..")
                .layer("data").definedBy("..data..")

                .whereLayer("api").mayNotBeAccessedByAnyLayer()
                .whereLayer("service").mayOnlyBeAccessedByLayers("api")
                .whereLayer("data").mayOnlyBeAccessedByLayers("service");

        rule.check(importedClasses);
    }

    @Test
    public void cycleCheck() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.max.bootcampspringboot");
        ArchRule rule = slices().matching("com.max.(*)..").should().beFreeOfCycles();

        rule.check(importedClasses);
    }
}
