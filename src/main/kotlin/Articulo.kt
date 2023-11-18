/***********************************************************************************************************************
 *  CLASE: Articulo
 *  CONSTRUCTOR:
 *      id      - > Nombre del artículo
 *      peso    - > Peso del artículo
 *      valor   - > Valor del artículo
 *
 *  METODOS
 *      getPeso()       - > Devuelve el peso como Int
 *      getValor()      - > Devuelve el valor como Int
 *      getId()         - > Devuelve el nombre del artículo como String
 *      toString()      - > Sobreescribimos el método toString para darle formato a la visualización de los artículos
 **********************************************************************************************************************/
class Articulo (private var id:String, private var peso:Int, private var valor:Int){

    fun getPeso():Int{
        return peso
    }
    fun getValor():Int{
        return valor
    }
    fun getId():String{
        return id
    }
    override fun toString(): String {
        return "[ID:$id, Peso:$peso, Valor:$valor]"
    }
}