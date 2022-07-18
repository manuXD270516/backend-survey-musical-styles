# Technical Test 3IT (React Native & Spring Boot)
#Desarrollador: Manuel Saavedra 
#Backend

[![CI](https://github.com/thombergs/buckpal/actions/workflows/ci.yml/badge.svg)](https://github.com/thombergs/buckpal/actions/workflows/ci.yml)

Prueba tecnica para medir aptitudes de desarrollo de software.
La lista de requerimientos es la siguiente:

1. Metodo para crear usuarios (instruccciones descritas en el PDF enviado)

#Arquitectura de Software
1. Sprint Boot
2. Java 11
3. Unit Test con Junit & Mockito
4. H2 base de datos en memoria
5. Postman Herramienta documentacion APIs
7. Gradle: Generacion de Build
8. IntellJ IDEA (IDE de desarrollo)

#URL APIs
https://documenter.getpostman.com/view/21907225/UzQxMPQt

#Instrucciones
1. Para Ejecutar, abrir el proyecto con Intellj IDEA y correr la solucion con boton Play
2. Para correr los tests direccionar a la carpeta ***src/test/io.manudev.test3it*** y ejecutar los archivos de test
3. Para ejecutar las APIs (Guiarse en la documentacion de postman adjunta)
4. para validar en base de datos ingresar a la url ```http://localhost:8080/h2Console``` user: sa, pass:

## Companion Articles

* [Hexagonal Architecture with Java and Spring](https://reflectoring.io/spring-hexagonal/)
* [Building a Multi-Module Spring Boot Application with Gradle](https://reflectoring.io/spring-boot-gradle-multi-module/)

## Prerequisites

* JDK 11
* El proyecto utiliza lombox, habilitar las anotaciones JAVA en las configuraciones del IDE.
