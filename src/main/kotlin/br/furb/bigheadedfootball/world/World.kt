package br.furb.bigheadedfootball.world

import br.furb.bigheadedfootball.common.*
import br.furb.bigheadedfootball.world.components.*
import br.furb.bigheadedfootball.world.objects.GraphicalObject
import br.furb.bigheadedfootball.world.objects.parts.*
import javax.media.opengl.GL
import javax.media.opengl.GLAutoDrawable
import javax.media.opengl.GLEventListener
import com.intellij.ide.a.u.gl
import kotlin.system.measureTimeMillis


class World : GLEventListener {

    private val backgroundColor: Color = Color.SKY
    private val graphicalObjects: ArrayList<GraphicalObject> = ArrayList()
    val camera: Camera
    var mainPlayer: MainPlayer = MainPlayer()
    private var _height: Int = 0
    private var _width: Int = 0

    init {
        val camp = Camp()
        graphicalObjects.add(camp)
        camera = Camera(mainPlayer)

        graphicalObjects.add(Goal())
        populateCharacter()
        mainPlayer.childGraphicalObjects.add(Ball())
        graphicalObjects.add(mainPlayer)
    }

    private fun populateCharacter() {
        val positions = listOf(
                Point(10.0, 0.0, 30.0),
                Point(20.0, 0.0, 20.0),
                Point(30.0, 0.0, 20.0),
                Point(40.0, 0.0, 30.0)
        )

        positions.forEach {
            val character = Player()
            character.transformation.translation(it.x, it.y, it.z)
            graphicalObjects.add(character)
        }
    }

    override fun init(glAutoDrawable: GLAutoDrawable?) {
        initializeProvider(glAutoDrawable)
        gl {
            glClearColor(backgroundColor.red, backgroundColor.green, backgroundColor.blue, 1.0f)
            val posLight = floatArrayOf(5.0f, 30.0f, 30.0f, 0.0f)
            glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, posLight, 0)
            glEnable(GL.GL_DEPTH_TEST)

            glEnable(GL.GL_LIGHT0)
            glEnable(GL.GL_LIGHT1)
            glEnable(GL.GL_LIGHTING)

            glEnable(GL.GL_COLOR_MATERIAL)

            glColorMaterial(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE)

            glEnable(GL.GL_CULL_FACE)
            glEnable(GL.GL_DEPTH_TEST)
            // Habilita o modelo de colorizacao de Gouraud
            glShadeModel(GL.GL_SMOOTH)

            glGenTextures(1, TexturesContext.idTexture, 3)        // Gera identificador de textura
            // Define os filtros de magnificacao e minificacao
            glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR)
            glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR)


            TexturesContext.campTexture = Texture(0, "./img/campo.jpg")
            TexturesContext.golTexture = Texture(1, "./img/gol.jpg")
            TexturesContext.arqTexture = Texture(2, "./img/arq.jpg")
            //TexturesContext.campTexture.load()
            //TexturesContext.golTexture.load()
        }
    }

    override fun reshape(glAutoDrawable: GLAutoDrawable, i: Int, i1: Int, width: Int, height: Int) {
        _width = width
        _height = height

    }

    override fun display(p0: GLAutoDrawable?) {
        gl {
            glClear(GL.GL_COLOR_BUFFER_BIT xor GL.GL_DEPTH_BUFFER_BIT)

            val fps = measureTimeMillis { draw3D() }


            draw2D(1000.0/fps)

            glFlush()
        }
    }

    fun draw2D(fps : Double) {
        gl {
            glMatrixMode(GL.GL_PROJECTION)
            glLoadIdentity()
            glu { gluOrtho2D(0.0, 400.0, 400.0, 0.0) }
            glMatrixMode(GL.GL_MODELVIEW)
            glLoadIdentity()

            glColor3f(1.0f, 1.0f, 0.0f)

            val x = 10
            val y = 390
            glRasterPos2i(x, y)
            glut { glutBitmapString(7, "FPS:"+ fps.toString()) }
        }
    }

    private fun GL.draw3D() {
        gl {
            glMatrixMode(GL.GL_PROJECTION)
            glLoadIdentity()
            glViewport(0, 0, _width, _height)
        }

        glu {
            gluPerspective(50.0, (_width / _height).toDouble(), 0.1, 1000.0)
        }

        glMatrixMode(GL.GL_MODELVIEW)
        glLoadIdentity()
        camera.draw()
        draw()
    }

    override fun displayChanged(p0: GLAutoDrawable?, p1: Boolean, p2: Boolean) {

    }

    private fun draw() {
        gl { glEnable(GL.GL_COLOR_MATERIAL) }
        drawAxis()

        graphicalObjects.forEach {
            it.draw()
        }
    }

    private fun drawAxis() {
        val center = PointCommom.neutralPoint()
        gl {
            // eixo X - Red
            glColor(Color.RED)
            drawGl(GL.GL_LINES) {
                glPoint(center)
                glPoint(Point(10.0, 0.0, 0.0))
            }
            // eixo Y - Green
            glColor(Color.GREEN)
            drawGl(GL.GL_LINES) {
                glPoint(center)
                glPoint(Point(0.0, 10.0, 0.0))
            }
            // eixo Z - Blue
            glColor(Color.BLUE)
            drawGl(GL.GL_LINES) {
                glPoint(center)
                glPoint(Point(0.0, 0.0, 10.0))
            }
        }
    }

}