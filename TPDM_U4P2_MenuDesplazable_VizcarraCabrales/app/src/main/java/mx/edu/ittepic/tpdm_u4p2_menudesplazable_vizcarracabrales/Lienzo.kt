package mx.edu.ittepic.tpdm_u4p2_menudesplazable_vizcarracabrales

import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class Lienzo(p:MainActivity):View(p) {

    val puntero = p
    var imagenes = imagenes()
    var toque = 0f
    var anterior = 0f

    fun imagenes():ArrayList<Imagen>{
        var imagenes = ArrayList<Imagen>()
        var separacion = 30f
        imagenes.add(Imagen(this, "Facebook", R.drawable.facebook, separacion))
        imagenes.add(Imagen(this, "Twitter", R.drawable.twitter, imagenes[0].x + imagenes[0].imagen.width + separacion))
        imagenes.add(Imagen(this, "Instagram", R.drawable.instagram, imagenes[1].x + imagenes[1].imagen.width + separacion))
        imagenes.add(Imagen(this, "Whatsapp", R.drawable.whatsapp, imagenes[2].x + imagenes[2].imagen.width + separacion))
        imagenes.add(Imagen(this, "Youtube", R.drawable.youtube, imagenes[3].x + imagenes[3].imagen.width + separacion))
        imagenes.add(Imagen(this, "Linkedin", R.drawable.linkedin, imagenes[4].x + imagenes[4].imagen.width + separacion))
        imagenes.add(Imagen(this, "Snapchat", R.drawable.snapchat, imagenes[5].x + imagenes[5].imagen.width + separacion))
        imagenes.add(Imagen(this, "Google", R.drawable.googleplus, imagenes[6].x + imagenes[6].imagen.width + separacion))
        imagenes.add(Imagen(this, "Skype", R.drawable.skype, imagenes[7].x + imagenes[7].imagen.width + separacion))
        return imagenes
    }
    override fun onDraw(c: Canvas) {
        super.onDraw(c)

        imagenes.forEach {
            it.pintar(c)
        }

    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        when(e.action){
            MotionEvent.ACTION_DOWN -> {
                imagenes.forEach {
                    if (it.estaEnArea(e.getX(), e.getY())){
                        Toast.makeText(puntero, it.titulo, Toast.LENGTH_LONG).show()
                    }
                }
                toque = e.getX()
            }
            MotionEvent.ACTION_MOVE -> {
                if(((imagenes[0].x <= 30f) && ((imagenes[8].x + imagenes[8].imagen.width + 30f) >= width) ) ||
                    ((imagenes[0].x > 30f) && ((toque - e.getX() - anterior) > 0)) ||
                    (((imagenes[8].x + imagenes[8].imagen.width + 30f) < width) && ((toque - e.getX() - anterior) < 0))){
                    var distancia = toque - e.getX()
                    imagenes.forEach {
                        it.mover(distancia - anterior)
                    }
                    invalidate()
                    anterior = distancia
                }
            }
            MotionEvent.ACTION_UP -> {
                anterior = 0f
            }
        }
        return true
    }
}