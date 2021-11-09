package demo.app

import groovy.transform.CompileStatic
import io.micronaut.context.ApplicationContextBuilder
import io.micronaut.context.ApplicationContextCustomizer
import io.micronaut.context.annotation.MicronautApplication
import io.micronaut.runtime.Micronaut

//@CompileStatic
//class GroovyApplication {
//    @ContextConfigurer
//    static class Configurer implements ApplicationContextCustomizer {
//        @Override
//        void customize(ApplicationContextBuilder builder) {
//            println("Groovy configurer loaded")
//            builder.deduceEnvironment(false);
//        }
//    }
//
//    static void main(String[] args) {
//        Micronaut.run(Application, args)
//    }
//}

@CompileStatic
@MicronautApplication
class GroovyApplication implements ApplicationContextCustomizer {
    @Override
    void customize(ApplicationContextBuilder builder) {
        println("Groovy configurer loaded")
        builder.deduceEnvironment(false)
    }

    static void main(String[] args) {
        Micronaut.run(Application, args)
    }
}
