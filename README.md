# BPMNDoc

BPMNDoc es una herramienta desarrollada para documentar procesos BPMN de manera automática, generando archivos HTML que muestran de manera intuitiva cada uno de los elementos que hacen parte de un proceso.

Integrantes del Equipo: 
 - Camilo Mantilla
 - Daniel Ospina
 - Sebastián Reyes

Este proyecto es realizado para la materia Arquitecturas Empresariales en Escuela Colombiana de Ingeniería 'Julio Garavito'.


## Uso

Para usar la herramienta es necesario tener Java instalado en la maquina, tener el .jar con los binarios del proyecto, o generarlo a partir de el código fuente, y por medio de la terminal usar el comando: 

    java -jar *BPMNDOC.jar* [Nombre del archivo .bpmn]

###### Adicionalmente el archivo Diagrama.bpmn disponible en este repositorio sirve como archivo de prueba para generar el BPMNDoc, tanto con el jar o usando un IDE, la configuración del proyecto esta incluida en el repositorio para Netbeans IDE.

## Descripción del funcionamiento

BPMNDoc parte de tomar un archivo .bpmn en formato XML, parsear su contenido y generar archivos HTML a partir de diferentes plantillas.
Este proceso tiene dos etapas clave:

 1. Desde la sección Main del programa, invocar el Parser XML que por medio de la librería Xerces de Apache, parsea el archivo indicado.
 2. Enviar el objeto Parseado a el constructor HTML que se encarga de leer las plantillas HTML y generar los archivos HTML necesarios.

## Documentación del proyecto [JAVADOC]

Este proyecto se encuentra completamente documentado, y se puede encontrar el Javadoc en este mismo repositorio, y se puede acceder al índice haciendo clic [AQUI](apidocs/index.html).

## Navegando la Documentación BPMNDoc.

Al ejecutar BPMNDoc, se crea una carpeta junto al archivo .jar llamada BPMNDoc, si accedemos a esta podemos abrir el archivo index.html con nuestro navegador favorito, éste es un índice que nos muestra el nombre del proceso, y la descripción.

Debajo de esto encontramos como subtítulos cada línea de nado y su documentación respectiva, y dentro de estas, los elementos pertenecientes a cada línea de nado, a los que podemos acceder por medio del link.

En cada elemento se encuentra su nombre y su tipo, además de la descripción. También existe la posibilidad de navegar los elementos siguientes y anteriores a los cuales esta conectado este elemento, permitiendo así seguir el flujo del proceso sin necesidad de salir al menú principal.

## Dependencias y Librerías del Proyecto

 - Xerces by Apache, para el parseo de archivos XML
 - Commons IO by Apache, para el manejo de archivos y generación de HTMLs.
 - PMD Plugin by Apache, para análisis de codigo.
 - Maven JAR Plugin, para la generación del Javadoc del proyecto.
 - Maven Assembly Plugin, para la generación de un archivo JAR Completo durante el uso de `mvn site`
