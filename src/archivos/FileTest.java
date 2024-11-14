package archivos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileTest {

    public static void main(String[] args) {
        MyFile file = new MyFile();
        Scanner lea = new Scanner(System.in).useDelimiter("\n");
        int options = 0;
        String text;

        do {
            System.out.println("""
                           1 - Set Archivo/Folder
                           2 - Ver Informacion
                           3 - Crear Archivo
                           4 - Crear Folder
                           5 - Agregar Archivo a Folder
                           6 - Borrar
                           7 - Comando DIR
                           8 - TREE
                           9 - Sobrescribir Archivo
                           10 - Agregar Texto al Archivo
                           11 - Leer Archivo
                           12 - Salir
                           """);
            System.out.print("Ingrese una opcion: ");
            try {
                options = lea.nextInt();
                switch (options) {
                    case 1:
                        System.out.print("Ingrese la direccion del archivo o carpeta: ");
                        file.setFile(lea.next());
                        break;
                    case 2:
                        file.info();
                        break;
                    case 3:
                        if (file.crearFile()) {
                            System.out.println("¡Archivo creado exitosamente!");
                        } else {
                            System.out.println("No se pudo crear el archivo.");
                        }
                        break;
                    case 4:
                        if (file.crearFolder()) {
                            System.out.println("¡Carpeta creada exitosamente!");
                        } else {
                            System.out.println("No se pudo crear la carpeta.");
                        }
                        break;
                    case 5:
                        System.out.print("Nombre de la carpeta a agregar: ");
                        String folderName = lea.next();
                        System.out.print("Ruta de destino: ");
                        String ruta = lea.next();

                        try {
                            if (file.addToFolder(folderName, ruta)) {
                                System.out.println("Carpeta agregada correctamente.");
                            } else {
                                System.out.println("No se pudo agregar la carpeta.");
                            }
                        } catch (IOException e) {
                            System.out.println("Error al agregar la carpeta: " + e.getMessage());
                        }
                        break;
                    case 6:
                        file.borrar();
                        break;
                    case 7:
                        file.dir();
                        break;
                    case 8:
                        file.tree();
                        break;
                    case 9:
                        System.out.print("Escriba el texto con el cual quiere sobrescribir: ");
                        text = lea.next();
                        file.overwrite(text);
                        break;
                    case 10:
                        System.out.print("Escriba el texto que desea agregar: ");
                        text = lea.next();
                        file.write(text);
                        break;
                    case 11:
                        file.readfile();
                        break;
                    case 12:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor ingrese un número válido.");
                lea.next();  // Limpiar entrada incorrecta
            } catch (IOException e) {
                System.out.println("Error al ejecutar la operación: " + e.getMessage());
            }
        } while (options != 12);
    }

}
