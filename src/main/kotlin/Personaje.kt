/***********************************************************************************************************************
 *  CLASE: Personaje
 *  CONSTRUCTOR:
 *      nombre          - > Nombre del personaje
 *      pesoMochila     - > Peso máximo que puede soportar la mochila
 *      estadoVital     - > Sólo puede tomar los valores Adulto, Joven o Anciano
 *      raza            - > Sólo puede tomar los valores Enano, Elfo, Humano y Goblin
 *      clase           - > Sólo puede tomar los valores Mercader, Ladrón, Guerrero, Mago y Berserker
 *
 *
 *  ATRIBUTOS:
 *      mochila         - > Cada personaje dispone de una mochila con un límite de peso establecido en el constructor
 *      monedero        - > Cada personaje dispone de un monedero con capacidad para coins de 1, 5, 10, 25 y 100
 *
 *  METODOS
 *      get/setNombre()         - > Devuelve/Establece el nombre del Personaje
 *      get/setEstadoVital()    - > Devuelve/Establece el estado vital del Personaje
 *      get/setRaza()           - > Devuelve/Establece la raza del Personaje
 *      get/setClase()          - > Devuelve/Establece la clase del Personaje
 *      getMochila              - > Devuelve/Establece la mochila del Personaje
 *
 *      cifrado                 - > Param. Entrada: <Texto a crifrar> <ROT>
 *      comunicacion            - > Param. Entrada: <Mensaje para el usuario>
 *      vender                  - > Param. Entrada: <Personaje Mercader> <Articulo a vender>
 *                                  Descripción: método que comprueba si el Personaje pasado como parámetro de entrada
 *                                  es Mercader o no. Asimismo, comprueba el contenido de la mochila y si en él se
 *                                  encuentra el Artículo pasado como parámetro de entrada. De ser así, lo mueve a la
 *                                  mochila del mercader, realiza la transacción económica y lo elimina de la mochila
 *                                  del Personaje.
 *
 *      cashConverter           - > Param. Entrada: <Articulo>
 *                                  Descripción: método que transforma el valor del Artículo en monedas.
 *
 **********************************************************************************************************************/
class Personaje(
    private var nombre: String,
    private var pesoMochila: Int,
    private var estadoVital: Estado,
    private var raza: Raza,
    private var clase: Clase,

    ) {

    private var mochila=Mochila(pesoMochila)
    var monedero = HashMap<Int, Int>()

    enum class Raza {Humano, Elfo, Enano, Goblin}
    enum class Clase {Combatiente, Mercader}
    enum class Estado {Anciano, Adulto, Joven}


    init {
        monedero.put(1, 0)
        monedero.put(5, 0)
        monedero.put(10, 0)
        monedero.put(25, 0)
        monedero.put(100, 0)
    }

    fun getNombre():String{
        return nombre
    }
    fun setNombre(nombre:String){
        this.nombre=nombre
    }
    fun getEstadoVital():Estado{
        return estadoVital
    }
    fun setEstadoVital(estadoVital:Estado){
        this.estadoVital=estadoVital
    }
    fun getRaza():Raza{
        return raza
    }
    fun setRaza(raza:Raza){
        this.raza=raza
    }
    fun getClase():Clase{
        return clase
    }
    fun setClase(clase:Clase){
        this.clase=clase
    }
    fun getMochila(): Mochila {
        return this.mochila
    }
    fun cifrado(mensaje : String, ROT : Int) : String{
        val abecedario : ArrayList<Char> = "abcdefghijklmnñopqrstuvwxyz".toList() as ArrayList<Char>
        var stringInv = ""
        var indice = 0

        for(i in mensaje.lowercase().toList() as ArrayList<Char>){
            if(!i.isLetter() || i.isWhitespace()){
                stringInv += i
            } else{
                indice = abecedario.indexOf(i) + ROT
                if (indice >= abecedario.size) {
                    indice -= abecedario.size
                    stringInv += abecedario[indice]
                } else
                    stringInv += abecedario[indice]
            }
        }
        return stringInv.filter { !it.isWhitespace() && it.isLetter() }
    }
    fun comunicacion(mensaje:String){
        var respuesta=""
        when(estadoVital){
            Estado.Adulto->{
                if (mensaje.equals("¿Como estas?"))
                    respuesta="En la flor de la vida, pero me empieza a doler la espalda"
                else
                    if ((mensaje.contains('?') || mensaje.contains('¿')) && mensaje == mensaje.uppercase())
                        respuesta="Estoy buscando la mejor solución"
                    else
                        if (mensaje == mensaje.uppercase())
                            respuesta="No me levantes la voz mequetrefe"
                        else
                            if (mensaje == nombre)
                                respuesta="¿Necesitas algo?"
                            else
                                respuesta="No sé de qué me estás hablando"
            }
            Estado.Joven->{
                if (mensaje.equals("¿Como estas?"))
                    respuesta="De lujo"
                else
                    if ((mensaje.contains('?') || mensaje.contains('¿')) && mensaje == mensaje.uppercase())
                        respuesta="Tranqui se lo que hago"
                    else
                        if (mensaje == mensaje.uppercase())
                            respuesta="Eh relájate"
                        else
                            if (mensaje == nombre)
                                respuesta="Qué pasa?"
                            else
                                respuesta="Yo que se"

            }
            Estado.Anciano->{
                if (mensaje.equals("¿Como estas?"))
                    respuesta="No me puedo mover"
                else
                    if ((mensaje.contains('?') || mensaje.contains('¿')) && mensaje == mensaje.uppercase())
                        respuesta="Que no te escucho!"
                    else
                        if (mensaje == mensaje.uppercase())
                            respuesta="Háblame más alto que no te escucho"
                        else
                            if (mensaje == nombre)
                                respuesta="Las 5 de la tarde"
                            else
                                respuesta="En mis tiempos esto no pasaba"
            }
        }
        when(raza){
            Raza.Elfo-> println(cifrado(respuesta, 1))
            Raza.Enano-> println(respuesta.uppercase())
            Raza.Goblin-> println(cifrado(respuesta, 1))
            Raza.Humano-> println(respuesta)
        }
    }
    fun vender(mercader:Personaje, articulo: Articulo) {
        var id: String
        var posicion: Int
        if (!mercader.clase.equals("Mercader"))
            print("No soy un Mercader")
        else {
            if(this.mochila.getContenido().size!=0){
                println("Tienes ${this.mochila.getContenido().size} objetos")
                mochila.getContenido().forEach { println(it) }
                posicion=mochila.findObjeto(articulo.getId())
                if(posicion!=-1){
                    println("Movemos el artículo al mercader")
                    mercader.mochila.addArticulo(mochila.getContenido()[posicion])
                    println("Convertimos el artículo en monedas")
                    cashConverter(mochila.getContenido()[posicion])
                    println("Eliminamos el artículo del jugador principal")
                    this.mochila.getContenido().remove(mochila.getContenido()[posicion])
                }else println("No hay ningún objeto con ese ID")

            }else println("No tienes objetos para vender")

            println("Te quedan ${this.mochila.getContenido().size} objetos")
            println("Hasta pronto")
        }
    }
    fun cashConverter(articulo: Articulo){
        var total = 0
        var i = 0
        var coins = arrayListOf(1, 5, 10, 25, 100)
        coins.sortDescending()

        while(total < articulo.getValor() && i < coins.size) {
            if (total + coins[i] <= articulo.getValor()) {
                total += coins[i]
                monedero[coins[i]] = monedero[coins[i]]!! + 1
            } else
                i++
        }
    }

}