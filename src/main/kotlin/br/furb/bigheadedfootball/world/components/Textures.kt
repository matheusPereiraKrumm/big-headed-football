package br.furb.bigheadedfootball.world.components

import br.furb.bigheadedfootball.common.gl
import com.sun.opengl.util.texture.TextureData
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.nio.ByteBuffer
import javax.imageio.ImageIO
import javax.media.opengl.GL
import javax.swing.JOptionPane


object TexturesContext {
    val idTexture: IntArray = IntArray(4)
    lateinit var campTexture:Texture
    lateinit var golTexture: Texture
    lateinit var arqTexture: Texture
}

class Texture(val index:Int,file: String) {
    private val image: BufferedImage
    private val texture:ByteBuffer

    init {
        try {
            image = ImageIO.read(File(file))
        } catch (e: IOException) {
            JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo " + file)
            throw e
        }
        texture = TextureData(0, 0, false, image).buffer as ByteBuffer
    }

    fun load() {
        gl {
            // Especifica qual e a textura corrente pelo identificador
            glBindTexture(GL.GL_TEXTURE_2D, TexturesContext.idTexture[index])
            // Envio da textura para OpenGL
            glTexImage2D(GL.GL_TEXTURE_2D, 0, 3,
                    image.width, image.height,
                    0, GL.GL_BGR, GL.GL_UNSIGNED_BYTE, texture)
        }
    }
}