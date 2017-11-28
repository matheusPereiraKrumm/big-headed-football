package br.furb.bigheadedfootball.world.objects.parts

import br.furb.bigheadedfootball.common.gl
import br.furb.bigheadedfootball.common.glut
import br.furb.bigheadedfootball.common.useTexture
import br.furb.bigheadedfootball.world.components.Color
import br.furb.bigheadedfootball.world.components.TexturesContext
import br.furb.bigheadedfootball.world.objects.GraphicalObject
import javax.media.opengl.GL

class Goal : GraphicalObject() {
    override var size = 1F
    override var color: Color = Color.WHITE

    init {
        transformationDistortion.scale(16.0, 10.0, 2.0)
        transformation.translation(25.0, 5.0, -0.7)
    }

    override fun innerDraw() {
        gl{

            useTexture(TexturesContext.golTexture) {
                glBegin (GL.GL_QUADS )
                // Especifica a coordenada de textura para cada vertice
                // Face superior
                glNormal3f(0.0f,0.0f,0.5f)
                glTexCoord2f(1f, 1f)
                glVertex3f(-0.5f,  -0.5f, 0.501f)
                glTexCoord2f(0f, 1f)
                glVertex3f(0.5f,  -0.5f,  0.501f)
                glTexCoord2f(0f, 0f)
                glVertex3f( 0.5f,  0.5f,  0.501f)
                glTexCoord2f(1f, 0f)
                glVertex3f( -0.5f,  0.5f, 0.501f)
                glEnd()
            }
            glut{
                glutSolidCube(1F)
            }
        }
    }
}