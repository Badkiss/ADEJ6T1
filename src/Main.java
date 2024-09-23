import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File file=new File("Productos.txt");
        Scanner scanner=new Scanner(System.in);
        int num=0;
        while (num!=4){
            System.out.println("Pulse 1 para guardar un producto");
            System.out.println("Pulse 2 para Mostrar los productos");
            System.out.println("Pulse 3 para Borrar los productos");
            System.out.println("Pulse 4 para salir");
            num=scanner.nextInt();
            if (num==1){
               if (existe(file)){
                   guardar(file);
               }else{
                   file.createNewFile();
                   guardar(file);
               }

            } else if (num==2) {
                if(existe(file)){
                    mostrar(file);
                }else {
                    System.out.println("No hay ningun producto guardado ");
                }

            } else if (num==3) {
                if (existe(file)){
                    file.delete();
                }else{
                    System.out.println("No hay ningun archivo guardado guardado ");
                }

            } else if (num==4) {

            } else {
                System.out.println("Seleccione un numero del 1 al 4");
            }
        }
    }

    private static void mostrar(File file) {
        try (FileInputStream fis=new FileInputStream(file); ObjectInputStream ois=new ObjectInputStream(fis)){
            ArrayList<Producto> arrayList=new ArrayList<>();
            arrayList=(ArrayList<Producto>) ois.readObject();
            for (Producto producto:arrayList){
                System.out.println(producto);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean existe(File file){
        return file.exists();
    }
    public static void guardar(File file){
        Scanner scanner=new Scanner(System.in);
        try (FileInputStream fis=new FileInputStream(file); ObjectInputStream ois =new ObjectInputStream(fis)){
            ArrayList<Producto> arrayList=new ArrayList<Producto>();
            arrayList=(ArrayList<Producto>) ois.readObject();
            if (arrayList.size()==10){
                System.out.println("Lista llena de productos");
            }else{
                try (FileOutputStream fos=new FileOutputStream(file); ObjectOutputStream oos=new ObjectOutputStream(fos)){
                    System.out.println("Digame el id del producto");
                    int id=scanner.nextInt();
                    System.out.println("Digame el nombre del producto");
                    String nombre=scanner.next();
                    System.out.println("Digame el precio del producto");
                    double precio=scanner.nextDouble();
                    arrayList.add(new Producto(id,nombre,precio));
                    oos.writeObject(arrayList);
                    System.out.println("Producto guardado");
                }

            }
        }catch (IOException e) {
            try (FileOutputStream fos=new FileOutputStream(file); ObjectOutputStream oos=new ObjectOutputStream(fos)){
                ArrayList<Producto> arrayList=new ArrayList<Producto>();
                if (arrayList.size()==10){
                    System.out.println("Lista llena de productos");
                }else{
                    System.out.println("Digame el id del producto");
                    int id=scanner.nextInt();
                    System.out.println("Digame el nombre del producto");
                    String nombre=scanner.next();
                    System.out.println("Digame el precio del producto");
                    double precio=scanner.nextDouble();
                    arrayList.add(new Producto(id,nombre,precio));
                    oos.writeObject(arrayList);
                    System.out.println("Producto guardado");
                    System.out.println();
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
