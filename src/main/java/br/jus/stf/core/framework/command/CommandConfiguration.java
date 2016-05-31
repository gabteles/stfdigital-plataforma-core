package br.jus.stf.core.framework.command;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Configuração que habilita o uso do AspectJ com o Spring
 * 
 * @author lucas.rodrigues
 *
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"br.jus.stf.framework.command"})
public class CommandConfiguration {

}
