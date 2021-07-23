<h1>Prueba Juntas</h1>
    <p>Se va a modificar la aplicación de prueba de procesador para que en lugar de que los objetos nuevos aparezcan de manera aleatoria, ahora se unirán con juntas.</p>
    <p>Primero se agrega una lista para guardar las juntas.</p>

```javascript
private Array<Joint> joints=new Array<Joint>();
```

   <p>Se va a modificar la función donde se crean los objetos, se actualiza el valor de FPS, se reinicia el temporizador y se define una forma poligonal.</p>

```javascript
fps=df.format(1/delta);
tn=.25f;
PolygonShape Cushape=new PolygonShape();
```

   <p>Con un condicional se hará que el primer objeto se cree en un punto predeterminado, y para los demás objetos, se crearán en la misma posición que el objeto anterior.</p>

```javascript
if(i==0) {
    bodiesW.add(world.createBody(createBody(0, 3, 'D')));
}else
{
    bodiesW.add(world.createBody(createBody(bodiesW.get(i-1).getPosition().x, bodiesW.get(i-1).getPosition().y, 'D')));
}
Cushape.setAsBox(.04f,.03f);
fixturesW.add(bodiesW.get(i).createFixture(createFix(Cushape,1f,0,3f)));
Cushape.dispose();
```

   <p>Se actualiza el valor del contador de objetos.</p>

```javascript
i=i+1;
```

   <p>A partir de que se cree el segundo objeto, este se unirá al anterior por medio de una junta.</p>

```javascript
if(i>=2)
{
 joints.add(world.createJoint(createRevjoin(bodiesW.get(i-2),bodiesW.get(i-1),0.035f,0,-0.035f,0,true,false,0,0)));
}
```

   <p>se le dio un valor de restitución mayor a uno para que los objetos siguieran moviéndose después de crearlos. La gravedad se cambio a -5.</p>
   <p align="center"><img src="https://github.com/BMJIvan/Servicio_Social/blob/master/Prueba_juntas/imagen01.jpg?raw=true" width="60%"></p>