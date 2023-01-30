import java.util.Arrays;

public class Estudiante implements Comparable<Estudiante>{
    protected String nombre;
    protected int edad;
    protected int altura;

    public Estudiante(String nombre,int edad, int altura){
        this.nombre=nombre;
        this.edad=edad;
        this.altura=altura;
    }

    public int getAltura() {
        return altura;
    }

    public int getEdad() {
        return edad;
    }
    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", altura=" + altura +
                '}';
    }
    @Override
    public int compareTo(Estudiante x) {
        int resultado=0;
        if (getAltura()<x.getAltura())
            resultado=1;
        else if (getAltura()>x.getAltura())
            resultado = -1;
        else if (getEdad()<x.getEdad()){
            resultado=1;
        }
        else if (getEdad()>x.getEdad()){
            resultado=-1;
        }
        else
            resultado=0;
        return resultado;
    }
}
class Main{
    public static void main(String[] args) {
        Estudiante[] e=new Estudiante[5];
        Estudiante e1=new Estudiante("Patri",12,170);
        Estudiante e2 =new Estudiante("Manuel",43,173);
        Estudiante e3=new Estudiante("Javier",72,189);
        Estudiante e4=new Estudiante("Alicia",52,168);
        Estudiante e5=new Estudiante("Alberto",35,189);
        e[0]=e1;
        e[1]=e2;
        e[2]=e3;
        e[3]=e4;
        e[4]=e5;
        System.out.println("Estudiantes sin ordenar:");
        for (int i = 0; i < e.length; i++) {
            System.out.println(e[i].toString());
        }
        System.out.println();
        Arrays.sort(e);
        System.out.println("Estudiantes ordenados:");
        for (int i = 0; i < e.length; i++) {
            System.out.println(e[i].toString());
        }
    }
}