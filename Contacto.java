import java.util.ArrayList;
import java.util.Scanner;

public class Contacto {
    private String name;
    private String phoneNumber;

    public Contacto(String name,String phoneNumber){
        this.name=name;
        this.phoneNumber=phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public static Contacto createContact(String nom,String num){
        return new Contacto(nom,num);
    }
}
class TelefonoMovil{
    private static String myNumber;
    private static ArrayList<Contacto> myContacts = new ArrayList<Contacto>();

    public TelefonoMovil(String myNumber,ArrayList<Contacto> myContacts){
        this.myNumber=myNumber;
        this.myContacts=myContacts;
    }

    private static int findContact(Contacto contacto){
        int index = myContacts.indexOf(contacto);
        if (index >=0) {
            return index;
        }
        else
            return -1;
    }
    private static int findContact(String nom){
        for (int i = 0; i < myContacts.size(); i++) {
            if (myContacts.get(i).getName().equals(nom)){
                return i;
            }
        }
        return -1;
    }
    private static int findnumber(String num){
        for (int i = 0; i < myContacts.size(); i++) {
            if (myContacts.get(i).getPhoneNumber().equals(num)){
                return i;
            }
        }
        return -1;
    }
    public static boolean addNewContact(Contacto contacto){
        if (findContact(contacto) == -1){
            myContacts.add(contacto);
            return true;
        }
        else{
            System.out.println("El contacto ya existe.");
            return false;
        }
    }
    public static boolean updateContact(Contacto contv,Contacto contn){
        if (findContact(contv) == -1){
            return false;
        }
        else{
            int index = myContacts.indexOf(contv);
            myContacts.set(index,contn);
            System.out.println(myContacts.get(findContact(contn)).getName()+" -> "+myContacts.get(findContact(contn)).getPhoneNumber());
            return true;
        }

    }
    public static boolean removeContact(Contacto contacto){
        if (findContact(contacto) == -1){
            return false;
        }
        else{
            int index = myContacts.indexOf(contacto);
            myContacts.remove(index);
            return true;
        }
    }
    public static Contacto queryContact(String nom){
        if (findContact(nom)==-1){
            System.out.println("Ese nombre no tiene ningun contacto asociado.");
            return null;
        }
        else{

            return myContacts.get(findContact(nom));
        }
    }
    public static void print1contact(String nom){
        System.out.println(myContacts.get(findContact(nom)).getName()+" -> "+myContacts.get(findContact(nom)).getPhoneNumber());
    }
    public static void printContacts(){
        System.out.println("Lista de contactos:");
        for (int i = 0; i < myContacts.size(); i++) {
            System.out.println(i+1+"."+myContacts.get(i).getName()+" -> "+myContacts.get(i).getPhoneNumber());
        }
    }
    public static void imprimirMenu(){
        System.out.println("0 - Para salir");
        System.out.println("1 - Para imprimir los contactos");
        System.out.println("2 - Para añadir un contacto");
        System.out.println("3 - Para modificar un contacto");
        System.out.println("4 - Para eliminar un contacto");
        System.out.println("5 - Para buscar un contacto ");
    }
    public static boolean comprobarNum(String num){
        if (!num.matches(("[0-9]{9}"))){
            System.out.println("Este numero es incorrecto");
            return false;
        }
        if (findnumber(num)>=0){
            System.out.println("Este numero ya está asociado a otro contacto");
            return false;
        }

        return true;
    }
    public static boolean comprobarNom(String nom){
        if (findContact(nom)>=0){
            return false;
        }
        return true;
    }
}
class Main{
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        boolean continuar=true;
        Scanner st=new Scanner(System.in);
        añadir();
        do {
            TelefonoMovil.imprimirMenu();
            System.out.println("Elija alguna opcion:");
            int opc = st.nextInt();

            switch (opc) {
                case 0:
                    System.out.println("Gracias por usar la agenda, hasta pronto.");
                    continuar=false;
                    break;
                case 1:
                    TelefonoMovil.printContacts();
                    break;
                case 2:
                    añadir();
                    break;
                case 3:
                    actualizar();
                    break;
                case 4:
                    borrar();
                    break;
                case 5:
                    buscar();
                    break;
                default:
                    System.out.println("Elige una opcion correcta.");
            }
        }while (continuar);
    }

    public static void añadir(){
        String num;
        String nom;
        do {
            System.out.print("Escriba el nombre del contacto que desea añadir: ");
            nom= sc.nextLine();
            if (!TelefonoMovil.comprobarNom(nom)){
                System.out.println("Este nombre ya tiene un contacto asociado.");
            }
        }while (!TelefonoMovil.comprobarNom(nom));
        do {
            System.out.print("Escriba el numero del contacto que desea añadir: ");
            num= sc.nextLine();
        }while (!TelefonoMovil.comprobarNum(num));
        Contacto cont=new Contacto(nom,num);
        TelefonoMovil.addNewContact(cont);
    }
    public static void actualizar(){
        String num;
        String nomv;
        do {
            System.out.print("Escriba el nombre del contacto que desea modificar: ");
            nomv= sc.nextLine();
            if (TelefonoMovil.comprobarNom(nomv)){
                System.out.println("Este nombre no existe en tu agenda.");
            }
        }while (TelefonoMovil.comprobarNom(nomv));
        System.out.print("Escriba un nuevo nombre: ");
        String nomn=sc.nextLine();
        do {
            System.out.print("Escriba un numero de telefono nuevo: ");
            num= sc.nextLine();
        }while (!TelefonoMovil.comprobarNum(num));
        Contacto contv=TelefonoMovil.queryContact(nomv);
        Contacto contn=new Contacto(nomn,num);
        TelefonoMovil.updateContact(contv,contn);
    }
    public static void borrar(){
        String nom;
        do {
            System.out.print("Escriba el nombre del contacto que desea eliminar: ");
            nom=sc.nextLine();
            if (TelefonoMovil.comprobarNom(nom)) {
                System.out.println("Este nombre no tiene ningun contacto asociado.");
            }
        }while(TelefonoMovil.comprobarNom(nom));
        Contacto cont=TelefonoMovil.queryContact(nom);
        TelefonoMovil.removeContact(cont);
    }
    public static void buscar(){
        String nom;
        do {
            System.out.print("Escriba el nombre del contacto que quiere buscar: ");
            nom= sc.nextLine();
            if (TelefonoMovil.comprobarNom(nom)) {
                System.out.println("Este nombre no tiene ningun contacto asociado.");
            }
        }while(TelefonoMovil.comprobarNom(nom));
        TelefonoMovil.queryContact(nom);
        TelefonoMovil.print1contact(nom);
    }
}