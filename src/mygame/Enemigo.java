/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author Eloy
 */
/*
Clase que genera un objeto enemigo recibiendo sus atributos como variable
*/
public class Enemigo extends TowerDefense{
    
    /*
    Definición de variables globales
    */
    String nombre;
    float velocidad;
    Geometry geo;
    /*
    Constructor de la clase que recibe los parametros
    */
    public Enemigo(String nombre, float velocidad){
        this.nombre = nombre;
        this.velocidad = velocidad;
        generarGeometria();
    }
    /*
    Constructor vacio de la clase
    */
    public Enemigo(){
        
    }
    /*
    Genera la geometria del enemigo
    */
    public void generarGeometria(){
        Sphere cuerpo = new Sphere(30 ,30 ,0.2f);
        geo = new Geometry(nombre, cuerpo);
        geo.move(0, 0.2f, 0);
    }
    
    //Getters
    public String getNombre(){
        return nombre;
    }
    public float getVelocidad(){
        return velocidad;
    }
    public Geometry getGeometria(){
        return geo;
    }
    //Setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setVelocidad(float velocidad){
        this.velocidad = velocidad;
    }
}
