# Conversor de Monedas en Java
 Ejemplo de Ejecución

```
Bienvenido al conversor de dinero
1. Convertir Dólar a Euro
2. Convertir Euro a Dólar
3. Convertir Dólar a Soles 
4. Convertir Soles  a Dólar
5. Convertir Dólar a Peso Chileno
6. Convertir Peso Chileno a Dólar
7. Salir
Seleccione una opción: 1
La tasa de cambio de USD a EUR es: 0.85
Ingrese la cantidad de dinero a convertir: 100
La cantidad convertida es: 85.000 EUR
```

### Captura de JSON y Conversión

La lógica del programa sigue los siguientes pasos:

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

## Conclusión

Este proyecto me hizo comprender de la interacción con APIs y el procesamiento de datos JSON, también me ayudó a mejorar la estructura y modularidad del código en Java, con énfasis en la reutilización de componentes clave.

