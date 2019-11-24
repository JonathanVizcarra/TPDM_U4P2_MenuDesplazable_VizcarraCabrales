package mx.edu.ittepic.tpdm_u4p2_menudesplazable_vizcarracabrales

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Imagen(l:Lienzo, t:String, img:Int, posX:Float) {

    val imagen = BitmapFactory.decodeResource(l.resources, img)
    var titulo = t
    var x = posX
    var y = 50f
    var ocultar = false

    fun pintar(c:Canvas){
        if (!ocultar){
            c.drawBitmap(imagen, x, y, Paint())
        }
    }

    fun estaEnArea(toqueX:Float, toqueY:Float):Boolean{

        var x2 = x+imagen.width
        var y2 = y+imagen.height

        if (toqueX >= x && toqueX <= x2){
            if (toqueY >= y && toqueY <= y2){
                return true
            }
        }

        return false
    }

    fun mover(distanciaX:Float){
        x = x - distanciaX
    }
}