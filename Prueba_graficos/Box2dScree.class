package com.mygdx.prueba;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Box2dScreen extends BaseScreen {

    public Box2dScreen(MainGame game) {
        super(game);
    }

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

    float H,W,R,An,La;

    private Body BodyCr,BodyCa;
    private Fixture CrFix,CaFix;

    private ShaderProgram shader;
    private BitmapFont font;
    private SpriteBatch batch;
    private Mesh mesh;
    private String str,fps,tiempo,lineas;

    private List<Float> grafica;
    private List<Float> graficaf;
    @Override
    public void show() {

        An=10;
        W=Gdx.graphics.getWidth();
        H=Gdx.graphics.getHeight();
        R=H/An;
        La=W/R;
        world=new World(new Vector2(0,0),true);
        renderer=new Box2DDebugRenderer();
        camera=new OrthographicCamera(La,An);

        CircleShape Crshape=new CircleShape();
        BodyCr=world.createBody(createBody(0,-4.5f,'D'));
        Crshape.setRadius(.5f);
        CrFix=BodyCr.createFixture(createFix(Crshape,0,0,1));

        Vector2[] chain=new Vector2[5];
        chain[0]=new Vector2(-9,-6);
        chain[1]=new Vector2(-9,4);
        chain[2]=new Vector2(9,4);
        chain[3]=new Vector2(9,-6);
        chain[4]=new Vector2(-9,-6);

        BodyCa=world.createBody(createBody(0,0,'S'));
        ChainShape cadena;
        cadena=new ChainShape();
        cadena.createChain(chain);
        CaFix=BodyCa.createFixture(createFix(cadena,0,0,1));
        Crshape.dispose();

        font=new BitmapFont();
        batch=new SpriteBatch();
        font.getData().setScale(H/360);
        grafica=new ArrayList<Float>();
        graficaf=new ArrayList<Float>();
        grafica.clear();
        graficaf.clear();

        str=FragShader();
        ShaderProgram.pedantic=false;
    }

    private String VertShader(float r,float g,float b,float a) {
        String R,G,B,A;
        R=Float.toString(r);
        G=Float.toString(g);
        B=Float.toString(b);
        A=Float.toString(a);
        return  "attribute vec4 a_position;    \n" +
                "attribute vec4 a_color;\n" +
                "uniform mat4 u_projTrans;\n" +
                "varying vec4 v_color;" +
                "void main()                  \n" +
                "{                            \n" +
                "   v_color = vec4("+R+", "+G+", "+B+", "+A+"); \n" +
                " gl_Position =  u_projTrans * a_position;\n" +
                "}                            \n" ;
    }

    private String FragShader() {
        return "varying vec4 v_color;\n" +
                "void main()                                  \n" +
                "{                                            \n" +
                "  gl_FragColor = v_color;\n" +
                "}";
    }

    private FixtureDef createFix(Shape cushape, float Den, float Fri, float Res) {
        FixtureDef def=new FixtureDef();
        def.shape=cushape;
        def.density=Den;
        def.friction=Fri;
        def.restitution=Res;
        return def;
    }

    private BodyDef createBody(float Px, float Py, Character Tipo) {
        BodyDef def=new BodyDef();
        def.position.set(Px,Py);
        switch(Tipo)
        {
            case 'D':
                def.type= BodyDef.BodyType.DynamicBody;
                break;
            case 'S':
                def.type= BodyDef.BodyType.StaticBody;
                break;
            case 'K':
                def.type= BodyDef.BodyType.KinematicBody;
                break;
            default:
                def.type= BodyDef.BodyType.StaticBody;
        }
        return def;
    }

    @Override
    public void dispose() {

        Gdx.input.vibrate(500);

        Array<Joint> joints=new Array<Joint>();
        world.getJoints(joints);
        for(int i = 0; i < joints.size; i++)
        {
            world.destroyJoint(joints.get(i));
        }

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for(int i = 0; i < bodies.size; i++)
        {
            world.destroyBody(bodies.get(i));
        }

        renderer.dispose();
        world.dispose();
        font.dispose();
        batch.dispose();
        shader.dispose();
    }


    private float t=0,tn=0;
    private double vel;

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.1f,0f,0.1f,0.5f);
        DecimalFormat df = new DecimalFormat("#0.00");


        if(tn<=0)
        {
            fps=df.format(1/delta);
            tiempo=df.format(t);
            lineas=df.format((grafica.size()/2)-1);
            tn=.1f;
            vel=13/Math.sqrt(Math.pow(BodyCr.getLinearVelocity().x,2)+Math.pow(BodyCr.getLinearVelocity().y,2));
            BodyCr.setLinearVelocity((float)(BodyCr.getLinearVelocity().x*vel),(float)(BodyCr.getLinearVelocity().y*vel));
        }

        batch.begin();
        font.draw(batch,"FPS = "+fps,Box2Pix(-6),Boy2Piy(4.5f));
        font.draw(batch,"Tiempo = "+tiempo,Box2Pix(-2),Boy2Piy(4.5f));
        font.draw(batch,"Lineas = "+lineas,Box2Pix(2),Boy2Piy(4.5f));
        batch.end();

        graficaf.add(BodyCr.getPosition().x);
        graficaf.add(BodyCr.getPosition().y);
        plot(graficaf,3,3,.2f,.2f,.7f,.5f,.4f,1,true);

        grafica.add(BodyCr.getPosition().x);
        grafica.add(BodyCr.getPosition().y);
        if(t>0)
        {
            int tam = grafica.size();
            float[] graf = new float[tam];
            int i = 0;
            for (Float f : grafica) {
                graf[i++] = f;
            }

            i=0;
            short[] indi = new short[(tam - 2)];
            for (int k=0;k<(tam-2)/2;k++) {
                i=2*k;
                indi[i] =(short)k;
                indi[i+1]=(short)(k+1);
            }

            mesh = new Mesh(true, tam / 2, tam-2,
                    new VertexAttribute(VertexAttributes.Usage.Position, 2, "a_position"));
            mesh.setVertices(graf);
            mesh.setIndices(indi);

            String vtr=VertShader(1,1,1,0);
            shader =new ShaderProgram(vtr,str);


            shader.begin();
            shader.setUniformMatrix("u_projTrans", camera.combined);
            mesh.render(shader, GL20.GL_LINES);
            shader.end();
            mesh.dispose();
            shader.dispose();
        }

        if(t<2*delta){
            BodyCr.setLinearVelocity(5,12);
        }

        world.step(delta,6,2);
        camera.update();
        renderer.render(world,camera.combined);
        t=t+delta;
        tn=tn-delta;
    }

    private void plot(List<Float> Gra, float px, float py, float sx, float sy, float roj, float gre, float blu, float alp,boolean bol) {
    if(Gra.size()>=4) {
        int Tam = Gra.size();
        float[] vgra = new float[Tam];
        int k = 0;
        for (Float f : Gra) {
            if(bol){
                vgra[k++]=(f*sx)+px;
            }else {
                vgra[k++]=(f*sy)+py;
            }
            bol=!bol;
        }

        int i=0;
        short[] indic = new short[(Tam - 2)];
        for (k=0;k<(Tam-2)/2;k++) {
            i=2*k;
            indic[i] =(short)k;
            indic[i+1]=(short)(k+1);
        }

        Mesh grafp;
        grafp = new Mesh(true, Tam / 2, Tam - 2,
                new VertexAttribute(VertexAttributes.Usage.Position, 2, "a_position"));
        grafp.setVertices(vgra);
        grafp.setIndices(indic);
        String vtr = VertShader(roj, gre, blu, alp);
        shader = new ShaderProgram(vtr, str);

        shader.begin();
        shader.setUniformMatrix("u_projTrans", camera.combined);
        grafp.render(shader, GL20.GL_LINES);
        shader.end();
        grafp.dispose();
        shader.dispose();
    }
    }

    private float Boy2Piy(float Cy) { return An*R-((An/2)-Cy)*R; }
    private float Box2Pix(float Cx) { return (Cx+(La/2))*R; }
    private float Piy2Boy(float Py) { return (An/2)-(Py/R); }
    private float Pix2Box(float  Px) { return (Px/R)-(La/2); }
}