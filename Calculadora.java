package cristo;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
public class Calculadora extends JFrame {
private static final long serialVersionUID = 1L;
private JTextField despliega = new JTextField("0");
private JPanel panBotones = new JPanel(); //creamos un nuevo panel de botones un marco pequeño 
private JButton [] boton = new JButton[30]; //creamos un arreglo de 30 botones
private String [] botmsg = {"MC","MR","MS","M+","M-","←","CE","C","+-","\u221A","7","8","9","/","%",
"4","5","6","*","1/x","1","2","3","-","+","0",".","log","\u03c0","="};
private String texto = "";
private boolean bPunto = true;
private double op1,op2,res,memoria;
private char oper;
class ManejadorEventos implements ActionListener{ //clase interna
public void actionPerformed(ActionEvent evento ){
for (int i=0; i < boton.length;i++){
//Que hay q hacer cuando preisonmos el boton[i]
if (evento.getSource() == boton[i]){

if (i >= 10 && i<=12 || (i>= 15 && i<=17) || //
(i>=20 && i<= 22) || i==25 || i==26){
if(i == 26 && bPunto){
texto+=boton[i].getText(); //concatena el texto 
bPunto = false; //no puede poner punto
} 
else if(i != 26) texto+=boton[i].getText();
despliega.setText(texto); //despleiga texto
}
if (i== 13 || i == 18 || i== 23 || i == 24){ //si introduce * / - + etc
oper = boton[i].getText().charAt(0);
op1 = Double.parseDouble(texto);
texto = "0";
bPunto = true;
despliega.setText(texto); 
texto = "";
}
if (i == 8 && texto !=""){
res = -Double.parseDouble(texto);
texto = ""+ res;
despliega.setText(texto);
}

if (i== 14 && texto!= ""){
texto = ""+Double.parseDouble(texto)/100;
}
if (i == 19 && texto !=""){
res = 1/Double.parseDouble(texto); //tecla inverso
texto = ""+res;
despliega.setText(texto);
}

if (i== 28){
texto ="" + Math.PI; //tecla pi
despliega.setText(texto);
}
if (i == 29){
op2 = Double.parseDouble(texto);
bPunto = true;
switch (oper){
case '/': res = op1 /op2;
break;
case '*': res = op1 *op2;
break;
case '-': res = op1 -op2;
break;
case '+': res = op1 +op2;
break;
}
texto = ""+res; //eel numero l conviete atexto
despliega.setText(texto); 
}
}
}
}
}
public Calculadora(){
super ("Calculadora");
ManejadorEventos maneja = new ManejadorEventos(); //para poder escuchar el evento con maneja
setLayout(new BorderLayout()); //el marco principal esta dividido en regiones
despliega.setEditable(false); //no se puede editar este campo
despliega.setHorizontalAlignment(SwingConstants.RIGHT); //despleiga el textfield a la derecha
despliega.setFont(new Font("Courier",Font.BOLD,24)); //cambia el tipo de letra
panBotones.setLayout(new GridLayout(6,5,3,3)); //un nuevo gridlayout de 6 * 5 prametro 3,3 separacion entre botones
for (int i=0;i<boton.length;i++){
boton[i] = new JButton(botmsg[i]); 
boton [i].addActionListener(maneja);
boton[i].setFont(new Font("Courier",Font.BOLD,18)); //coor de bootnes
boton[i].setBackground(new Color(50,20,250)); //color de letras
boton[i].setForeground(new Color(250,250,250));
panBotones.add(boton[i]);
}
add(BorderLayout.NORTH,despliega); 
add(BorderLayout.CENTER,panBotones); //ubicar el marco de botones en el centro
BufferedImage image= null;
try{

image=ImageIO.read(getClass().getClassLoader().getResource("surf.png"));

}catch(IOException e){


e.printStackTrace();
}
super.setIconImage(image);
}
public static void main(String[] args) {
Calculadora calc = new Calculadora();
calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
calc.setLocation(300, 300);
calc.setSize(350,400);
calc.setVisible(true);
}
}