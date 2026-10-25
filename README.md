# DOCUMENTACIÓN DEL PUNTO 15
## Práctica 11: Nómina mensual en Java

### 1. Descripción del programa

El programa permite administrar la nómina mensual de una empresa mediante una aplicación de consola desarrollada en Java. Permite seleccionar el mes, registrar empleados fijos y empleados por hora, calcular sus pagos individuales e imprimir un reporte con el total de trabajadores y el monto total de la nómina.

La información se conserva en memoria durante la ejecución del programa. No se utiliza una base de datos ni se guardan los registros al finalizar.

### 2. Organización del proyecto

El proyecto está organizado en dos paquetes:

- **modelo:** contiene las clases `Empleado`, `EmpleadoFijo` y `EmpleadoPorHora`.
- **aplicacion:** contiene la clase `Main`, que controla los menús, la captura de datos, el registro y la impresión de la nómina.

Cada clase se encuentra en su propio archivo `.java`. Se utilizan instrucciones `package` para identificar los paquetes e `import` para acceder a las clases y herramientas necesarias.

### 3. Conceptos de programación utilizados

**Clase, objeto e instancia:** una clase define las características y operaciones de un tipo de objeto. Por ejemplo, `EmpleadoFijo` define los datos y el cálculo de un empleado fijo. Cuando se registra a Ana López, se crea una instancia mediante `new EmpleadoFijo(...)`.

**Constructor:** inicializa los datos de un objeto. Las clases utilizan constructores con parámetros para recibir el DPI, el nombre y los datos necesarios para calcular el pago.

**Parámetros y argumentos:** un parámetro es una variable declarada en la definición de un método o constructor. Un argumento es el valor que se envía al llamar a ese método o constructor. Por ejemplo, en `new EmpleadoFijo(dpi, nombre, sueldo, bonificacion)`, los nombres dentro de los paréntesis representan los argumentos enviados al constructor.

**Palabra clave `this`:** permite referirse al objeto actual y distinguir sus atributos de los parámetros. Por ejemplo, `this.nombre = nombre` asigna al atributo `nombre` el valor recibido mediante el parámetro del mismo nombre.

**Encapsulamiento:** los atributos de las clases se declaran `private` para proteger los datos. Se utilizan métodos de acceso, como `getDpi()` y `getNombre()`, para consultar la información.

**Abstracción:** `Empleado` es una clase abstracta que contiene los datos comunes de los empleados y declara el método abstracto `calcularPago()`. No se crean objetos directamente de esta clase.

**Herencia:** `EmpleadoFijo` y `EmpleadoPorHora` heredan de `Empleado` mediante `extends`. Ambas clases utilizan `super(dpi, nombre)` para inicializar los atributos comunes.

**Sobrescritura:** las clases hijas implementan su propia versión de `calcularPago()` mediante `@Override`. El empleado fijo suma el sueldo y la bonificación; el empleado por hora multiplica la tarifa por las horas trabajadas.

**Polimorfismo:** los empleados fijos y los empleados por hora se almacenan en una sola colección de tipo `ArrayList<Empleado>`. Al invocar `calcularPago()` sobre cada referencia de tipo `Empleado`, Java ejecuta la fórmula correspondiente al objeto concreto.

### 4. Arreglo simple y colección ArrayList

El arreglo `String[] meses` contiene los nombres de los doce meses del año. Los nombres están definidos previamente en el código.

La instrucción `meses[numeroMes - 1]` obtiene el nombre del mes seleccionado. Se resta uno porque los índices de los arreglos comienzan en cero. Por ejemplo, si `numeroMes` vale 3, se consulta `meses[2]`, que corresponde a marzo.

La colección `ArrayList<Empleado> empleados` almacena los registros de ambos tipos de empleados.

- `empleados.add(empleado)`: agrega un empleado después de completar y validar sus datos.
- `empleados.size()`: devuelve la cantidad de empleados registrados.
- `for (Empleado empleado : empleados)`: recorre todos los empleados para calcular sus pagos e imprimir el reporte.

La lista aumenta de tamaño conforme se agregan trabajadores.

### 5. Métodos, parámetros y alcance de las variables

El método `main(String[] args)` inicia la ejecución del programa. Las tareas se distribuyen entre métodos auxiliares como `ingresarNomina()`, `registrarEmpleadoFijo()`, `registrarEmpleadoPorHora()` e `imprimirNomina()`.

Ejemplos de variables y su alcance:

- **Atributo:** `private String nombre` en `Empleado`. Pertenece a cada objeto empleado y se utiliza dentro de la clase que lo declara.
- **Parámetro:** `String nombre` en el constructor de `Empleado`. Recibe el nombre cuando se crea el objeto.
- **Variable local:** `double totalNomina` en `imprimirNomina()`. Se utiliza para acumular los pagos durante la generación del reporte y no es un atributo de los empleados.

También se utiliza el método `leerEnteroEnRango(String mensaje, int minimo, int maximo)`, que recibe parámetros y devuelve un número entero válido mediante `return`.

### 6. Validaciones implementadas

El programa verifica que el mes esté entre 1 y 12, que las opciones de los menús sean válidas, que el DPI contenga exactamente trece dígitos y que el nombre no esté vacío.

Los valores numéricos deben ser válidos, finitos y mayores o iguales a cero. Si el usuario introduce texto donde se espera un número, un valor negativo o un dato fuera del rango permitido, el programa muestra un mensaje y vuelve a solicitarlo.

Los datos de un empleado se agregan a la lista únicamente después de completar correctamente la captura.

### 7. Comprobación del resultado

Para comprobar el funcionamiento, se selecciona el mes 3, correspondiente a marzo, y se registran los siguientes empleados:

**Empleado fijo**
- DPI: 1234567890101
- Nombre: Ana López
- Sueldo mensual: Q 4,000.00
- Bonificación: Q 300.00
- Pago calculado: Q 4,300.00

**Empleado por hora**
- DPI: 9876543210101
- Nombre: Luis Pérez
- Pago por hora: Q 25.00
- Horas trabajadas: 120
- Pago calculado: Q 3,000.00

**Resultado esperado del reporte**
- Mes: Marzo
- Total de trabajadores: 2
- Total de nómina: Q 7,300.00

El total se obtiene sumando Q 4,300.00 más Q 3,000.00.

También deben comprobarse la selección de enero y diciembre, la impresión de una nómina vacía, el regreso al menú principal, la salida del programa y el manejo de entradas inválidas.
