package service;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author BowenDeng
 */
public class Terminal {
    private static String title;
    private static boolean isLinux() {
        String osName = System.getProperty("os.name").toLowerCase();

        if(osName.contains("linux")){
            return true;
        }else if(osName.contains("ubuntu")){
            return true;
        }else if(osName.contains("centos")){
            return true;
        }else if(osName.contains("debian")){
            return true;
        }else if(osName.contains("mageia")){
            return true;
        }else if(osName.contains("fedora")){
            return true;
        }else if(osName.contains("opensuse")){
            return true;
        }else if(osName.contains("freebsd")){
            return true;
        }else return osName.contains("openeuler");
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    private static void setDefaultTitle(){
        if(isLinux()){
            title = "~/";
        } else if (isWindows()) {
            title = "D://";
        }else{
            System.out.println("ERROR: can't recognize your system as windows or linux");
        }

    }

    public static void initService(){
        setDefaultTitle();
        System.out.println(title + " >>");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] items = line.split(" ");
        TaskManager.commandHandler(items);
    }

    public static void setTitle(String title) {
        Terminal.title = title;
    }

    public static String getTitle(){
        return title;
    }
}
