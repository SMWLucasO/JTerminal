package nl.lucasouwens.command;

import nl.lucasouwens.command.parsing.CommandType;

import java.io.File;
import java.io.IOException;

public class Commands {

    private static Commands instance = null;

    public static Commands getInstance() {
        if(instance == null) {
            instance = new Commands();
        }

        return instance;
    }

    @CommandType(name = "fdelete", args = {"filename"})
    public void deleteFile(String fileName) {
        File toDelete = new File(fileName);
        if(!(toDelete.exists())) {
            System.out.println("[Lucas' Terminal] We cannot delete this file.");
        } else if(toDelete.delete()) {
            System.out.println(String.format("[Lucas' Terminal] The file %s has been deleted", fileName));
        } else {
            System.out.println("[Lucas' Terminal] We are cannot delete this file.");
        }
    }

    @CommandType(name = "fcreate", args = {"filename"}, optional = {"contents"})
    public void createFile(String fileName) {
        File toCreate = new File(fileName);
        if(toCreate.isDirectory() || toCreate.exists()) {
            System.out.println("[Lucas' Terminal] We cannot create this file.");
            return;
        }


        File _temp = new File(toCreate.getPath());
        if(!_temp.exists()) {
            if(!_temp.mkdirs()) {
                System.out.println("[Lucas' Terminal] The creation of the directory has failed.");
                return;
            }
        }

        try {
            if (!(toCreate.createNewFile())) {
                System.out.println("[Lucas' Terminal] The creation of the file has failed.");
            }
        } catch(IOException e) {
            System.out.println("[Lucas' Terminal] " + e.getMessage());
        }

    }

}
