package nl.lucasouwens.command;

import nl.lucasouwens.command.parsing.CommandType;
import nl.lucasouwens.logger.Logger;
import nl.lucasouwens.logger.MessageType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Commands {

    private static Commands instance = null;

    /**
     * (Singleton)
     * Easy access to the commands class so that we don't need to reconstruct it every time
     * @return Commands
     */
    public static Commands getInstance() {
        if(instance == null) {
            instance = new Commands();
        }

        return instance;
    }

    // Below are the commands which I am registering.

    @CommandType(name = "fdelete", args = {"filename"})
    public void deleteFile(String fileName) {
        File toDelete = new File(fileName);
        if(!(toDelete.exists())) {
            Logger.log("We cannot delete this file.");
        } else if(toDelete.delete()) {
            Logger.log(String.format("The file %s has been deleted", fileName));
        } else {
            Logger.log("We are unable to delete this file");
        }
    }

    @CommandType(name = "fcreate", args = {"filename"}, optional = {"contents"})
    public void createFile(String fileName, String contents) {
        File toCreate = new File(fileName);
        if(toCreate.isDirectory() || toCreate.exists()) {
            Logger.log("We cannot create this file.");
            return;
        }

        File _temp = new File(toCreate.getAbsolutePath().replace(toCreate.getName(), ""));
        if(!_temp.exists()) {
            if(!_temp.mkdirs()) {
                Logger.log("The creation of the directory has failed.");
                return;
            }
        }

        try {
            if (!(toCreate.createNewFile())) {
                Logger.log("The creation of the file has failed");
            }

            if(contents != null) {
                try(FileWriter fw = new FileWriter(toCreate)) {
                    fw.write(contents);
                }
            }

            Logger.log("The file has been created.");
        } catch(IOException e) {
            Logger.log("An error has occurred, (fcreate command failed due to an IOException.)", MessageType.ERROR);
        }

    }

}
