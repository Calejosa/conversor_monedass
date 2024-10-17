# Conversor de Monedas en Java

![Status](https://img.shields.io/badge/Status-Finalizado-green)
![License](https://img.shields.io/badge/License-MIT-blue)
![Release Date](https://img.shields.io/badge/Release-October%202024-orange)

Este proyecto es un **Conversor de Monedas** implementado en Java que permite realizar conversiones de divisas en tiempo real utilizando una API de tasas de cambio. A través de una interfaz interactiva en la consola, los usuarios pueden seleccionar monedas de su interés y realizar conversiones basadas en tasas de cambio actualizadas.

El proyecto fue realizado como challenge final de la formmación "Java Orientado a Objetos", impartida por Alura Latam, a través del programa One de Oracle.

## Índice

1. [Características Principales](#características-principales)
2. [Tecnologías Utilizadas](#tecnologías-utilizadas)
3. [Requisitos](#requisitos)
4. [Instalación y Ejecución](#instalación-y-ejecución)
5. [Uso](#uso)
   - [Ejemplo de Ejecución](#ejemplo-de-ejecución)
   - [Captura de JSON y Conversión](#captura-de-json-y-conversión)
6. [Personalización y Soluciones Creativas](#personalización-y-soluciones-creativas)
7. [Estructura del Proyecto](#estructura-del-proyecto)
8. [Contribuciones](#contribuciones)
9. [Licencia](#licencia)

## Características Principales

- **Consumo de API**: Utiliza la [Exchange Rate API](https://www.exchangerate-api.com) para obtener tasas de cambio actualizadas.
- **Análisis de JSON**: Los datos de la API son deserializados usando la biblioteca Gson, convirtiendo el JSON en objetos Java.
- **Interfaz de Consola**: Los usuarios interactúan a través de un menú en la consola que permite seleccionar opciones de conversión y proporcionar montos.
- **Soluciones Creativas**: Se implementaron enfoques personalizados para la validación de entradas del usuario, mejorando la experiencia respecto a los lineamientos originales.

## Tecnologías Utilizadas

- **Java**: Lenguaje principal para desarrollar la lógica del conversor.
- **HttpClient**: Para realizar solicitudes HTTP a la API de tasas de cambio.
- **Gson**: Biblioteca para el procesamiento de datos JSON en Java.
- **Scanner**: Para capturar las entradas de usuario en la consola.

## Requisitos

Antes de ejecutar el proyecto, asegúrate de tener lo siguiente:

- **Java Development Kit (JDK)** versión 11 o superior.
- **IntelliJ IDEA** u otro IDE de desarrollo Java.
- **Biblioteca Gson**: Sigue estos pasos para agregar Gson al proyecto en IntelliJ:
  1. Haz clic derecho en la carpeta del proyecto.
  2. Selecciona "Open Module Settings".
  3. Ve a la pestaña "Dependencies" y agrega Gson como biblioteca.

## Instalación y Ejecución

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/conversor-monedas.git
   ```
2. Abre el proyecto en tu IDE preferido.
3. Asegúrate de agregar la biblioteca Gson al proyecto.
4. Obtén tu clave API en Exchange Rate API e insértala en el archivo ApiRequestResponse.java reemplazando el valor de KeyApi.
5. Compila y ejecuta el programa.

## Uso

1. Al ejecutar el programa, verás un menú en la consola con las opciones de conversión de moneda.
2. Selecciona la opción de conversión ingresando el número correspondiente.
3. Introduce la cantidad de dinero que deseas convertir.
4. El programa calculará y mostrará el valor convertido según la tasa actual.

### Ejemplo de Ejecución

```
Bienvenido al conversor de divisas
1. Convertir Dólar a Euro
2. Convertir Euro a Dólar
3. Convertir Dólar a Real Brasileño
4. Convertir Real Brasileño a Dólar
5. Convertir Dólar a Peso Chileno
6. Convertir Peso Chileno a Dólar
7. Salir
Seleccione una opción: 1
La tasa de cambio de USD a EUR es: 0.85
Ingrese la cantidad de dinero a convertir: 100
La cantidad convertida es: 85.000 EUR
```

### Captura de JSON y Conversión

La lógica del programa para capturar la información de la API y convertir la moneda sigue los siguientes pasos:

1. **Codificar las monedas**: Antes de realizar la solicitud a la API, las monedas seleccionadas por el usuario se codifican utilizando URLEncoder.encode() para asegurar que los caracteres especiales en la URL se manejen adecuadamente.

   ```java
   monedaOrigenCodificada = URLEncoder.encode(monedaOrigen, "UTF-8");
   monedaDestinoCodificada = URLEncoder.encode(monedaDestino, "UTF-8");
   ```

2. **Construir la URL y solicitar los datos de la API**: El programa construye la URL de la API incluyendo las monedas codificadas. Utiliza la clase HttpClient de Java para enviar una solicitud a la API de tasas de cambio.

   ```java
   String url = "https://v6.exchangerate-api.com/v6/" + KeyApi + "/pair/" + monedaOrigenCodificada + "/" + monedaDestinoCodificada;
   HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
   ```

3. **Recibir la respuesta en formato JSON**: La API devuelve una respuesta en formato JSON, que incluye la tasa de cambio entre las dos monedas solicitadas. Usamos la clase HttpResponse para capturar el cuerpo de la respuesta.

   ```java
   HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
   String json = response.body();
   ```

4. **Deserializar el JSON con Gson**: La biblioteca Gson permite convertir el JSON en un objeto Java, en este caso, un objeto de la clase TasaDeCambio. El valor de la tasa de conversión se extrae a través de este objeto.

   ```java
   TasaDeCambio tasaDeCambio = new Gson().fromJson(json, TasaDeCambio.class);
   return tasaDeCambio.getConversionRate();
   ```

5. **Cálculo de la conversión**: Una vez obtenida la tasa de cambio, el programa solicita al usuario que ingrese la cantidad de dinero que desea convertir. Luego, se realiza el cálculo utilizando el método convertirDinero() de la clase ConversorDinero.

   ```java
   ConversorDinero conversor = new ConversorDinero(tasaCambio);
   double resultadoConversion = conversor.convertirDinero(cantidadDinero);
   ```

6. **Mostrar el resultado al usuario**: Finalmente, el programa imprime el valor convertido en la consola, redondeado a tres decimales para mayor precisión.

   ```java
   System.out.printf("La cantidad convertida es: %.3f %s%n", resultadoConversion, monedaDestino);
   ```

Este flujo asegura que los datos obtenidos de la API se procesen correctamente y que el usuario reciba un valor de conversión exacto y actualizado.

## Personalización y Soluciones Creativas

En lugar de seguir estrictamente los lineamientos originales, se optó por soluciones personalizadas para mejorar la experiencia del usuario. Algunas de las modificaciones más destacadas son:

- **Validación de Entradas**: Se implementó una verificación detallada para validar las entradas del usuario, incluyendo la gestión de letras, caracteres no válidos y puntos decimales mal formateados. Por ejemplo, si el usuario ingresa letras o símbolos no permitidos, el programa lo informa claramente y solicita que ingrese nuevamente los valores correctos.

- **Manejo de Errores en API**: El programa captura errores relacionados con solicitudes HTTP o fallos en la deserialización de la respuesta JSON, asegurando que no se produzcan fallos inesperados y que el programa continúe funcionando correctamente.

## Estructura del Proyecto

El proyecto está organizado en varios paquetes y clases para mejorar la modularidad:

- **Main**: Clase principal que gestiona el menú y la interacción con el usuario.
- **ApiRequestResponse**: Clase encargada de realizar las solicitudes a la API y obtener la tasa de cambio.
- **ConversorDinero**: Clase que realiza la conversión del dinero utilizando la tasa de cambio obtenida.
- **EjecutaConversiones**: Clase que procesa las opciones seleccionadas por el usuario y coordina las conversiones.
- **TasaDeCambio**: Clase que modela la estructura de la respuesta JSON obtenida de la API.

## Licencia

Este proyecto está licenciado bajo la licencia MIT. Para más detalles, consulta el archivo LICENSE.

## Conclusión

Este proyecto de conversor de monedas fue una excelente oportunidad para aplicar conocimientos en Java y trabajar con APIs externas en tiempo real. La integración de la API de tasas de cambio y la manipulación de datos JSON utilizando la biblioteca Gson me permitió profundizar en el manejo de estructuras de datos complejas y su conversión en objetos Java. 

Además, implementar la lógica para convertir divisas y presentar los resultados de manera interactiva fue un desafío interesante que mejoró mis habilidades en programación orientada a objetos. Optar por soluciones creativas, como el manejo de errores en la entrada del usuario y la validación de datos, me permitió ofrecer una experiencia más fluida y robusta al usuario.

Este proyecto no solo fortaleció mi comprensión de la interacción con APIs y el procesamiento de datos JSON, sino que también me ayudó a mejorar la estructura y modularidad del código en Java, con énfasis en la reutilización de componentes clave.

