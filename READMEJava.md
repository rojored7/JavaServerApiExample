# JavaServerApiExample

**JavaServerApiExample** es un proyecto de ejemplo que demuestra cómo integrar **Instana**, una plataforma de observabilidad y monitoreo en tiempo real, en una aplicación Java. Este proyecto utiliza **Maven** para la gestión de dependencias y la construcción del proyecto, facilitando la incorporación de bibliotecas y plugins necesarios para la instrumentación con Instana.

---

## 🎯 Objetivo del Proyecto

El propósito principal de este proyecto es ilustrar cómo instrumentar manualmente una aplicación Java utilizando el **Instana Java SDK**. A través de esta instrumentación, es posible monitorear y rastrear operaciones específicas dentro de la aplicación, permitiendo una observabilidad detallada y personalizada.

---

## 🚀 Funcionalidades Clave

### 🛠️ Integración con Instana

- **Instrumentación Manual**: Uso del **Instana Java SDK** para instrumentar métodos y operaciones específicas dentro de la aplicación. Esto permite crear spans personalizados que representan diferentes partes del flujo de trabajo de la aplicación.
  
- **Configuración mediante Maven**: Inclusión de las dependencias necesarias en el archivo `pom.xml` para integrar el SDK de Instana y otras bibliotecas requeridas.

- **Monitoreo en Tiempo Real**: Una vez instrumentada, la aplicación envía datos de trazas y métricas a Instana, proporcionando información en tiempo real sobre el rendimiento y comportamiento de la aplicación.

---

## 🛠️ Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal del proyecto.

- **Maven**: Herramienta de gestión y construcción de proyectos utilizada para manejar las dependencias y la configuración del proyecto.

- **Instana Java SDK**: Biblioteca proporcionada por Instana para la instrumentación manual de aplicaciones Java.

---

## 📦 Estructura del Proyecto

```
JavaServerApiExample/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/
│       │       └── MyApp.java        # Clase principal de la aplicación
│       └── resources/
├── pom.xml                           # Archivo de configuración de Maven
└── README.md                         # Documentación del proyecto
```

---

## ⚙️ Configuración e Integración con Instana

### 1. Inclusión del Instana Java SDK en el Proyecto

Para instrumentar la aplicación con Instana, es necesario agregar el **Instana Java SDK** como una dependencia en el archivo `pom.xml`. A continuación, se muestra cómo hacerlo:

```xml
<dependency>
    <groupId>com.instana</groupId>
    <artifactId>instana-java-sdk</artifactId>
    <version>1.2.0</version>
</dependency>
```

Esta dependencia está disponible en el repositorio central de Maven y permite acceder a las anotaciones y clases necesarias para la instrumentación manual.

### 2. Instrumentación de Métodos con Anotaciones

Una vez añadida la dependencia, se pueden utilizar las anotaciones proporcionadas por el SDK para marcar los métodos que se desean monitorear. Por ejemplo:

```java
import com.instana.sdk.annotation.Span;
import com.instana.sdk.annotation.Span.Type;

public class MyApp {

    @Span(type = Type.ENTRY, value = "procesar-solicitud")
    public void procesarSolicitud() {
        // Lógica del método
    }

    @Span(type = Type.EXIT, value = "llamar-servicio-externo")
    public void llamarServicioExterno() {
        // Lógica del método
    }
}
```

En este ejemplo:

- `@Span(type = Type.ENTRY, value = "procesar-solicitud")`: Marca el inicio de una nueva traza cuando se invoca el método `procesarSolicitud`.

- `@Span(type = Type.EXIT, value = "llamar-servicio-externo")`: Indica una llamada saliente a un servicio externo dentro del método `llamarServicioExterno`.

Estas anotaciones ayudan a Instana a identificar y rastrear las operaciones específicas dentro de la aplicación.

### 3. Configuración del Agente de Instana

Para que el agente de Instana reconozca las anotaciones y realice la instrumentación adecuada, es necesario especificar los paquetes que contienen las clases instrumentadas en el archivo de configuración del agente (`configuration.yaml`):

```yaml
com.instana.plugin.javatrace:
  instrumentation:
    sdk:
      packages:
        - 'com.example'
```

Esta configuración indica al agente que escanee el paquete `com.example` en busca de anotaciones del SDK de Instana.

---

## 🏃‍♂️ Ejecución del Proyecto

1. **Clonar el Repositorio**:

   ```bash
   git clone https://github.com/rojored7/JavaServerApiExample.git
   ```

2. **Compilar y Ejecutar con Maven**:

   Navega al directorio del proyecto y utiliza Maven para compilar y ejecutar la aplicación:

   ```bash
   cd JavaServerApiExample
   mvn clean install
   mvn exec:java -Dexec.mainClass="com.example.MyApp"
   ```

   Asegúrate de tener configurado correctamente el agente de Instana y que esté ejecutándose para capturar las trazas y métricas generadas por la aplicación.

---

## 📊 Observabilidad con Instana

Una vez que la aplicación está instrumentada y en ejecución, puedes acceder al panel de control de Instana para visualizar:

- **Trazas de Solicitudes**: Detalles de las solicitudes procesadas por la aplicación, incluyendo las operaciones de entrada y salida instrumentadas.

- **Métricas de Rendimiento**: Información sobre el tiempo de respuesta, uso de recursos y otros indicadores clave de rendimiento.

- **Mapas de Dependencias**: Visualización de las interacciones entre los diferentes componentes y servicios con los que la aplicación se comunica.

---

## 🤝 Contribuciones

Si deseas contribuir a este proyecto:

1. Realiza un fork del repositorio.

2. Crea una nueva rama para tu característica o corrección de errores:

   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```

3. Realiza tus cambios y confirma los commits:

   ```bash
   git commit -m "Añadir nueva funcionalidad"
   ```

4. Sube tus cambios al repositorio remoto:

   ```bash
   git push origin feature/nueva-funcionalidad
   ```

5. Abre una Pull Request en GitHub describiendo tus cambios.

---

## 📄 Licencia

Este proyecto se distribuye bajo la licencia MIT. Consulta el archivo `LICENSE` para más detalles.

---

## 👤 Autor

**rojored7**

- GitHub: [https://github.com/rojored7](https://github.com/rojored7)
