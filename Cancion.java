import java.util.*;

public class Cancion{
    String titulo;
    double duracion;
    public Cancion(String titulo,double duracion){
        this.titulo=titulo;
        this.duracion=duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return titulo+" : "+duracion;
    }
}
class Album{
    String nombre;
    String artista;
    ArrayList<Cancion> canciones = new ArrayList<>();
    public Album(String nombre,String artista){
        this.nombre=nombre;
        this.artista=artista;
    }
    public static Cancion createCancion(String titulo,double duracion){
        return new Cancion(titulo,duracion);
    }
    private int findSong(String titulo){
        for (int i = 0; i < canciones.size(); i++) {
            if (canciones.get(i).getTitulo().equals(titulo)){
                return i;
            }
        }
        return -1;
    }
    public boolean addNewSong(String cancion,double duracion){
        if (findSong(cancion) == -1){
            canciones.add(createCancion(cancion,duracion));
            return true;
        }
        else{
            System.out.println("Esta cancion ya está añadida.");
            return false;
        }
    }
    public boolean addToPlayList (int num, LinkedList<Cancion> lista){
        ListIterator<Cancion> it = lista.listIterator();
        if (num>=0 && num< canciones.size()){
            it.add(canciones.get(num));
            return true;
        }
        return false;
    }
    public boolean addToPlayList(String titulo,LinkedList<Cancion> lista){
        ListIterator<Cancion> it = lista.listIterator();
        if (findSong(titulo) >=0 && findSong(titulo) < canciones.size()){
            it.add(canciones.get(findSong(titulo)));
            return true;
        }
        return false;
    }
}
class MAIN{

    static LinkedList<Album> albums = new LinkedList<>();
    static LinkedList<Cancion> playlist =new LinkedList<>();

    public static void main(String[] args) {
        Album album1=new Album("DAMN.","Kendrick Lamar");
        Album album2=new Album("Un verano sin ti","Bad Bunny");
        albums.add(album1);
        albums.add(album2);
        album1.addNewSong("FEAR.",7.40);
        album1.addNewSong("HUMBLE.",2.57);
        album1.addNewSong("LUST.",5.07);
        album1.addNewSong("DUCKWORTH.",4.08);
        album1.addNewSong("LOYALTY.",3.47);
        album2.addNewSong("Me porto bonito",2.58);
        album2.addNewSong("Yo no soy celoso",3.50);
        album2.addNewSong("Tití me pregunto",4.03);
        album2.addNewSong("Neverita",2.53);
        album2.addNewSong("Ojitos lindos",4.18);
        album1.addToPlayList(0,playlist);
        album1.addToPlayList(1,playlist);
        album1.addToPlayList(2,playlist);
        album1.addToPlayList(3,playlist);
        album1.addToPlayList("LOYALTY.",playlist);
        album2.addToPlayList(0,playlist);
        album2.addToPlayList(1,playlist);
        album2.addToPlayList(2,playlist);
        album2.addToPlayList(3,playlist);
        album2.addToPlayList("Ojitos lindos",playlist);
        printList(playlist);
        play(playlist);
    }

    public static void printList(LinkedList<Cancion> lista) {
        Iterator<Cancion> it = lista.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("-----");
    }
    public static void imprimirMenu() {
        System.out.println("""
                0 - Salir de la lista de reproduccion
                1 - Reproducir siguiente cancion de la lista
                2 - Reproducir la cancion previa de la lista
                3 - Repetir la cancion actual
                4 - Imprimir la lista de canciones de la playlist
                5 - Imprimir el menú
                6 - Eliminar una cancion de la playlist""");
    }
    public static void play(LinkedList<Cancion> lista) {
        boolean continuar = true;
        ListIterator<Cancion> it = lista.listIterator();

        if (lista.isEmpty()) {
            System.out.println("No hay canciones para escuchar");
            return;
        } else {
            System.out.println("Escuchando " + it.next());
            imprimirMenu();
        }
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                boolean haciaAdelante = true;
                while (continuar) {
                    int opcion = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcion) {
                        case 0 -> {
                            System.out.println("Ha dejado de escuchar la playlist");
                            continuar = false;
                        }
                        case 1 -> {
                            if (!haciaAdelante) {
                                if (it.hasNext())
                                    it.next();
                                haciaAdelante = true;
                            }
                            if (it.hasNext()) {
                                System.out.println("Escuchando " + it.next());
                            } else {
                                System.out.println("Ya no hay más canciones");
                                it.previous();
                                haciaAdelante = false;
                            }
                        }
                        case 2 -> {
                            if (haciaAdelante) {
                                if (it.hasPrevious())
                                    it.previous();
                                haciaAdelante = false;
                            }
                            if (it.hasPrevious()) {
                                System.out.println("Escuchando " + it.previous());
                            } else {
                                System.out.println("Primera cancion");
                                it.next();
                                haciaAdelante = true;
                            }
                        }
                        case 3 -> {
                            if (!haciaAdelante){
                                it.next();
                                System.out.println("Escuchando " + it.previous());
                            }else {
                                it.previous();
                                System.out.println("Escuchando " + it.next());
                            }


                        }
                        case 4 -> {
                            printList(playlist);
                        }
                        case 5 -> {
                            imprimirMenu();
                        }
                        case 6 -> {
                            if (!haciaAdelante){
                                it.next();
                                System.out.println(it.previous().getTitulo() + " ha sido eliminada de la playlist");
                                it.remove();
                                if (it.hasNext()) {
                                    System.out.println("Escuchando " + it.next());
                                } else {
                                    System.out.println("Escuchando " + it.previous());
                                }
                                break;
                            }else {
                                it.previous();
                                System.out.println(it.next().getTitulo() + " ha sido eliminada de la playlist");
                                it.remove();
                                if (it.hasNext()) {
                                    System.out.println("Escuchando " + it.next());
                                } else {
                                    System.out.println("Escuchando " + it.previous());
                                }
                                break;
                            }
                        }
                        default -> {
                            System.out.println("Introduzca una opcion valida");
                        }
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca una pocion valida");
                continuar=true;
            }catch (NoSuchElementException e){
                System.out.println("No hay más canciones en la playlist");
                continuar=true;
            }
        }while(continuar);
    }
}