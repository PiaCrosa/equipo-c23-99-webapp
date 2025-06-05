# README de Pruebas

## Introducción

Este proyecto utiliza pruebas automatizadas para verificar la funcionalidad de la página de registro. Las pruebas se implementaron utilizando **Pytest** para la ejecución de las pruebas y **Playwright** para la automatización del navegador. A continuación se describe cómo funcionan las pruebas, la estructura de datos de prueba y cómo ejecutarlas.

## Estructura de las Pruebas

### Generación de Datos de Prueba

En este proyecto, los datos de prueba se generan dinámicamente utilizando una función específica en el archivo `generate_data.py`. Esto permite que las pruebas sean más flexibles y puedan adaptarse a diferentes escenarios, como cuando falta un campo obligatorio o cuando un campo contiene un valor inválido.

El objetivo de esta estrategia es simular diferentes situaciones que podrían ocurrir durante el registro, como:

- Campos faltantes (por ejemplo, si el campo DNI está vacío).
- Valores inválidos (como un DNI con 7 dígitos en lugar de 8).

De esta manera, es posible verificar que la aplicación maneje correctamente estos casos y que los mensajes de error sean los esperados.

### Uso de Page Object Model (POM)

El enfoque utilizado para la automatización de pruebas sigue el patrón **Page Object Model** (POM). Este patrón ayuda a mantener el código limpio y modular, separando la lógica de interacción con la interfaz de usuario de las pruebas en sí. En el proyecto, la clase `RegistrationPage` actúa como el objeto de página que encapsula todas las interacciones posibles con la página de registro.

### Navegación a la Página de Registro

Para navegar a la página de registro se utiliza la interfaz de usuario en lugar de modificar directamente la URL. Esto es necesario para evitar un error 404 que ya fue reportado.

### Pruebas Implementadas

Las pruebas automatizadas cubren los siguientes casos:

- Verificar que se muestra un **tooltip** cuando un campo obligatorio está vacío.
- Verificar que el **DNI** tiene la cantidad correcta de dígitos (debe tener 8 dígitos).
- Verificar que el **teléfono** contiene solo valores numéricos.
- Verificar que el campo **confirmar contraseña** coincide con la **contraseña**.

Existen otras validaciones, como las de la contraseña (que debe contener al menos una mayúscula, una minúscula, un dígito y un carácter especial). Estas validaciones aún no están implementadas, pero se agregarán a las pruebas cuando se implementen en la aplicación.

Actualmente, solo se han automatizado algunos de estos casos como ejemplos. A medida que la aplicación se estabilice, se automatizarán más casos.

## Ejecución de las Pruebas

Pasos para la ejecución:

1. **Instalar las dependencias necesarias:**

   Estando en la carpeta `tests`, ejecutar el siguiente comando para instalar las dependencias:

   ```bash
   pip install -r requirements.txt
   
2. **Instalar Playwright:**

   Para instalar Playwright y sus dependencias, ejecutar los siguientes comandos:

   ```bash
   playwright install
   playwright install-deps
   
3. **Ejecutar las pruebas:**

   Una vez instaladas las dependencias, para ejecutar todas las pruebas:

   ```bash
   pytest