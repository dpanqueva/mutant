# Mutant
## _Proyecto para evaluar ADN humanos y determinar si son o no mutantes._

[![N|Solid](https://github.com/dpanqueva/mutant/blob/main/img/sombra.png)]()

## Instrucciones
Recomendaciones a tener en cuenta:
- Ambiente Java local en la versión 11 (OpenJdk)
- Una base de datos en postgressql o en su defecto ajustar el contexto de trabajo del microservicio a H2.
- Tener instalado el gestor de paquetes Maven.
- Postman o soap ui (herramienta para realizar consumos a rest).

## Instalación

Descargue el proyecto del repositorio [GitHub](https://github.com/dpanqueva/mutant).

Siguiendo el comando:

```sh
cd carpeta-proyecto
git clone https://github.com/dpanqueva/mutant.git
```

Una vez descargo el proyecto es necesario descargar los paquetes necesarios para su correcto funcionamiento.
Para este proceso seguimos el siguiente comando:

```sh
mvn clean install
```

## Run (desplegar proyecto)
Para correr el proyecto, se debe ingresar a la carpeta donde se descargo el código fuente (git), abrir la consola y ejecutar el siguiente comando:
Antes de desdplegar el proyecto, debemos ubicar la carpeta (en la raiz del pryecto) target, allí se encuentra el jar generado por maven con todo lo correspondiente a la ejecución, nos situamos en dentro de esta carpeta y ejecutamos en consola:
```sh
java -jar mutant-0.0.1-SNAPSHOT.jar
```
Ya con esto nuestro proyecto ha quedado desplegado y listo para probar.

[![N|Solid](https://github.com/dpanqueva/mutant/blob/main/img/despliegueJava.PNG)]()


## Ejecución
Para la ejecución, en mi caso utilizare [Postman](https://www.postman.com/downloads/), ya en la herramienta debemos tener en cuenta los endposints:

| Operacion | Endpoint |
| ------ | ------ |
| GET | [localhost:8005/api/V1/stats][PlDb] |
| POST | [localhost:8005/api/V1/mutant][PlGh] |

para la operación POST el cuerpo del consumo es:
> {
> "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
> }

## Respuestas esperadas
Para la operación POST, esperamos las respuestas.
Respuesta con Éxitoso (es mutante):
> {
>    "code": 200,
>    "dateAt": "2021-07-27 11:26:24",
>    "message": "Successfully evaluated DNA"
>}

Respuesta 403 (no mutante):
> {
>    "code": 403,
>    "dateAt": "2021-07-27 15:23:48",
>    "message": "The evaluated dna belongs to a human"
> }

## Tech
Para la solución de este reto, se implemento:
| Herramienta | Url |
| ------ | ------ |
| Java | [OpenJdk](https://openjdk.java.net/projects/jdk/11/) |
| Maven | [Maven](https://maven.apache.org/download.cgi) |
| Intellij Idea | [Intellij](https://www.jetbrains.com/es-es/idea/download) |
| GitHub | [https://github.com/](https://github.com/) |
| Spring Boot | [https://start.spring.io/](https://start.spring.io/) |
| H2 | [H2 Databse](https://mvnrepository.com/artifact/com.h2database/h2/1.4.200) |
| PostgresSql | [H2 Databse](https://www.postgresql.org/download/) |
| Junit5 | [Junit](https://junit.org/junit5/) |

## Pruebas unitarias
Coverages en el 80% de la ejecución de las pruebas.
[![N|Solid](https://github.com/dpanqueva/mutant/blob/main/img/coverage.PNG)]()

## Gracias!!

