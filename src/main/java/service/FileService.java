package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.FilmeController;

public class FileService<T> {

    private String path;
    private static Scanner scan = new Scanner(System.in);
    private PrintWriter printWriter = null;
    private FileWriter fileWriter = null;

    public FileService(String path) {
        this.path = path;
    }

    public T insert(T item) {
        try {
            fileWriter = new FileWriter(this.path, true);
            printWriter = new PrintWriter(fileWriter);
            printWriter.println(item);

        } catch (IOException ex) {
            System.out.println("Não foi possível cadastrar");
        } finally {
            if (printWriter != null) {
                printWriter.close(); // Fechar o PrintWriter apenas uma vez
            }
        }

        return item;
    }

    public T delete(T item) {
        try {
            File file = new File(this.path);

            if (file.exists()) {

                List<String> lines = Files.readAllLines(file.toPath());
                int lineIdx = lines.indexOf(item.toString());

                // altera se achar a linha
                if (lineIdx != -1) {
                    lines.remove(lineIdx);
                }

                Files.write(file.toPath(), lines);
                System.out.println("Item deletado com sucesso");

            } else {
                System.out.println("Arquivo nao encontrado");
            }

        } catch (IOException ex) {
            System.out.println("Erro ao deletar o item");
            Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return item;
    }

    public T updateItem(T item, T newValue) {
        try {
            File file = new File(this.path);

            if (file.exists()) {

                List<String> lines = Files.readAllLines(file.toPath());

                int lineIdx = lines.indexOf(item.toString());

                // altera se achar a linha
                if (lineIdx != -1) {
                    lines.set(lineIdx, newValue.toString());
                    Files.write(file.toPath(), lines);
                } else {
                    throw new Exception("Item nao encontrado");
                }

            } else {
                System.out.println("Arquivo nao encontrado");
            }

        } catch (IOException ex) {
            System.out.println("Erro ao alterar o filme");
            Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // e.printStackTrace();
        }

        return item;
    }
    

}
