import kotlin.system.measureTimeMillis

fun main() {
    // Menú principal con opciones para el usuario
    while (true) {
        println("\nSeleccione una opción:")
        println("1. Ordenar una lista usando el método de Burbuja")
        println("2. Ordenar una lista usando el método Quick Sort")
        println("3. Calcular el factorial de un número")
        println("4. Resolver el problema de las Torres de Hanoi")
        println("5. Salir del programa")

        when (readLine()?.toIntOrNull()) {
            1 -> ejecutarMetodoBurbuja()
            2 -> ejecutarQuickSort()
            3 -> calcularFactorialUsuario()
            4 -> resolverTorresHanoiUsuario()
            5 -> {
                println("Saliendo del programa...")
                break
            }
            else -> println("Opción no válida. Por favor, intente nuevamente.")
        }
    }
}

// 1. Ordenar usando el método de Burbuja
fun ejecutarMetodoBurbuja() {
    println("Ingrese una lista de números separados por comas (ejemplo: 9,4,6,2,7):")
    val numeros = readLine()?.split(",")?.mapNotNull { it.trim().toIntOrNull() } ?: run {
        println("Entrada no válida. Asegúrese de usar números enteros.")
        return
    }

    val tiempoTranscurrido = measureTimeMillis {
        val listaOrdenada = metodoBurbuja(numeros.toMutableList())
        println("Lista ordenada utilizando el método de Burbuja: $listaOrdenada")
    }

    println("Tiempo de ejecución: ${tiempoTranscurrido}ms")
}

fun metodoBurbuja(lista: MutableList<Int>): List<Int> {
    // Algoritmo de ordenamiento de burbuja
    for (pasada in 0 until lista.size - 1) {
        for (indice in 0 until lista.size - 1 - pasada) {
            if (lista[indice] > lista[indice + 1]) {
                // Intercambio de elementos si no están en orden
                val temporal = lista[indice]
                lista[indice] = lista[indice + 1]
                lista[indice + 1] = temporal
            }
        }
    }
    return lista
}

// 2. Ordenar usando Quick Sort
fun ejecutarQuickSort() {
    println("Ingrese una lista de números separados por comas (ejemplo: 9,4,6,2,7):")
    val numeros = readLine()?.split(",")?.mapNotNull { it.trim().toIntOrNull() } ?: run {
        println("Entrada no válida. Asegúrese de usar números enteros.")
        return
    }

    val tiempoTranscurrido = measureTimeMillis {
        val listaOrdenada = metodoQuickSort(numeros)
        println("Lista ordenada utilizando Quick Sort: $listaOrdenada")
    }

    println("Tiempo de ejecución: ${tiempoTranscurrido}ms")
}

fun metodoQuickSort(lista: List<Int>): List<Int> {
    // Caso base: si la lista tiene uno o ningún elemento, ya está ordenada
    if (lista.size <= 1) return lista

    val pivote = lista[lista.size / 2]
    val menores = lista.filter { it < pivote }
    val iguales = lista.filter { it == pivote }
    val mayores = lista.filter { it > pivote }

    // Ordena recursivamente y concatena los resultados
    return metodoQuickSort(menores) + iguales + metodoQuickSort(mayores)
}

// 3. Calcular el factorial de un número ingresado por el usuario
fun calcularFactorialUsuario() {
    println("Ingrese un número entero positivo:")
    val numero = readLine()?.toIntOrNull()

    if (numero == null || numero < 0) {
        println("Entrada no válida. Por favor, ingrese un número entero positivo.")
        return
    }

    val resultado = calcularFactorial(numero)
    println("El factorial de $numero es: $resultado")
}

fun calcularFactorial(n: Int): Long {
    // Calcula el factorial de manera recursiva
    return if (n == 0) 1 else n * calcularFactorial(n - 1)
}

// 4. Resolver las Torres de Hanoi para un número de discos dado
fun resolverTorresHanoiUsuario() {
    println("Ingrese el número de discos:")
    val discos = readLine()?.toIntOrNull()

    if (discos == null || discos <= 0) {
        println("Entrada no válida. Por favor, ingrese un número entero positivo.")
        return
    }

    println("Resolviendo Torres de Hanoi con $discos discos:")
    resolverTorresHanoi(discos, "Torre A", "Torre C", "Torre B")
}

fun resolverTorresHanoi(cantidad: Int, origen: String, destino: String, auxiliar: String) {
    // Caso base: si solo hay un disco, se mueve directamente
    if (cantidad == 1) {
        println("Mover disco 1 de $origen a $destino")
    } else {
        // Mover n-1 discos al auxiliar
        resolverTorresHanoi(cantidad - 1, origen, auxiliar, destino)
        // Mover el disco restante al destino
        println("Mover disco $cantidad de $origen a $destino")
        // Mover los n-1 discos del auxiliar al destino
        resolverTorresHanoi(cantidad - 1, auxiliar, destino, origen)
    }
}
