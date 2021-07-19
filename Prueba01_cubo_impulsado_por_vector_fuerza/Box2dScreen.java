package com.mygdx.prueba;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.net.httpserver.Filter;

public class Box2dScreen extends BaseScreen {

    public Box2dScreen(MainGame game) {
        super(game);
    }

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private float W,H,W2,H2;

    private Body BodyParedI,BodyParedD,BodyParedAr,BodyParedAb,Bodycadena1,Bodycadena2;
    private Fixture ParedIFix, ParedDFix, ParedArFix,ParedAbFix,Cadena1Fix,Cadena2Fix;

    private Body BodyCubo;
    private Fixture CuboFix;

    private Vector2 chain[];

    private float E;

    @Override
    public void show() {


        W=Gdx.graphics.getWidth();
        H=Gdx.graphics.getHeight();
        W2=W/2;
        H2=H/2;
        E=H/480;
        world=new World(new Vector2(0,0),true);
        renderer=new Box2DDebugRenderer();
        camera=new OrthographicCamera(W/20,H/20);

        BodyParedAb=world.createBody(createBodypareddef(0f,-H/40+.25f));
        PolygonShape ParedShape=new PolygonShape();
        ParedShape.setAsBox((W/40)-.5f,.25f);
        ParedAbFix=BodyParedAb.createFixture(ParedShape,1f);
        ParedAbFix.setRestitution(.7f);
        ParedAbFix.setFriction(.3f);

        BodyParedAr=world.createBody(createBodypareddef(0f,+H/40-.25f));
        ParedShape.setAsBox((W/40)-.5f,.25f);
        ParedArFix=BodyParedAr.createFixture(ParedShape,1f);
        ParedArFix.setRestitution(.7f);
        ParedArFix.setFriction(.3f);

        BodyParedD=world.createBody(createBodypareddef(W/40-.25f,0));
        ParedShape.setAsBox(.25f,H/40);
        ParedDFix=BodyParedD.createFixture(ParedShape,1f);
        ParedDFix.setRestitution(.7f);
        ParedDFix.setFriction(.3f);

        BodyParedI=world.createBody(createBodypareddef(-W/40+.25f,0));
        ParedShape.setAsBox(.25f,H/40);
        ParedIFix=BodyParedI.createFixture(ParedShape,1f);
        ParedIFix.setRestitution(.7f);
        ParedIFix.setFriction(.3f);
        ParedShape.dispose();

        BodyCubo=world.createBody(createBodycubodef());
        PolygonShape cuboShape=new PolygonShape();
        cuboShape.setAsBox(.5f+((E-1)*.5f),.5f+((E-1)*.5f));
        CuboFix=BodyCubo.createFixture(cuboShape,1/E);
        CuboFix.setRestitution(.7f);
        CuboFix.setFriction(.3f);
        cuboShape.dispose();

        Vector2[] chain=new Vector2[7];
        chain[0]=new Vector2(1*E,0f);
        chain[1]=new Vector2(3*E,2*E);
        chain[2]=new Vector2(5*E,2*E);
        chain[3]=new Vector2(7*E,0);
        chain[4]=new Vector2(5*E,-2*E);
        chain[5]=new Vector2(3*E,-2*E);
        chain[6]=new Vector2(1*E,0);


        Bodycadena1=world.createBody(createcedena1());
        ChainShape cadena;
        cadena=new ChainShape();
        cadena.createChain(chain);
        Cadena1Fix=Bodycadena1.createFixture(cadena,1);

        chain[0]=new Vector2(-7*E,0f);
        chain[1]=new Vector2(-5*E,2*E);
        chain[2]=new Vector2(-3*E,2*E);
        chain[3]=new Vector2(-1*E,0);
        chain[4]=new Vector2(-3*E,-2*E);
        chain[5]=new Vector2(-5*E,-2*E);
        chain[6]=new Vector2(-7*E,0);

        Bodycadena2=world.createBody(createcedena2());
        cadena=new ChainShape();
        cadena.createChain(chain);
        Cadena2Fix=Bodycadena2.createFixture(cadena,1);
        cadena.dispose();
    }

    private BodyDef createcedena1() {
        BodyDef def = new BodyDef();
        def.type= BodyDef.BodyType.DynamicBody;
        return def;
    }

    private BodyDef createcedena2() {
        BodyDef def = new BodyDef();
        def.type= BodyDef.BodyType.StaticBody;
        return def;
    }

    private BodyDef createBodycubodef() {
        BodyDef def = new BodyDef();
        def.position.set(0, 10);
        def.type= BodyDef.BodyType.DynamicBody;
        return def;
    }

    private BodyDef createBodypareddef(float x,float y) {
        BodyDef def = new BodyDef();
        def.position.set(x, y);
        def.type= BodyDef.BodyType.StaticBody;
        return def;
    }

    @Override
    public void dispose() {
        BodyParedAb.destroyFixture(ParedAbFix);
        world.destroyBody(BodyParedAb);
        BodyParedAr.destroyFixture(ParedArFix);
        world.destroyBody(BodyParedAr);
        BodyParedI.destroyFixture(ParedIFix);
        world.destroyBody(BodyParedI);
        BodyParedD.destroyFixture(ParedDFix);
        world.destroyBody(BodyParedD);
        BodyCubo.destroyFixture(CuboFix);
        world.destroyBody(BodyCubo);
        Bodycadena1.destroyFixture(Cadena1Fix);
        world.destroyBody(Bodycadena1);
        Bodycadena2.destroyFixture(Cadena2Fix);
        world.destroyBody(Bodycadena2);
        renderer.dispose();
	world.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.justTouched()){
            impulso(Gdx.input.getX(),Gdx.input.getY());
        }

        world.step(delta,6,2);
        camera.update();
        renderer.render(world,camera.combined);
    }

    private void impulso (float x,float y){
        float magx,magy;
        Vector2 position=BodyCubo.getPosition();
        magx=x-(position.x*20+W2);
        magy=y-(-position.y*20+H2);
        BodyCubo.setLinearVelocity(0,0);
        BodyCubo.applyLinearImpulse(magx/10,-magy/10,position.x,position.y,true);
    }
}
