package nl.lucasouwens.loader;

import nl.lucasouwens.command.parsing.CommandType;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class TerminalCommandLoader {

    public boolean load() {
        final File loadingDir = new File("/modules/");
        if(!(loadingDir.isDirectory())) {
            if(!(loadingDir.mkdirs())) {
                System.out.println("[Lucas' Terminal] Failed to generate module folder");
            }
        }
        for(final File jar : loadingDir.listFiles()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(jar.getAbsolutePath() + "/module.properties")));

            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    String[] split = line.split("=");
                    if(split.length >= 1) {
                        if(split[0].equalsIgnoreCase("main")) {
                            boolean loadable = false;
                            Class<?> clazz = Class.forName(split[1], true, ClassLoader.getSystemClassLoader());
                            if(clazz.newInstance() instanceof TerminalCommandExecutor) {
                                ((TerminalCommandExecutor) clazz.newInstance()).register(); // TODO test if this works
                            }
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                System.out.println("[Lucas' Terminal] Unable to initialize external modules.");
            }
        }

        return false;
    }

}
