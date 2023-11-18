import kotlin.random.Random
/***********************************************************************************************************************
 *  CLASE: Dado
 *  CONSTRUCTOR:
 *      numMin    - > límite inferior del rango del dado
 *      numMax    - > límite superior del rango del dado
 *
 *  METODOS
 *      tirada()  - > Devuelve el valor (Int) aleatorio entre 1 y 6
 **********************************************************************************************************************/
class Dado() {

    private var numMin=1
    private var numMax=6

    fun tirada (): Int {
        return Random.nextInt(numMin, numMax)
    }
}