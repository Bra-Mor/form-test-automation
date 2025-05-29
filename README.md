# 🧪 Form Test Automation

Este proyecto automatiza pruebas para un formulario web utilizando Java, Selenium WebDriver, Cucumber y Gradle. Se sigue el patrón Page Object Model (POM), lo que facilita la mantenibilidad, reutilización y escalabilidad de las pruebas.

---

## 📌 ¿Por qué Page Object Model?

El patrón **Page Object Model (POM)** desacopla la lógica de automatización de la estructura de la UI, permitiendo:

- Mejor organización del código
- Reutilización de componentes
- Mayor legibilidad y facilidad de mantenimiento
- Aislamiento de cambios: si cambia el HTML de la página, solo se modifica una clase

---

## 📁 Estructura del Proyecto

```
src/
├── main/
│ └── java/test/
│ ├─────── pages/ # Clases que representan las páginas (POM)
│ └─────── model/ # Modelos de datos como FormModel
│ └─────── runner/ # Configuración
│ └─────── utils / # Utilidades como generadores de datos
├── resources/
│ └── features/ # Casos de prueba escritos en Gherkin
```

---

## 🧰 Technologies Used

- Java 17
- Selenium WebDriver
- Cucumber + Gherkin
- Gradle
- TestNG
- [Faker](https://github.com/DiUS/java-faker) (for generating fake data)

---

## ▶️ Cómo correr el proyecto

### Pre-requisitos

- Java 17: `brew install openjdk@17`
- Gradle (opcional): `brew install gradle`
  > ⚠️ Este proyecto usa `./gradlew`, así que no necesitas tener Gradle globalmente.

### Ejecutar pruebas

```bash
# Da permisos si es necesario
chmod +x gradlew

# Ejecutar todas las pruebas
gradle test o ./gradlew test
```

---

## 🧪 Casos de Prueba

Los escenarios de prueba están escritos en Gherkin en el archivo:

📄 [`RegisterForm.feature`](/src/test/resources/features/RegisterForm.feature)

Incluyen pruebas como:

- Validación de campos obligatorios
- Formatos inválidos en email o teléfono
- Visualización del modal de confirmación

---

## 🧠 Estrategia de Pruebas

Se aplica una estrategia **centrada en datos** y **validación de flujo completo**, basada en:

- Pruebas funcionales para asegurar la lógica del formulario
- Técnicas de partición de clases equivalentes para validar emails y teléfonos
- Uso de Page Object Model para desacoplar la lógica del DOM

---

## ✅ Resultados

Los logs de resultados se encuentran en:

📄 [`test-output`](test-output\HtmlReport\ExtentHtml.html)

Archivo de visualización de pruebas de Cucumber se encuentra en:

📄 [`Cucumber Reports`](/build/reports/tests/test/index.html)

## Son archivos html puedes abrirlos en tu navegador.

## 📌 Próximos pasos

- [ ] Integrar **GitHub Actions** para ejecución continua
- [ ] Generar más **datos dinámicos con Faker**
- [ ] Crear documentación extendida en la carpeta [`/docs`](./docs)

---

_Looking for english documentation ?_
📄 [`en-Readme`](/src/docs/en-README.md)
