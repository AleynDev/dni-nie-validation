# Validación de DNI/NIE en Java con Spring Boot

### 📖 Publicación relacionada

Este proyecto es un muestra práctica que acompaña a una publicación sobre validaciones personalizadas en Spring Boot.  
Puedes leer el artículo completo aquí:  
👉 [Validaciones personalizadas en Spring boot](https://www.linkedin.com/feed/update/urn:li:activity:7320473490090827776/)

Este proyecto es un ejemplo práctico de cómo crear una **anotación personalizada** en Java utilizando Spring Boot para validar documentos de identidad españoles: **DNI y NIE**.

Forma parte de una serie de publicaciones sobre buenas prácticas de validación en APIs desarrolladas con Spring.

---

## 🧠 ¿Qué vas a encontrar en este repositorio?

- Una anotación personalizada: `@ValidSpanishDocument`
- Un validador que implementa la lógica para verificar DNI y NIE válidos
- Un DTO request de ejemplo para registro de usuarios con validación integrada
- Un controlador REST donde se recibe el DTO request y se aplica la validación automáticamente

Todo ello usando **Bean Validation (JSR 380)** y una estructura limpia y modular.

---

## 🗂️ Estructura del proyecto

```plaintext
src/
└── main/
    ├── java/com/example/dni_validation/
    │   ├── controllers/
    │   │   ├── errors/
    │   │   │   └── GlobalExceptionHandler.java
    │   │   ├── request/
    │   │   │   └── UserRegistrationRequest.java
    │   │   └── UserController.java
    │   ├── utils/
    │   │   ├── annotations/
    │   │   │   └── ValidSpanishDocument.java
    │   │   └── validations/
    │   │       └── SpanishDocumentValidator.java
    │   └── DniValidationApplication.java
    └── resources/
        ├── static/
        ├── templates/
        └── application.properties
```

---

## ⚙️ ¿Cómo funciona la validación?

1. El campo del DTO `document` se anota con `@ValidSpanishDocument`.
2. Esta anotación está vinculada a la clase `SpanishDocumentValidator`, que implementa la lógica para validar:
    - Formato correcto
    - Cálculo de la letra de control
    - Compatibilidad con DNI y NIE
3. Si el documento es válido, la solicitud se procesa con normalidad.
4. Si no es válido, Spring devuelve un error `400 Bad Request` de forma automática.

---

## ▶️ Cómo probarlo

1. Clona este repositorio:
   ```bash
   git clone https://github.com/AleynDev/dni-nie-validation.git
   ```

2. Ejecuta la aplicación

   Abre el proyecto en tu IDE favorito (se recomienda [IntelliJ IDEA](https://www.jetbrains.com/idea/) para una mejor integración con Spring Boot).

   Una vez abierto, localiza la clase `DniValidationApplication.java`, haz clic derecho sobre ella y selecciona **Run** (o utiliza el ícono de ▶️).

   Esto iniciará la aplicación y podrás acceder a ella en `http://localhost:8080`.


3. Prueba con documentos válidos

   Puedes generar DNIs y NIEs válidos para realizar pruebas utilizando la siguiente herramienta online:

   👉 [https://testingdatagenerator.com/doi.html](https://testingdatagenerator.com/doi.html)

   Te recomendamos probar con documentos válidos y también modificar las letras de control para comprobar que el validador detecta errores correctamente.

   Aquí tienes un ejemplo de petición `curl` que puedes ejecutar desde tu terminal:

   ```bash
   curl -X POST http://localhost:8080/users/register \
     -H "Content-Type: application/json" \
     -d '{
       "full_name": "Juan Perez",
       "email": "juan@example.com",
       "document": "12345678Z"
     }'
   ```

   Si el documento es válido, recibirás una respuesta HTTP 200 OK.  
   Si no lo es, obtendrás una respuesta HTTP 400 con los errores de validación en formato JSON.

---

### 🌟 ¿Te ha resultado útil?

Si este proyecto te ha servido o te ha parecido interesante, considera dejarle una ⭐ en GitHub.  
¡Es una buena forma de apoyar y motivar a seguir creando contenido útil!