package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.common.gl
import br.furb.bigheadedfootball.common.useTexture
import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.TexturesContext
import br.furb.bigheadedfootball.world.objects.GraphicalObject
import javax.media.opengl.GL

class Camp : GraphicalObject() {
    override var size = 1F
    override var color: Color = Color.WHITE

    init {
        transformation.scale(50.0, 15.0, 100.0)
        transformation.translation(25.0, 7.5, 50.0)
    }

    override fun innerDraw() {
        gl {
            useTexture(TexturesContext.campTexture) {
                glBegin(GL.GL_QUADS)
                // Especifica a coordenada de textura para cada vertice
                // Face superior
                glNormal3f(0.0f, 0.5f, 0.0f)
                glTexCoord2f(0.0f, 1f)
                glVertex3f(-0.5f, -0.5f, -0.5f)
                glTexCoord2f(0.0f, 0.0f)
                glVertex3f(-0.5f, -0.5f, 0.5f)
                glTexCoord2f(1f, 0.0f)
                glVertex3f(0.5f, -0.5f, 0.5f)
                glTexCoord2f(1f, 1f)
                glVertex3f(0.5f, -0.5f, -0.5f)
                glEnd()
            }

            useTexture(TexturesContext.arqTexture) {
                glBegin(GL.GL_QUADS)
                // Especifica a coordenada de textura para cada vertice
                // Face superior
                glNormal3f(0.0f, 0.0f, 1.0f)
                glTexCoord2f(1f, 1f)
                glVertex3f(-0.5f, -0.5f, -0.5f)
                glTexCoord2f(0f, 1f)
                glVertex3f(0.5f, -0.5f, -0.5f)
                glTexCoord2f(0f, 0f)
                glVertex3f(0.5f, 0.5f, -0.5f)
                glTexCoord2f(1f, 0f)
                glVertex3f(-0.5f, 0.5f, -0.5f)
                glEnd()
            }

            useTexture(TexturesContext.arqTexture) {
                glBegin(GL.GL_QUADS)
                // Especifica a coordenada de textura para cada vertice
                // Face superior
                glNormal3f(-1.0f, 0.0f, 0.0f)
                glTexCoord2f(1f, 1f)
                glVertex3f(0.5f, -0.5f, -0.5f)
                glTexCoord2f(0f, 1f)
                glVertex3f(0.5f, -0.5f, 0.5f)
                glTexCoord2f(0f, 0f)
                glVertex3f(0.5f, 0.5f, 0.5f)
                glTexCoord2f(1f, 0f)
                glVertex3f(0.5f, 0.5f, -0.5f)
                glEnd()
            }

            useTexture(TexturesContext.arqTexture) {
                glBegin(GL.GL_QUADS)
                // Especifica a coordenada de textura para cada vertice
                // Face superior
                glNormal3f(0.0f, 0.0f, -1f)
                glTexCoord2f(1f, 1f)
                glVertex3f(0.5f, -0.5f, 0.5f)
                glTexCoord2f(0f, 1f)
                glVertex3f(-0.5f, -0.5f, 0.5f)
                glTexCoord2f(0f, 0f)
                glVertex3f(-0.5f, 0.5f, 0.5f)
                glTexCoord2f(1f, 0f)
                glVertex3f(0.5f, 0.5f, 0.5f)
                glEnd()
            }

            useTexture(TexturesContext.arqTexture) {
                glBegin(GL.GL_QUADS)
                // Especifica a coordenada de textura para cada vertice
                // Face superior
                glNormal3f(1f, 0.0f, 0f)
                glTexCoord2f(1f, 1f)
                glVertex3f(-0.5f, -0.5f, 0.5f)
                glTexCoord2f(0f, 1f)
                glVertex3f(-0.5f, -0.5f, -0.5f)
                glTexCoord2f(0f, 0f)
                glVertex3f(-0.5f, 0.5f, -0.5f)
                glTexCoord2f(1f, 0f)
                glVertex3f(-0.5f, 0.5f, 0.5f)
                glEnd()
            }
        }
    }

}