package nl.lucasouwens.loader;

import nl.lucasouwens.command.parsing.CommandType;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TerminalCommandLoader {

    private static TerminalCommandLoader instance;

    public static TerminalCommandLoader getInstance() {
        if(instance == null) {
            instance = new TerminalCommandLoader();
        }

        return instance;
    }

    public boolean load() {
        final File loadingDir = new File("modules/");
        if(!(loadingDir.isDirectory())) {
            if(!(loadingDir.mkdirs())) {
                System.out.println("[Lucas' Terminal] Failed to generate module folder");
            }
        }
        System.out.println("[Lucas' Terminal] Loading modules...");
        for(File jar : loadingDir.listFiles()) {
            try {
                JarFile jJar = new JarFile(jar);
                JarEntry entry = jJar.getJarEntry("module.properties");

                BufferedReader reader = new BufferedReader(new InputStreamReader(jJar.getInputStream(entry)));

                String line;
                while((line = reader.readLine()) != null) {
                    String[] split = line.split("=");
                    if(split.length >= 1) {
                        if(split[0].equalsIgnoreCase("main")) {
                            Class<?> clazz = getClass(jar.getAbsolutePath(), split[1]);
                            if(clazz.newInstance() instanceof TerminalCommandExecutor) {
                                System.out.println("[Lucas' Terminal] Registered the plugin " + clazz.getSimpleName());
                                ((TerminalCommandExecutor) clazz.newInstance()).register();
                            }
                        }
                    }
                }

            } catch (IOException | InstantiationException | IllegalAccessException e) {
                System.out.println("[Lucas' Terminal] Unable to initialize external modules.");
            }
        }
        System.out.println("[Lucas' Terminal] Modules loaded.");

        return false;
    }

    public Class getClass(String path, String name) {
        try {
            JarFile jarFile = new JarFile(path);
            Enumeration<JarEntry> e = jarFile.entries();

            URL[] urls = {new URL("jar:file:" + path + "!/")};
            URLClassLoader cl = URLClassLoader.newInstance(urls);

            while (e.hasMoreElements()) {
                JarEntry je = e.nextElement();
                if (je.isDirectory() || !je.getName().endsWith(".class")) {
                    continue;
                }
                // -6 because of .class
                String className = je.getName().substring(0, je.getName().length() - 6);
                className = className.replace('/', '.');
                Class c = cl.loadClass(className);
                if(c.getName().equalsIgnoreCase(name)) {
                    return c;
                }
            }
        } catch(IOException | ClassNotFoundException e) {
            System.out.println("[Lucas' Terminal] Failed to find plugin for " + name.split(".")[name.split(".").length -1]);
        }

        return null;
    }

}
