package service;

import lombok.Getter;

/**
 * @author BowenDeng
 */
@Getter
public class DirInfoCache {
    private static String currentPath;
    private static String[] currentFiles;
}
