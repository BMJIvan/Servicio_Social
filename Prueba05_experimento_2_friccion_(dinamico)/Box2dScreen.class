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
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.PolygonShape;
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

    private Body BodyCu,BodySu;
    private Fixture CuFix,SuFix;

    private BitmapFont font;
    private SpriteBatch batch;

    private float F=7,t=0,X=0;
    private ShaderProgram shader;
    private Mesh mesh;
    private String str,pos1,pos2,error;

    private List<Float> graftra;
    private List<Float> grafecu;

    @Override
    public void show() {

        An=5;
        W=Gdx.graphics.getWidth();
        H=Gdx.graphics.getHeight();
        R=H/An;
        La=W/R;
        world=new World(new Vector2(0,-10),true);
        renderer=new Box2DDebugRenderer();
        camera=new OrthographicCamera(La,An);

        Vector2[] chain=new Vector2[2];
        chain[0]=new Vector2(-La/2,-.5f);
        chain[1]=new Vector2(10*(La/2),-.5f);

        BodySu=world.createBody(createBody(0,0,'S'));
        ChainShape cadena;
        cadena=new ChainShape();
        cadena.createChain(chain);
        SuFix=BodySu.createFixture(createFix(cadena,0,1,0));

        PolygonShape Cushape=new PolygonShape();
        BodyCu=world.createBody(createBody(-2.5f,0,'D'));
        Cushape.setAsBox(.5f,.5f);
        CuFix=BodyCu.createFixture(createFix(Cushape,1,(float)Math.pow(.6f,2),0));
        Cushape.dispose();

        font=new BitmapFont();
        batch=new SpriteBatch();
        font.getData().setScale(H/360);
        str=FragShader();
        ShaderProgram.pedantic=false;

        graftra=new ArrayList<Float>();
        grafecu=new ArrayList<Float>();
        graftra.clear();
        grafecu.clear();
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
        for(int u = 0; u < joints.size; u++)
        {
            world.destroyJoint(joints.get(u));
        }

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for(int u = 0; u < bodies.size; u++)
        {
            world.destroyBody(bodies.get(u));
        }

        renderer.dispose();
        world.dispose();
        font.dispose();
        batch.dispose();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.1f,0f,0.1f,0.5f);
        DecimalFormat df = new DecimalFormat("#0.00");

        BodyCu.applyForceToCenter(F,0,true);
        graftra.add(BodyCu.getPosition().x);
        graftra.add(BodyCu.getPosition().y);

        X=((t*t)/2)-2.5f;
        grafecu.add(X);
        grafecu.add(.1f);

        plot(graftra,0,0,1,1,1,0,0,.5f,true);
        plot(grafecu,0,0,1,1,0,1,0,.5f,true);

        pos1=df.format(BodyCu.getPosition().x);
        pos2=df.format(X);
        error=df.format(Math.abs(BodyCu.getPosition().x-X));

        batch.begin();
        font.draw(batch,"Posicion cuerpo  ="+pos1,Box2Pix(-2),Boy2Piy(2.2f));
        font.draw(batch,"Posicion ecuacion="+pos2,Box2Pix(-2),Boy2Piy(1.7f));
        font.draw(batch,"           error ="+error,Box2Pix(-2),Boy2Piy(1.2f));
        batch.end();

        world.step(delta,6,2);
        camera.update();
        renderer.render(world,camera.combined);

        t=t+delta;
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